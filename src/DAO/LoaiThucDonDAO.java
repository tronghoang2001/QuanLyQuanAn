package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.BanAn;
import DAO.DatabaseConn;
import DTO.LoaiThucDon;
public class LoaiThucDonDAO {
	private static LoaiThucDonDAO instance;
    LoaiThucDon ltd = new LoaiThucDon();
    public LoaiThucDonDAO() {
    }
    
    public LoaiThucDon getLtd() {
		return ltd;
	}

	public void setLtd(LoaiThucDon ltd) {
		this.ltd = ltd;
	}

	public static LoaiThucDonDAO getInstance() {
        if (instance == null) {
            instance = new LoaiThucDonDAO();
        }
        return instance;
    }
    
    public List<LoaiThucDon> GetListLoaiThucDon() {
        List<LoaiThucDon> list = new ArrayList<LoaiThucDon>();
        Connection conn = DatabaseConn.openConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM LoaiThucDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	LoaiThucDon ltd = new LoaiThucDon(rs.getString(1), rs.getInt(2));
                list.add(ltd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
