package xupt.se.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import xupt.se.ttms.entity.Play
import java.lang.ref.WeakReference
import java.math.BigDecimal

object BigDecimalAdapter{
    @FromJson
    fun fromJson(str: String) = BigDecimal(str)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()

}

object MOSHI{
    private var inst: WeakReference<Moshi>? = null


    val instance: Moshi
    get(){
        if (inst == null || inst!!.get() == null) {
            synchronized(this)
            {
                if (inst == null || inst!!.get() == null)
                {
                    inst = WeakReference(Moshi.Builder().
                        add(BigDecimalAdapter)
                        .addLast(KotlinJsonAdapterFactory())
                        .build())
                }
            }
        }
        return inst!!.get()!!
    }
}