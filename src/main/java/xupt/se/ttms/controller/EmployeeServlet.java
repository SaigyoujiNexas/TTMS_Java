package xupt.se.ttms.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudioServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var type = req.getParameter("type");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        if(type.trim().equals("add"))
            add(req, resp);
        else if(type.trim().equals("delete")) delete(req, resp);
        else if(type.trim().equals("update")) update(req, resp);
        else if(type.trim().equals("search")) search(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
    }
}
