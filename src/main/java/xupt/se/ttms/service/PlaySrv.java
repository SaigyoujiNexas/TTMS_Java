package xupt.se.ttms.service;

import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.entity.Play;
import xupt.se.ttms.entity.Studio;
import xupt.se.ttms.idao.DAOFactory;

import java.util.List;

public class PlaySrv {
    private PlayDAO playDAO = DAOFactory.INSTANCE.getPlayDAO();

    public int add(Play stu){
        return playDAO.insert(stu);
    }

    public int modify(Play stu){
        return playDAO.update(stu);
    }

    public int delete(int ID){
        return playDAO.delete(ID);
    }

    public List<Play> Fetch(String name){
        return playDAO.select(name);
    }

    public List<Play> FetchAll(){
        System.out.println("service fetch all invoked");
        return playDAO.fetchAll();
    }
}
