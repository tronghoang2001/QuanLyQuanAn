package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.ThongKeThucDon;

public class ThongKeThucDonDAO {
	private static ThongKeThucDonDAO instance;

    public ThongKeThucDonDAO() {
    }

    
    public static void setInstance(ThongKeThucDonDAO instance) {
    	ThongKeThucDonDAO.instance = instance;
    }

    public List<ThongKeThucDon> GetListThongKeThucDon(){
        List<ThongKeThucDon> list = new ArrayList<ThongKeThucDon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ThongKeThucDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	ThongKeThucDon tktd = new ThongKeThucDon(rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(tktd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

	public static ThongKeThucDonDAO getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {
            instance = new ThongKeThucDonDAO();
        }
        return instance;
	}
}
