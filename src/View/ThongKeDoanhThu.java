package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;

import DAO.DatabaseConn;
import DAO.HoaDonDAO;
import DAO.TaiKhoanDAO;
import DAO.DoanhThuDAO;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThongKeDoanhThu extends JFrame {

	private JPanel contentPane;
	private JTextField txtDoanhThu;
	Connection conn = DatabaseConn.openConnection();
	private JTable tblDoanhThu;
	private ArrayList<ThongKeDoanhThu> list;
	DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
	public ThongKeDoanhThu() {
		setResizable(false);
		initComponents();
        this.setLocationRelativeTo(null);
        List<DTO.DoanhThu> list = DoanhThuDAO.getInstance().GetListThongKeDoanhThu();
        model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Ngày", "Doanh thu"
            });
        ShowTable();
        LoadTable();
	}
	
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<DTO.DoanhThu> list = DoanhThuDAO.getInstance().GetListThongKeDoanhThu();
        for (int i = 0; i < list.size(); i++) {
        	DTO.DoanhThu tkdt = list.get(i);
            Object[] dt = {tkdt.getNgay(), tkdt.getDoanhThu()};
            model.addRow(dt);
        }
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		List<DTO.DoanhThu> list = DoanhThuDAO.getInstance().GetListThongKeDoanhThu();
		for (DTO.DoanhThu tkdt : list) {
            model.addRow(new Object[]{
            		tkdt.getNgay(), tkdt.getDoanhThu()
            });
        }
	}
	public void ShowDetail() {
		List<DTO.DoanhThu> list = DoanhThuDAO.getInstance().GetListThongKeDoanhThu();
		int i = tblDoanhThu.getSelectedRow();
		DTO.DoanhThu dt = list.get(i);
		txtDoanhThu.setText(String.valueOf(dt.getDoanhThu()));
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKeDoanhThu frame = new ThongKeDoanhThu();
					frame.setLocationRelativeTo(null);
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\grow-up.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 538, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("DOANH THU");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(0, 0, 514, 59);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ng\u00E0y");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(40, 69, 45, 16);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Doanh thu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(39, 106, 74, 13);
		desktopPane.add(lblNewLabel_2);
		
		txtDoanhThu = new JTextField();
		txtDoanhThu.setBounds(123, 100, 307, 26);
		desktopPane.add(txtDoanhThu);
		txtDoanhThu.setColumns(10);
		
		JLabel lblTongDoanhThu = new JLabel("");
		lblTongDoanhThu.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTongDoanhThu.setBounds(186, 380, 228, 51);
		desktopPane.add(lblTongDoanhThu);
		
		JComboBox cbbNgay = new JComboBox();
		cbbNgay.setBounds(123, 63, 307, 26);
		desktopPane.add(cbbNgay);
		
		JButton btnNewButton = new JButton("T\u00EDnh");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\bills.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbbNgay.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Chưa thêm ngày!");
					return;
				}
				
				TinhDoanhThu();
			}

			private void TinhDoanhThu(){
				// TODO Auto-generated method stub
				try {
					String sql = "SELECT SUM(HoaDon.TongTien) AS DoanhThu FROM HoaDon WHERE HoaDon.NgayXuat=?";
		            PreparedStatement ps= conn.prepareStatement(sql);
		            ps.setString(1, (String) cbbNgay.getSelectedItem());
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                txtDoanhThu.setText(rs.getString("DoanhThu"));
		            }
		            
		        } catch (SQLException ex) {
		        	JOptionPane.showMessageDialog(null, "Lỗi tính doanh thu!");
		        }
			}
			
		});
		btnNewButton.setBounds(123, 128, 98, 45);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Tho\u00E1t");
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?","Exit!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					setVisible(false);
				}
			}
		});
		btnNewButton_3.setBounds(410, 386, 98, 45);
		desktopPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("TênĐN:");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setBounds(39, 522, 66, 16);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(112, 522, 98, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblNewLabel_4_2 = new JLabel("Quyền:");
		lblNewLabel_4_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_4_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4_2.setBounds(254, 522, 66, 16);
		desktopPane.add(lblNewLabel_4_2);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(321, 522, 74, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_5.setBounds(0, 517, 514, 26);
		desktopPane.add(lblNewLabel_5);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 195, 514, 184);
		desktopPane.add(scrollPane);
		
		tblDoanhThu = new JTable();
		tblDoanhThu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
		});
		scrollPane.setViewportView(tblDoanhThu);
		
		JLabel lblNewLabel_3 = new JLabel("TỔNG DOANH THU:");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_3.setBounds(6, 380, 193, 51);
		desktopPane.add(lblNewLabel_3);
		
		
		
		JLabel lblNewLabel_7 = new JLabel("Doanh thu:");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setBounds(6, 179, 314, 16);
		desktopPane.add(lblNewLabel_7);
		
		JButton btnNewButton_2 = new JButton("Thêm");
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO ThongKeDoanhThu(Ngay, DoanhThu) VALUES (?,?)");		            
		            ps.setString(1, (String) cbbNgay.getSelectedItem());
		            ps.setInt(2, Integer.parseInt(txtDoanhThu.getText()));    
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                TinhTongDoanhThu();
		                JOptionPane.showMessageDialog(null, "Thêm doanh thu thành công!");	
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Lỗi thêm doanh thu!");	
		        }	
			}

			private void TinhTongDoanhThu() {
				// TODO Auto-generated method stub
				try {
					String sql = "SELECT SUM(ThongKeDoanhThu.DoanhThu) AS TongDoanhThu FROM ThongKeDoanhThu";
		            PreparedStatement ps= conn.prepareStatement(sql);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                lblTongDoanhThu.setText(rs.getString("TongDoanhThu"));
		            }
		            
		        } catch (SQLException ex) {
		        	
		        }
			}
		});
		btnNewButton_2.setBounds(242, 128, 98, 45);
		desktopPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1_1 = new JButton("Xóa");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
		            PreparedStatement ps= conn.prepareStatement("DELETE FROM ThongKeDoanhThu WHERE Ngay=?");
		            ps.setString(1, (String) cbbNgay.getSelectedItem());
		            if (cbbNgay.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Nhập ngày muốn xóa!");
		                return;
		            }
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Xóa doanh thu thành công!");
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);		           
		        }
			}
		});
		btnNewButton_2_1_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnNewButton_2_1_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_2_1_1.setBounds(363, 128, 98, 45);
		desktopPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_4 = new JButton("Thống kê");
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\statistics (1).png"));
		btnNewButton_4.addActionListener(new ActionListener() {
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
		        DoanhThuTheoNgay bdtk = new DoanhThuTheoNgay();
				bdtk.setDataToChart1(chartPanel);
			}
		});
		btnNewButton_4.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnNewButton_4.setBounds(123, 443, 269, 67);
		desktopPane.add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCBbox();
			}

			private void getCBbox() {
				// TODO Auto-generated method stub
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT NgayXuat From HoaDon Group by NgayXuat");
					while(rs.next()) {
						String NgayXuat = rs.getString("NgayXuat");
						cbbNgay.addItem(NgayXuat);
					}
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\refresh.png"));
		btnNewButton_1.setBounds(428, 62, 45, 28);
		desktopPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_6.setBounds(0, 0, 514, 517);
		desktopPane.add(lblNewLabel_6);

		
		if (!TaiKhoanDAO.getInstance().DangNhap(lblTenDN.getText(),lblQuyen.getText())) {
			if(!TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN().equals("admin")) {
				btnNewButton_2_1_1.setVisible(false);
			}
		}
	}
}
