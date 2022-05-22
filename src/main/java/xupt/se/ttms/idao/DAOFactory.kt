package xupt.se.ttms.idao

import xupt.se.ttms.dao.EmployeeDAO
import xupt.se.ttms.dao.StudioDAO

object DAOFactory {
    private val stuDao: StudioDAO by lazy {
        StudioDAO()
    }

    private val employeeDAO: EmployeeDAO by lazy {
        EmployeeDAO()
    }
}