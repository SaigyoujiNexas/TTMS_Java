package xupt.se.ttms.idao

import xupt.se.ttms.model.Studio

interface iStudioDAO {
    fun insert(stu: Studio): Int
    fun update(stu: Studio): Int
    fun delete(ID: Int): Int
    fun select(studioName: String): List<Studio>
}