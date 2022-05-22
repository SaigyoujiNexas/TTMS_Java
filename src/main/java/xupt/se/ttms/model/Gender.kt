package xupt.se.ttms.model

sealed class Gender {
    object F : Gender() {
        override fun toString(): String {
            return "女"
        }
    }

    object M : Gender() {
        override fun toString(): String {
            return "男"
        }
    }
    val code: Int
    get() = when(this) {
        Gender.F -> 0
        Gender.M -> 1
    }
    companion object{
        fun get(code: Short) =
            when(code){
                0.toShort() -> Gender.F
                1.toShort() -> Gender.M
                else -> Gender.F
            }
        }
}