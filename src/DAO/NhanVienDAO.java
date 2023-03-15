package DAO;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAO.DatabaseConn;

import DTO.NhanVien;
public class NhanVienDAO {
private static NhanVienDAO instance;
    
    public NhanVienDAO() {
    }
    
    public static NhanVienDAO getInstance() {
        if (instance == null) {
            instance = new NhanVienDAO();
        }
        return instance;
    }
    
    public List<NhanVien> GetListNhanVien() {
        List<NhanVien> list = new ArrayList<NhanVien>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM NhanVien");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<NhanVien> TimKiemTheoTen(String name){
    	List<NhanVien> list = new ArrayList<NhanVien>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM NhanVien WHERE TenNV LIKE '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                list.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
