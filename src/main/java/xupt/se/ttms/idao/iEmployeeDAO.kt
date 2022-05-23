package xupt.se.ttms.idao;

import xupt.se.ttms.entity.Employee

interface iEmployeeDAO {
    fun insert(emp: Employee): Int
    fun delete(emp: Employee): Int
    fun delete(id: Int): Int
    fun update(emp: Employee): Int
    fun select(name: String): List<Employee>
}