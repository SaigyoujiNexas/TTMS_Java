package xupt.se.ttms.idao;

import xupt.se.ttms.dao.EmployeeDAO
import xupt.se.ttms.dao.PlayDAO
import xupt.se.ttms.dao.StudioDAO

object DAOFactory {
    val stuDao: StudioDAO by lazy {
        StudioDAO()
    }
    val employeeDAO: EmployeeDAO by lazy {
        EmployeeDAO()
    }
    val playDAO: PlayDAO by lazy { PlayDAO() }
}