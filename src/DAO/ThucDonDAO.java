package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.LoaiThucDon;
import DTO.NhanVien;
import DTO.ThucDon;

public class ThucDonDAO {
private static ThucDonDAO instance;
    
    public ThucDonDAO() {
    }
    
    public static ThucDonDAO getInstance() {
        if (instance == null) {
            instance = new ThucDonDAO();
        }
        return instance;
    }
    
    public List<ThucDon> GetListThucDon() {
    	List<ThucDon> list = new ArrayList<ThucDon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ThucDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	ThucDon td = new ThucDon(rs.getString(1), rs.getInt(2), rs.getString(4), rs.getInt(3));
                list.add(td);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<ThucDon> TimKiemTheoTen(String name){
    	List<ThucDon> list = new ArrayList<ThucDon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ThucDon WHERE TenTD LIKE '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	ThucDon td = new ThucDon(rs.getString(1), rs.getInt(2), rs.getString(4), rs.getInt(3));
                list.add(td);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
