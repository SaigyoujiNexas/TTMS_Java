package xupt.se.ttms.dao

import xupt.se.ttms.entity.Play
import xupt.se.ttms.entity.PlayLang
import xupt.se.ttms.entity.PlayType
import xupt.se.ttms.idao.iPlayDAO
import xupt.se.util.DBUtil
import java.sql.ResultSet
import java.util.*

class PlayDAO : iPlayDAO {
    override fun insert(py: Play): Int {
        var result = 0
        try {
            val sql = ("insert into play(dict_type_id, dict_lang_id, play_name, play_introduction, play_image, " +
                    "play_video, play_length, play_ticket_price, play_status)"
                    + "values (${py.type.id}, ${py.lang.id}, '${py.name}', '${py.introduction}', '${py.image}', '${py.video}'," +
                    "${py.length}, ${py.ticketPrice}, 0")
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
                ("update play set dict_type_id = ${py.type.id}, dict_lang_id = ${py.lang.id}, play_name = '${py.name}', " +
                        "play_introduction = '${py.introduction}', play_image = '${py.image}', play_video = '${py.video}', play_video = '${py.video}'," +
                        "play_length = ${py.length}, play_ticket_price = ${py.ticketPrice} " +
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
            val sql = "update play set play_status = -1 where play_id = $id"
            val db = DBUtil()
            db.openConnection()
            result = db.execCommand(sql)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }


    fun fetchAll(): List<Play> {
        val db = DBUtil()
        var list = mutableListOf<Play>()
        try {
            val sql = "SELECT * from view_play"
            if (!db.openConnection()) {
                print("fail to connect database")
                return list.toList()
            }
            val rst = db.execQuery(sql)
            list += getPlayList(rst)
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
            val sql = "select * from view_play where play_name like '%$name%' "
            if (!db.openConnection()) {
                print("fail to connect database")
                return emptyList()
            }
            val rst = db.execQuery(sql)
            if (rst != null) {
                list += getPlayList(rst)
            }
            db.close(rst)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
    companion object {
        fun getPlayList(rst: ResultSet): List<Play> {
            val list = mutableListOf<Play>()
            while (rst.next()) {
                val play = generatePlay(rst)
                list.add(play)
            }
            return list
        }
        private fun generatePlay(rst: ResultSet) = Play(
            id = rst.getInt("play_id"),
            type = PlayType(id = rst.getInt("dict_type_id"), rst.getString("play_type")),
            lang = PlayLang(id = rst.getInt("dict_lang_id"), rst.getString("play_lang")),
            name = rst.getString("play_name"),
            introduction = rst.getString("play_introduction"),
            image = rst.getString("play_image"),
            video = rst.getString("play_video"),
            length = rst.getInt("play_length"),
            ticketPrice = rst.getBigDecimal("play_ticket_price"),
            status = rst.getShort("play_status")
        )

    }


}