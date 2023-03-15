package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;

import DAO.DatabaseConn;
import DAO.DoanhThuDAO;
import DAO.HoaDonDAO;
import DAO.ThongKeThucDonDAO;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;

public class ThongKeThucDon extends JFrame {

	private JPanel contentPane;
	private JTextField txtThucDon;
	Connection conn = DatabaseConn.openConnection();
	private ArrayList<ThongKeDoanhThu> list;
	DefaultTableModel model;
	private JTable tblThucDon;
	private JTextField txtSoLuong;
	
	public ThongKeThucDon() {
		setResizable(false);
		initComponents();
        this.setLocationRelativeTo(null);
        List<DTO.ThongKeThucDon> list = ThongKeThucDonDAO.getInstance().GetListThongKeThucDon();
        model = (DefaultTableModel) tblThucDon.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Ngày", "Thực đơn", "Số lượng"
            });
        ShowTable();
        LoadTable();
	}
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<DTO.ThongKeThucDon> list = ThongKeThucDonDAO.getInstance().GetListThongKeThucDon();
        for (int i = 0; i < list.size(); i++) {
        	DTO.ThongKeThucDon tktd = list.get(i);
            Object[] dt = {tktd.getNgay(), tktd.getTenTD(), tktd.getSoLuong()};
            model.addRow(dt);
        }
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		List<DTO.ThongKeThucDon> list = ThongKeThucDonDAO.getInstance().GetListThongKeThucDon();
		for (DTO.ThongKeThucDon tktd : list) {
            model.addRow(new Object[]{
            		tktd.getNgay(), tktd.getTenTD(), tktd.getSoLuong()
            });
        }
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeThucDon frame = new ThongKeThucDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 630, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("TH\u1EF0C \u0110\u01A0N B\u00C1N NHI\u1EC0U NH\u1EA4T");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(0, 0, 606, 61);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Ng\u00E0y");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(92, 61, 45, 18);
		desktopPane.add(lblNewLabel_2);
		
		JComboBox cbbNgay = new JComboBox();
		cbbNgay.setBounds(218, 55, 252, 31);
		desktopPane.add(cbbNgay);
		
		JLabel lblNewLabel_3 = new JLabel("T\u00EAn th\u1EF1c \u0111\u01A1n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(92, 106, 94, 17);
		desktopPane.add(lblNewLabel_3);
		
		txtThucDon = new JTextField();
		txtThucDon.setBounds(218, 100, 252, 29);
		desktopPane.add(txtThucDon);
		txtThucDon.setColumns(10);
		
		JButton btnTim = new JButton("T\u00ECm");
		btnTim.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\search.png"));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbbNgay.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Chưa thêm ngày!");
					return;
				}
				
				TimThucDon();
			}

			private void TimThucDon() {
				// TODO Auto-generated method stub
				try {
					String sql = "SELECT TOP(2) TenTD,SUM(SoLuong) AS SoLuong FROM PhieuGoiMon WHERE NgayLapPhieu = ? Group by TenTD ORDER BY SoLuong DESC ";
		            PreparedStatement ps= conn.prepareStatement(sql);
		            ps.setString(1, (String) cbbNgay.getSelectedItem());
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                txtThucDon.setText(rs.getString("TenTD"));
		                txtSoLuong.setText(rs.getString("SoLuong"));
		            }
		            
		        } catch (SQLException ex) {
		        	JOptionPane.showMessageDialog(null, "Lỗi tính doanh thu!");
		        }
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setBounds(218, 182, 90, 31);
		desktopPane.add(btnTim);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\add.png"));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO ThongKeThucDon(Ngay, TenTD, SoLuong) VALUES (?,?,?)");		            
		            ps.setString(1, (String) cbbNgay.getSelectedItem());
		            ps.setString(2, txtThucDon.getText());    
		            ps.setInt(3, Integer.parseInt(txtSoLuong.getText()));  
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Thêm thành công!");	
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(ThongKeThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Lỗi!");	
		        }	
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setBounds(320, 182, 92, 31);
		desktopPane.add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            PreparedStatement ps= conn.prepareStatement("DELETE FROM ThongKeThucDon WHERE Ngay=?");
		            ps.setString(1, (String) cbbNgay.getSelectedItem());
		            if (cbbNgay.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Nhập ngày muốn xóa!");
		                return;
		            }
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Xóa thành công!");
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(ThongKeThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);		           
		        }
			}
		});
		btnXoa.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\delete.png"));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setBounds(424, 182, 90, 31);
		desktopPane.add(btnXoa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 221, 586, 213);
		desktopPane.add(scrollPane);
		
		tblThucDon = new JTable();
		scrollPane.setViewportView(tblThucDon);
		
		JLabel lblNewLabel_1 = new JLabel("Số lượng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(92, 149, 85, 13);
		desktopPane.add(lblNewLabel_1);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(218, 141, 50, 29);
		desktopPane.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\refresh.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCBbox();
			}

			private void getCBbox() {
				// TODO Auto-generated method stub
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT NgayLapPhieu FROM PhieuGoiMon GROUP BY NgayLapPhieu");
					while(rs.next()) {
						String NgayLapPhieu = rs.getString("NgayLapPhieu");
						cbbNgay.addItem(NgayLapPhieu);
					}
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton.setBounds(476, 55, 38, 31);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("THỐNG KÊ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartPanel chartPanel = new ChartPanel(null);
		        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		        JFrame frame = new JFrame();
		        frame.getContentPane().add(chartPanel);
		        frame.setTitle("Biểu đồ BarChart");
		        frame.setSize(1200, 800);
		        frame.setLocationRelativeTo(null);
		        frame.setResizable(false);
		        frame.setVisible(true);
		        ThucDonBanNhieuNhat bdtk = new ThucDonBanNhieuNhat();
		        bdtk.setDataToChart1(chartPanel);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 20));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\statistics (1).png"));
		btnNewButton_1.setBounds(213, 446, 184, 39);
		desktopPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_4.setBounds(0, 0, 606, 497);
		desktopPane.add(lblNewLabel_4);
	}
}
