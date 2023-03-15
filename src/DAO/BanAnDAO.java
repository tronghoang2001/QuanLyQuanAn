package DAO;

import DAO.DatabaseConn;
import DTO.BanAn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BanAnDAO {
	 private static BanAnDAO instance;
	    
	    public BanAnDAO() {
	    }
	    
	    public static BanAnDAO getInstance() {
	        if (instance == null) {
	            instance = new BanAnDAO();
	        }
	        return instance;
	    }
	    
	    public List<BanAn> GetListBanAn() {
	        List<BanAn> list = new ArrayList<BanAn>();
	        Connection conn = DatabaseConn.openConnection();
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM BanAn");
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	BanAn ba = new BanAn(rs.getString(1), rs.getInt(2));
	                list.add(ba);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(BanAnDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return list;
	    }
}
