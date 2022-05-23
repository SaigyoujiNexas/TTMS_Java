@file:OptIn(ExperimentalStdlibApi::class)

package xupt.se.ttms.controller

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import org.json.JSONException
import xupt.se.ttms.entity.Play
import xupt.se.ttms.service.PlaySrv
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class PlayServlet: HttpServlet(){
    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        println("get Get Request")
        doPost(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        println("get Post Request")
        val type = request.getParameter("type")
        response.setHeader("Access-Control-Allow-Origin", "*")
        // 根据请求操作类型，执行相应的增、删、该、查
        if (type.equals("add", ignoreCase = true)) add(request, response) else if (type.equals(
                "delete",
                ignoreCase = true
            )
        ) delete(request, response) else if (type.equals("update", ignoreCase = true)) update(
            request,
            response
        ) else if (type.equals("search", ignoreCase = true)) search(request, response)
    }

    @Throws(ServletException::class, IOException::class)
    private fun add(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val play = Play(
                dictID = request.getParameter("dict_type_id").toInt(),
                dictLangId = request.getParameter("dict_lang_id").toInt(),
                name = request.getParameter("name"),
                introduction = request.getParameter("intro"),
                image = request.getParameter("image"),
                video = request.getParameter("video"),
                length = request.getParameter("length").toInt(),
                ticketPrice = request.getParameter("ticket_price").toBigDecimal()
            )
            response.contentType = "text/html;charset=utf-8"
            val out = response.writer
            if (PlaySrv().add(play) == 1) out.write("数据添加成功") else out.write("数据添加失败，请重试")
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
            response.contentType = "text/html;charset=utf-8"
            response.writer.write("操作错误，请重试")
        }
    }

    @Throws(ServletException::class, IOException::class)
    private fun delete(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val id = Integer.valueOf(request.getParameter("id"))
            response.contentType = "text/html;charset=utf-8"
            val out = response.writer
            out.write(PlaySrv().delete(id).toString())
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
            response.contentType = "text/html;charset=utf-8"
            response.writer.write("操作错误，请重试")
        }
    }

    @Throws(ServletException::class, IOException::class)
    private fun update(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val play = Play(
                id = request.getParameter("id").toInt(),
                dictID = request.getParameter("dict_type_id").toInt(),
                dictLangId = request.getParameter("dict_lang_id").toInt(),
                name = request.getParameter("name"),
                introduction = request.getParameter("intro"),
                image = request.getParameter("image"),
                video = request.getParameter("video"),
                length = request.getParameter("length").toInt(),
                ticketPrice = request.getParameter("ticket_price").toBigDecimal()
            )
            response.contentType = "text/html;charset=utf-8"
            val out = response.writer
            if (PlaySrv().modify(play) == 1) out.write("数据修改成功") else out.write("数据修改失败，请重试")
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
            response.contentType = "text/html;charset=utf-8"
            response.writer.write("操作错误，请重试")
        }
    }

    @Throws(ServletException::class, IOException::class)
    private fun search(request: HttpServletRequest, response: HttpServletResponse) {
        response.characterEncoding = "UTF-8"
        val out = response.writer
        val name = request.getParameter("name")
        var arr = ""
        val result: List<Play> =
            if (name != null && name.isNotEmpty())
                PlaySrv().Fetch(name) else PlaySrv().FetchAll()
        try {
            arr = Moshi.Builder().build().adapter<List<Play>>().toJson(result)
        } catch (e: JSONException) {
            e.printStackTrace()
        } finally {
            out.println(arr)
            out.flush()
            out.close()
        }
        print(arr)
    }

}