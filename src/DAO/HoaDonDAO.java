package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.HoaDon;

public class HoaDonDAO {
	private static HoaDonDAO instance;

    public HoaDonDAO() {
    }

    public static HoaDonDAO getInstance() {
        if (instance == null) {
            instance = new HoaDonDAO();
        }
        return instance;
    }

    public static void setInstance(HoaDonDAO instance) {
    	HoaDonDAO.instance = instance;
    }

    public List<HoaDon> GetListHoaDon(){
        List<HoaDon> list = new ArrayList<HoaDon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM HoaDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	HoaDon hd = new HoaDon(rs.getString(3), rs.getString(5), rs.getString(2), rs.getInt(1), rs.getInt(4));
                list.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
