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
import DTO.TaiKhoan;

public class TaiKhoanDAO {
	private static TaiKhoanDAO instance;
	TaiKhoan tk = new TaiKhoan();
	
    public TaiKhoanDAO() {
    }

    public static TaiKhoanDAO getInstance() {
        if (instance == null) {
            instance = new TaiKhoanDAO();
        }
        return instance;
    }

    public static void setInstance(TaiKhoanDAO instance) {
    	TaiKhoanDAO.instance = instance;
    }
    
    public Boolean DangNhap(String taikhoan, String matkhau) {
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM TaiKhoan WHERE TenDN = ? AND MatKhau = ?");
            pstmt.setString(1, taikhoan);
            pstmt.setString(2, matkhau);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tk.setTenDN(rs.getString(1));
                tk.setMatKhau(rs.getString(2));
                tk.setQuyenDN(rs.getString(3));
                tk.setMaNV(rs.getInt(4));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public TaiKhoan GetTaiKhoan() {
        return tk;
    }

    public List<TaiKhoan> GetListTaiKhoan(){
        List<TaiKhoan> list = new ArrayList<TaiKhoan>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TaiKhoan");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(4));
                list.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<TaiKhoan> TimKiemTheoTen(String name){
    	List<TaiKhoan> list = new ArrayList<TaiKhoan>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TaiKhoan WHERE TenDN LIKE '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	TaiKhoan tk = new TaiKhoan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(4));
                list.add(tk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
