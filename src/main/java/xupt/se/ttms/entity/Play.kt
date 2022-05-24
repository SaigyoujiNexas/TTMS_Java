package xupt.se.ttms.entity

import com.squareup.moshi.JsonClass
import java.math.BigDecimal
@JsonClass(generateAdapter = true)
data class Play(
    var id: Int = 0,
    var type: PlayType,
    var lang: PlayLang,
    val name: String,
    val introduction: String = "",
    val image : String? = null,
    val video : String? = null,
    val length : Int,
    val ticketPrice: BigDecimal,
    val status: Short
)
@JsonClass(generateAdapter = true)
data class PlayType(
    var id: Int,
    var name: String = ""
)
@JsonClass(generateAdapter = true)
data class PlayLang(
    var id: Int,
    var name: String = ""
)
