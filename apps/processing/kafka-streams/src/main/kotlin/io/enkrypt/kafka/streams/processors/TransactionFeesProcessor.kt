package io.enkrypt.kafka.streams.processors

import io.enkrypt.avro.processing.BlockMetricsRecord
import io.enkrypt.avro.processing.TransactionFeeListRecord
import io.enkrypt.avro.processing.TransactionFeeRecord
import io.enkrypt.avro.processing.TransactionGasPriceListRecord
import io.enkrypt.avro.processing.TransactionGasPriceRecord
import io.enkrypt.avro.processing.TransactionGasUsedListRecord
import io.enkrypt.avro.processing.TransactionGasUsedRecord
import io.enkrypt.kafka.streams.config.Topics
import io.enkrypt.kafka.streams.config.Topics.CanonicalGasPrices
import io.enkrypt.kafka.streams.config.Topics.CanonicalGasUsed
import io.enkrypt.kafka.streams.config.Topics.CanonicalReceipts
import io.enkrypt.kafka.streams.config.Topics.CanonicalTransactionFees
import io.enkrypt.kafka.streams.config.Topics.CanonicalTransactions
import io.enkrypt.kafka.streams.config.Topics.TransactionFeeBlockMetrics
import io.enkrypt.kafka.streams.serdes.Serdes
import mu.KLogger
import mu.KotlinLogging
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Materialized
import java.math.BigInteger
import java.util.Properties

class TransactionFeesProcessor : AbstractKafkaProcessor() {

  override val id: String = "transaction-fees-processor"

  override val kafkaProps: Properties = Properties()
    .apply {
      putAll(baseKafkaProps.toMap())
      put(StreamsConfig.APPLICATION_ID_CONFIG, id)
      put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1)
    }

  override val logger: KLogger = KotlinLogging.logger {}

  override fun buildTopology(): Topology {

    // Create stream builder
    val builder = StreamsBuilder()

    CanonicalGasPrices.sinkFor(
      CanonicalTransactions.stream(builder)
        .mapValues { transactionsList ->

          TransactionGasPriceListRecord.newBuilder()
            .setGasPrices(
              transactionsList.getTransactions()
                .map { tx ->

                  TransactionGasPriceRecord.newBuilder()
                    .setAddress(tx.getFrom())
                    .setGasPrice(tx.getGasPrice())
                    .build()

                }
            ).build()

        }
    )


    CanonicalGasUsed.sinkFor(
      CanonicalReceipts.stream(builder)
        .mapValues { receiptsList ->

          TransactionGasUsedListRecord.newBuilder()
            .setGasUsed(
              receiptsList.getReceipts()
                .map { receipt ->

                  TransactionGasUsedRecord.newBuilder()
                    .setGasUsed(receipt.getGasUsed())
                    .build()

                }
            ).build()
        }
    )

    CanonicalTransactionFees.sinkFor(

      CanonicalGasPrices.table(builder)
        .join(
          CanonicalGasUsed.table(builder),
          { left, right ->

            val feeListBuilder = TransactionFeeListRecord.newBuilder()

            val gasPrices = left.getGasPrices()
            val gasUsage = right.getGasUsed()

            if (gasPrices.size == gasUsage.size) {
              // if sizes are not the same then it's likely a fork has happened and we are in the process of receiving updated lists or we have not received all data yet from parity

              feeListBuilder.transactionFees =
                gasPrices
                  .zip(gasUsage)
                  .map { (gasPrice, gasUsed) ->

                    val fee = (gasPrice.getGasPrice().toBigInteger() * gasUsed.getGasUsed().toBigInteger()).toString()

                    TransactionFeeRecord.newBuilder()
                      .setAddress(gasPrice.getAddress())
                      .setTransactionFee(fee)
                      .build()

                  }

            }

            feeListBuilder.build()
          },
          Materialized.with(Serdes.CanonicalKey(), Serdes.TransactionFeeList())
        ).toStream()

    )

    return builder.build()
  }
}
