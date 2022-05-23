package xupt.se.ttms.entity

import java.math.BigDecimal

data class Play(
    var id: Int = 0,
    var dictID: Int? = null,
    var dictLangId: Int? = null,
    val name: String,
    val introduction: String? = null,
    val image : String? = null,
    val video : String? = null,
    val length : Int? = null,
    val ticketPrice: BigDecimal? = null
)
