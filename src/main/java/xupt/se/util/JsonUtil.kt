package xupt.se.util

import com.squareup.moshi.adapter

@ExperimentalStdlibApi
object JsonUtil {
    inline fun <reified T> toJson(t: T): String{
        val moshi = MOSHI.instance
        val adapter = moshi.adapter<T>()
        return adapter.toJson(t)
    }

    inline fun <reified T> fromJson(str: String): T{
        val moshi = MOSHI.instance
        val adapter = moshi.adapter<T>()
        return adapter.fromJson(str) as T
    }
}