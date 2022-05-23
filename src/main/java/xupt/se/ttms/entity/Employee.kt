package xupt.se.ttms.entity

data class Employee(
    var id: Int = -1, var dictionaryId: Int? = null,
    var no: String = "", val name: String,
    val gender: Gender?, val tel: String?,
    val email: String?, val password: String?)
