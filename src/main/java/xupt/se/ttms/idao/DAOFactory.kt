package xupt.se.ttms.idao;

import xupt.se.ttms.dao.StudioDAO;

public class DAOFactory
{
    private static StudioDAO stuDao;

    public static synchronized StudioDAO creatStudioDAO()
    {
        if(null == stuDao)
            stuDao=new StudioDAO();
        return stuDao;
    }
}
