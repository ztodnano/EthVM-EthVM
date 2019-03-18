package io.enkrypt.common.config

import java.math.BigInteger

object DaoHardFork {

  val balances = listOf(
    Pair("d4fe7bc31cedb7bfb8a345f31e668033056b2728", BigInteger("110000142499690430")),
    Pair("2c19c7f9ae8b751e37aeb2d93a699722395ae18f", BigInteger("8519214441755701")),
    Pair("1975bd06d486162d5dc297798dfc41edd5d160a7", BigInteger("989001281201758473335")),
    Pair("319f70bab6845585f412ec7724b744fec6095c85", BigInteger("90658")),
    Pair("06706dd3f2c9abf0a21ddcc6941d9b86f0596936", BigInteger("1428573279216753537")),
    Pair("5c8536898fbb74fc7445814902fd08422eac56d0", BigInteger("205100000000392887672")),
    Pair("779543a0491a837ca36ce8c635d6154e3c4911a6", BigInteger("100000000000000000")),
    Pair("2a5ed960395e2a49b1c758cef4aa15213cfd874c", BigInteger("18693039890011849")),
    Pair("5c6e67ccd5849c0d29219c4f95f1a7a93b3f5dc5", BigInteger("1")),
    Pair("200450f06520bdd6c527622a273333384d870efb", BigInteger("1250001619314659344457")),
    Pair("6b0c4d41ba9ab8d8cfb5d379c69a612f2ced8ecb", BigInteger("854763543")),
    Pair("d1ac8b1ef1b69ff51d1d401a476e7e612414f091", BigInteger("18387737083543350")),
    Pair("51e0ddd9998364a2eb38588679f0d2c42653e4a6", BigInteger("10000012954517274755")),
    Pair("9f27daea7aca0aa0446220b98d028715e3bc803d", BigInteger("99998647723253121277")),
    Pair("d9aef3a1e38a39c16b31d1ace71bca8ef58d315b", BigInteger("100000129545172747556")),
    Pair("6f6704e5a10332af6672e50b3d9754dc460dfa4d", BigInteger("41173345768012804300")),
    Pair("492ea3bb0f3315521c31f273e565b868fc090f17", BigInteger("367380383063135344585")),
    Pair("9ea779f907f0b315b364b0cfc39a0fde5b02a416", BigInteger("15841461690131427090010")),
    Pair("cc34673c6c40e791051898567a1222daf90be287", BigInteger("60000077727103648")),
    Pair("ac1ecab32727358dba8962a0f3b261731aad9723", BigInteger("1")),
    Pair("440c59b325d2997a134c2c7c60a8c61611212bad", BigInteger("266854104538362875475")),
    Pair("9c15b54878ba618f494b38f0ae7443db6af648ba", BigInteger("2236999142516500888")),
    Pair("21c7fdb9ed8d291d79ffd82eb2c4356ec0d81241", BigInteger("27428797178668633")),
    Pair("1ca6abd14d30affe533b24d7a21bff4c2d5e1f3b", BigInteger("76761842290232377901")),
    Pair("6131c42fa982e56929107413a9d526fd99405560", BigInteger("2121837249362469256186")),
    Pair("542a9515200d14b68e934e9830d91645a980dd7a", BigInteger("12548793143344641481996")),
    Pair("782495b7b3355efb2833d56ecb34dc22ad7dfcc4", BigInteger("250000323862931868891")),
    Pair("3ba4d81db016dc2890c81f3acec2454bff5aada5", BigInteger("1")),
    Pair("e4ae1efdfc53b73893af49113d8694a057b9c0d1", BigInteger("5000006477258637377")),
    Pair("0737a6b837f97f46ebade41b9bc3e1c509c85c53", BigInteger("7144077587762826223")),
    Pair("52c5317c848ba20c7504cb2c8052abd1fde29d03", BigInteger("1996002585721648041229")),
    Pair("5d2b2e6fcbe3b11d26b525e085ff818dae332479", BigInteger("5000006477258637377")),
    Pair("057b56736d32b86616a10f619859c6cd6f59092a", BigInteger("9900012824972102")),
    Pair("304a554a310c7e546dfe434669c62820b7d83490", BigInteger("3642408527612792706899331")),
    Pair("914d1b8b43e92723e64fd0a06f5bdb8dd9b10c79", BigInteger("285714295714285714286")),
    Pair("4deb0033bb26bc534b197e61d19e0733e5679784", BigInteger("1256101627216914882057")),
    Pair("35a051a0010aba705c9008d7a7eff6fb88f6ea7b", BigInteger("15276059789372406985")),
    Pair("9da397b9e80755301a3b32173283a91c0ef6c87e", BigInteger("934889382511061152962")),
    Pair("0101f3be8ebb4bbd39a2e3b9a3639d4259832fd9", BigInteger("559384955979606013894")),
    Pair("bcf899e6c7d9d5a215ab1e3444c86806fa854c76", BigInteger("30696803822257124360133")),
    Pair("a2f1ccba9395d7fcb155bba8bc92db9bafaeade7", BigInteger("5000006477258637377")),
    Pair("d164b088bd9108b60d0ca3751da4bceb207b0782", BigInteger("1000001295451727475566")),
    Pair("1cba23d343a983e9b5cfd19496b9a9701ada385f", BigInteger("68587370259945226")),
    Pair("9fcd2deaff372a39cc679d5c5e4de7bafb0b1339", BigInteger("1409336722195117395464")),
    Pair("0e0da70933f4c7849fc0d203f5d1d43b9ae4532d", BigInteger("19173240336954131945545")),
    Pair("d131637d5275fd1a68a3200f4ad25c71a2a9522e", BigInteger("118886510785155274580")),
    Pair("bc07118b9ac290e4622f5e77a0853539789effbe", BigInteger("5634097608979247392143")),
    Pair("acd87e28b0c9d1254e868b81cba4cc20d9a32225", BigInteger("207153967008322399135")),
    Pair("5524c55fb03cf21f549444ccbecb664d0acad706", BigInteger("6773243673260677597543")),
    Pair("fe24cdd8648121a43a7c86d289be4dd2951ed49f", BigInteger("269833661813680507459")),
    Pair("253488078a4edf4d6f42f113d1e62836a942cf1a", BigInteger("3486036451558542464")),
    Pair("b136707642a4ea12fb4bae820f03d2562ebff487", BigInteger("7277385711515429122911683")),
    Pair("f14c14075d6c4ed84b86798af0956deef67365b5", BigInteger("2123311222366559138")),
    Pair("aeeb8ff27288bdabc0fa5ebb731b6f409507516c", BigInteger("859189750496835322093")),
    Pair("6d87578288b6cb5549d5076a207456a1f6a63dc0", BigInteger("1944767821345229848")),
    Pair("accc230e8a6e5be9160b8cdf2864dd2a001c28b6", BigInteger("23997787866533545896")),
    Pair("4613f3bca5c44ea06337a9e439fbc6d42e501d0a", BigInteger("28927603152430302650042")),
    Pair("d343b217de44030afaa275f54d31a9317c7f441e", BigInteger("5192307692307692307692")),
    Pair("84ef4b2357079cd7a7c69fd7a37cd0609a679106", BigInteger("598974326560793095813484")),
    Pair("da2fef9e4a3230988ff17df2165440f37e8b1708", BigInteger("73722042576599901129491")),
    Pair("f4c64518ea10f995918a454158c6b61407ea345c", BigInteger("269565591797974102411594")),
    Pair("7602b46df5390e432ef1c307d4f2c9ff6d65cc97", BigInteger("369231179004682274248")),
    Pair("bb9bc244d798123fde783fcc1c72d3bb8c189413", BigInteger("1200000000000000001")),
    Pair("807640a13483f8ac783c557fcdf27be11ea4ac7a", BigInteger("89472700"))
  )

  val withdrawAccount = "bf4ed7b27f1d666546e30d74d50d173d20bca754"
}
