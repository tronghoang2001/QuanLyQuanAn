package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.DoanhThu;

public class DoanhThuDAO {
	private static DoanhThuDAO instance;

    public DoanhThuDAO() {
    }

    
    public static void setInstance(DoanhThuDAO instance) {
    	DoanhThuDAO.instance = instance;
    }

    public List<DoanhThu> GetListThongKeDoanhThu(){
        List<DoanhThu> list = new ArrayList<DoanhThu>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ThongKeDoanhThu");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	DoanhThu tkdt = new DoanhThu(rs.getString(1), rs.getInt(2));
                list.add(tkdt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

	public static DoanhThuDAO getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {
            instance = new DoanhThuDAO();
        }
        return instance;
	}
}
