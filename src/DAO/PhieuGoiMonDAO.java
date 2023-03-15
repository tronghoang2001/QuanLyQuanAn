package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.NhanVien;
import DTO.PhieuGoiMon;

public class PhieuGoiMonDAO {
	private static PhieuGoiMonDAO instance;

    public PhieuGoiMonDAO() {
    }

    public static PhieuGoiMonDAO getInstance() {
        if (instance == null) {
            instance = new PhieuGoiMonDAO();
        }
        return instance;
    }

    public static void setInstance(PhieuGoiMonDAO instance) {
    	PhieuGoiMonDAO.instance = instance;
    }

    public List<PhieuGoiMon> GetListPhieuGoiMon(){
        List<PhieuGoiMon> list = new ArrayList<PhieuGoiMon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PhieuGoiMon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	PhieuGoiMon pgm = new PhieuGoiMon(rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(pgm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGoiMonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<PhieuGoiMon> TimKiemTheoTen(String name){
    	List<PhieuGoiMon> list = new ArrayList<PhieuGoiMon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PhieuGoiMon WHERE SoBan LIKE '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	PhieuGoiMon pgm = new PhieuGoiMon(rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(pgm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGoiMonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
