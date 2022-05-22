package xupt.se.ttms.dao

import xupt.se.ttms.idao.iEmployeeDAO
import xupt.se.ttms.model.Employee
import xupt.se.ttms.model.Gender
import xupt.se.util.DBUtil

class EmployeeDAO : iEmployeeDAO {
    override fun insert(emp: Employee): Int {
        var result = 0;
        try{
            var sql = "INSERT INTO employee(dict_id, emp_no, emp_name," +
                    " emp_gender, emp_telnum, emp_email, emp_pwd)" +
                    "values(${emp.dictionaryId}, '${emp.no}', '${emp.name}'," +
                    "${emp.gender?.code?:"NULL"}, '${emp.tel}', '${emp.email}', '${emp.password}')"
            val db = DBUtil()
            db.openConnection()
            var rst = db.getInsertObjectIDs(sql)
            if(rst.first()) emp.id = rst.getInt(1)
            db.close(rst)
            db.close()
            result = 1;
        }catch (e: Exception)
        {
            e.printStackTrace()
        }
        return result
    }
    override fun delete(emp: Employee): Int {
        return delete(emp.id)
    }

    override fun delete(id: Int): Int {
        var result = 0
        try{
            var sql = "DELETE FROM employee WHERE emp_id = $id"
            val db = DBUtil()
            db.openConnection()
            result = db.execCommand(sql)
            db.close()
        }catch (e: Exception)
        {
            e.printStackTrace()
        }
        return result
    }
//
//    data class Employee(
//        var id: Int = -1, var dictionaryId: Int? = null,
//        var no: String = "", val name: String,
//        val gender: Gender?, val tel: String?,
//        val email: String?, val password: String?)
    override fun update(emp: Employee): Int {
        var result = 0
        try{
            val sql = "UPDATE employee SET dict_id = ${emp.dictionaryId}, emp_no = '${emp.no}'," +
                    "emp_name = '${emp.name}', emp_gender = ${emp.gender?.code?:"NULL"}, " +
                    "emp_telnum = '${emp.tel}', emp_email = '${emp.email}', emp_pwd = '${emp.password}' " +
                    "WHERE emp_id = ${emp.id}"
            val db = DBUtil()
            db.openConnection()
            result = db.execCommand(sql)
            db.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
        return result;
    }

    override fun select(name: String): List<Employee> {
        var list = mutableListOf<Employee>()
        val db = DBUtil()
        try{
            name.trim()
            val sql = "SELECT * FROM employee WHERE emp_name LIKE '%$name%'"
            if(!db.openConnection())
            {
                println("Failed to connect database")
                return list
            }
            val rst = db.execQuery(sql)
            rst?.let {
                while(rst.next())
                {
                    val emp = Employee(
                        id = rst.getInt("emp_id"),
                        dictionaryId = rst.getInt("dict_id"),
                        no = rst.getString("emp_no"),
                        name = rst.getString("emp_name"),
                        tel = rst.getString("emp_telnum"),
                        gender = Gender.get(rst.getShort("emp_gender")),
                        email =  rst.getString("emp_email"),
                        password = rst.getString("emp_pwd"))
                    list += emp
                }
                db.close(rst)
                db.close()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return list
    }
}