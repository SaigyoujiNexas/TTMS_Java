import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import xupt.se.ttms.entity.Play
import xupt.se.ttms.service.PlaySrv
import java.math.BigDecimal

object BigDecimalAdapter{
    @FromJson
    fun fromJson(str: String) = BigDecimal(str)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()

}
@OptIn(ExperimentalStdlibApi::class)
fun main(){
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .add(BigDecimalAdapter).build()
    val list = mutableListOf<Play>()


    val adapter = moshi.adapter<List<Play>>()
    println(adapter.toJson(list.toList()))
}