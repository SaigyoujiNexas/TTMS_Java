package xupt.se.ttms.idao

import xupt.se.ttms.entity.Play
import xupt.se.ttms.entity.Studio

interface iPlayDAO {
    fun insert(py: Play): Int
    fun update(py: Play): Int
    fun delete(id: Int): Int
    fun select(name: String): List<Play>
}