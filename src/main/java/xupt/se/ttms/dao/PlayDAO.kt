package xupt.se.ttms.dao

import xupt.se.ttms.entity.Play
import xupt.se.ttms.idao.iPlayDAO
import xupt.se.util.DBUtil
import java.util.*

class PlayDAO : iPlayDAO {
    override fun insert(py: Play): Int {
        var result = 0
        try {
            val sql = ("insert into play(dict_type_id, dict_lang_id, play_name, play_introduction, play_image, play_video, play_length, play_ticket_price)"
                    + "values (${py.dictID}, ${py.dictLangId}, '${py.name}', '${py.introduction}', '${py.image}', '${py.video}'," +
                    "${py.length}, ${py.ticketPrice}")
            val db = DBUtil()
            db.openConnection()
            val rst = db.getInsertObjectIDs(sql)
            if (rst != null && rst.first()) {
                py.id = rst.getInt(1)
            }
            db.close(rst)
            db.close()
            result = 1
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result

    }

    override fun update(py: Play): Int {
        var result = 0
        try {
            val sql =
                ("update play set dict_type_id = ${py.dictID}, dict_lang_id = ${py.dictLangId}, play_name = '${py.name}', " +
                        "play_introduction = '${py.introduction}', play_image = '${py.image}', play_video = '${py.video}', play_video = '${py.video}'," +
                        "play_length = ${py.length}, play_ticket_price = ${py.ticketPrice.toString()} " +
                        "where play_id = ${py.id}")
            val db = DBUtil()
            db.openConnection()
            result = db.execCommand(sql)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    override fun delete(id: Int): Int {
        var result = 0
        try {
            val sql = "delete from play where play_id = $id"
            val db = DBUtil()
            db.openConnection()
            result = db.execCommand(sql)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun selectStudioId(id: Int): String? {
        val db = DBUtil()
        var result = ""
        try {
            val sql = "select play_name from play where play_id= $id"
            if (!db.openConnection()) {
                print("fail to connect database")
                return null
            }
            val rst = db.execQuery(sql)
            if (rst != null) {
                while (rst.next()) {
                    result = rst.getString("play_name")
                }
            }
            db.close(rst)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun fetchAll(): List<Play> {
        val db = DBUtil()
        val list: MutableList<Play> = LinkedList()
        try {
            val sql = "SELECT * from studio"
            if (!db.openConnection()) {
                print("fail to connect database")
                return emptyList()
            }
            val rst = db.execQuery(sql)
            if (rst != null) {
                while (rst.next()) {
                    val play = Play(
                    id = rst.getInt("play_id"),
                    dictID = rst.getInt("dict_type_id"),
                    dictLangId = rst.getInt("dict_lang_id"),
                    name =  rst.getString("play_name"),
                    introduction =  rst.getString("play_introduction"),
                    image = rst.getString("play_image"),
                        video = rst.getString("play_video"),
                        length = rst.getInt("play_length"),
                        ticketPrice = rst.getBigDecimal("play_ticket_price")
                    )
                    list.add(play)
                }
            }
            db.close(rst)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    override fun select(name: String): List<Play> {
        val db = DBUtil()
        val list: MutableList<Play> = LinkedList()
        try {
            name.trim()
            val sql = "select * from play where play_name like '%$name%' "
            if (!db.openConnection()) {
                print("fail to connect database")
                return emptyList()
            }
            val rst = db.execQuery(sql)
            if (rst != null) {
                while (rst.next()) {
                    val play = Play(
                        id = rst.getInt("play_id"),
                        dictID = rst.getInt("dict_type_id"),
                        dictLangId = rst.getInt("dict_lang_id"),
                        name =  rst.getString("play_name"),
                        introduction =  rst.getString("play_introduction"),
                        image = rst.getString("play_image"),
                        video = rst.getString("play_video"),
                        length = rst.getInt("play_length"),
                        ticketPrice = rst.getBigDecimal("play_ticket_price")
                    )
                    list.add(play)
                }
            }
            db.close(rst)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}