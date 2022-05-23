package xupt.se.ttms.service;

import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.entity.Studio;
import xupt.se.ttms.idao.DAOFactory;

import java.util.List;

public class EmployeeSrv {
    private StudioDAO stuDAO= DAOFactory.INSTANCE.getStuDao();


    public int add(Studio stu){
        return stuDAO.insert(stu);
    }

    public int modify(Studio stu){
        return stuDAO.update(stu);
    }

    public int delete(int ID){
        return stuDAO.delete(ID);
    }

    public List<Studio> Fetch(String condt){
        return stuDAO.select(condt);
    }

    public List<Studio> FetchAll(){
        System.out.println("service fetch all invoked");
        return stuDAO.fetchAll();
    }
}
