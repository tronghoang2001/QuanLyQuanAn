package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConn {
	
	public static Connection openConnection() {
		Connection conn = null;
			try {
				
				String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYQUANAN;user=sa;password=sa";
				conn = DriverManager.getConnection(dbURL);
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ""+ex);
			}
			return conn;
	}
	
	public static void main(String[] args) {
		openConnection();
	}
}

