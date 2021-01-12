<template>
    <app-new-update :text="buttonText" :update-count="valueNumber" :hide-count="true" @reload="onReload" />
</template>

<script lang="ts">
import { Mixins, Component, Prop, Watch } from 'vue-property-decorator'
import { NewBlockSubscription } from '@app/modules/blocks/NewBlockSubscription/newBlockSubscription.mixin'
import { newBlock } from '@app/modules/blocks/blocks.graphql'
import { TranslateResult } from 'vue-i18n'
import AppNewUpdate from '@app/core/components/ui/AppNewUpdate.vue'

@Component({
    components: {
        AppNewUpdate
    }
})
export default class NoticeNewBlock extends Mixins(NewBlockSubscription) {
    /*
    ===================================================================================
      Props:
    ===================================================================================
  */
    @Prop({ type: String, default: 'block' }) pageId!: string // Ids: block, uncles, txs, pending

    /*
    ===================================================================================
      Data:
    ===================================================================================
  */
    countTotal: number = 0
    newTxsCount: number = 0
    newUncles: number = 0
    validID = ['block', 'tx', 'pending', 'uncle']
    valueNumber: number = 0

    /*
    ===================================================================================
      Lifecycle:
    ===================================================================================
  */

    mounted() {
        //Check for valid ids:
        if (!this.validID.includes(this.pageId)) {
            throw new Error('Invalid pageId for notice new block component: ' + this.pageId)
        }
    }

    /*
    ===================================================================================
      Watch:
    ===================================================================================
  */

    @Watch('newBlockNumber')
    onNewBlockNumberChange(newVal: number, oldVal: number): void {
        if (this.pageId === this.validID[0] && this.newBlockNumber) {
            this.countTotal++
            this.valueNumber = this.countTotal
        }
        if (this.pageId === this.validID[1] && this.newTxs && this.newTxs > 0) {
            this.newTxsCount += this.newTxs
            this.valueNumber = this.newTxs
        }
        if (this.pageId === this.validID[3] && this.newUncles > 0) {
            this.valueNumber = this.newUncles
        }
    }
    /*
    ===================================================================================
      Methods:
    ===================================================================================
  */
    /**
     * Reloads everything and emit's reload to parent
     */
    onReload() {
        this.$emit('reload')
        this.valueNumber = 0
        this.countTotal = 0
        this.newTxsCount = 0
        this.newUncles = 0
    }

    /*
    ===================================================================================
      Computed:
    ===================================================================================
  */
    get buttonText(): String {
        const plural = this.countTotal === 1 ? 1 : 2
        const message = `message.update.${this.pageId}`
        return `${this.valueNumber} ${this.$tc(message, plural)}`
    }
}
</script>
