package xupt.se.ttms.controller

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "StudioServlet", urlPatterns = ["/EmployeeServlet"])
class EmployeeServlet : HttpServlet() {
    @Throws(ServletException::class, IOException::class)
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        doPost(req, resp)
    }

    @Throws(ServletException::class, IOException::class)
    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val type = req.getParameter("type")
        resp.setHeader("Access-Control-Allow-Origin", "*")
        if (type.trim { it <= ' ' } == "add") add(req, resp) else if (type.trim { it <= ' ' } == "delete") delete(
            req,
            resp
        ) else if (type.trim { it <= ' ' } == "update") update(
            req,
            resp
        ) else if (type.trim { it <= ' ' } == "search") search(req, resp)
    }

    private fun search(req: HttpServletRequest, resp: HttpServletResponse) {}
    private fun update(req: HttpServletRequest, resp: HttpServletResponse) {}
    private fun delete(req: HttpServletRequest, resp: HttpServletResponse) {}
    private fun add(req: HttpServletRequest, resp: HttpServletResponse) {}
}