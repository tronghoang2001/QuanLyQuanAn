package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseConn;
import DAO.HoaDonDAO;
import DAO.PhieuGoiMonDAO;
import DAO.TaiKhoanDAO;
import DAO.ThucDonDAO;
import DTO.HoaDon;
import DTO.PhieuGoiMon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class ThemHoaDon extends JFrame {

	private JPanel contentPane;
	private JTable tblHoaDon;
	private JTextField txtDate;
	private JTextField txtMaHD;
	private JTextField txtTongTien;
	private ArrayList<HoaDon> list;
	DefaultTableModel model;
	Connection conn = DatabaseConn.openConnection();
	/**
	 * Launch the application.
	 */
	
	public ThemHoaDon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\bill.png"));
		setResizable(false);
		initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<HoaDon>) HoaDonDAO.getInstance().GetListHoaDon();
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Mã hóa đơn", "Số bàn", "Tổng tiền", "Ngày xuất", "Mã NV"
            });
        ShowTable();
        LoadTable();
	}
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<HoaDon> list = HoaDonDAO.getInstance().GetListHoaDon();
        for (int i = 0; i < list.size(); i++) {
        	HoaDon hd = list.get(i);
            Object[] dt = {hd.getMaHD(), hd.getSoBan(), hd.getTongTien(), hd.getNgayXuat(), hd.getMaNV()};
            model.addRow(dt);
        }
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (HoaDon hd : list) {
            model.addRow(new Object[]{
            		hd.getMaHD(), hd.getSoBan(), hd.getTongTien(), hd.getNgayXuat(), hd.getMaNV()
            });
        }
	}
	
	public void ShowDetail() {
		int i = tblHoaDon.getSelectedRow();
		HoaDon hd = list.get(i);
		txtMaHD.setText(String.valueOf(hd.getMaHD()));
		txtTongTien.setText(String.valueOf(hd.getTongTien()));
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
					ThemHoaDon frame = new ThemHoaDon();
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
		setBounds(100, 100, 712, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("H\u00D3A \u0110\u01A0N");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(-2, 0, 690, 62);
		desktopPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(254, 74, 434, 265);
		desktopPane.add(scrollPane);
		
		tblHoaDon = new JTable();
		tblHoaDon.setForeground(Color.BLACK);
		tblHoaDon.setBackground(Color.WHITE);
		tblHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ShowDetail();
			}
		});
		tblHoaDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblHoaDon);
		
		JLabel lblNewLabel_1 = new JLabel("Số bàn");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(12, 126, 101, 13);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("M\u00E3 h\u00F3a \u0111\u01A1n");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(12, 92, 85, 13);
		desktopPane.add(lblNewLabel_2);
		
		txtDate = new JTextField();
		txtDate.setForeground(Color.BLACK);
		txtDate.setBounds(121, 233, 113, 28);
		desktopPane.add(txtDate);
		txtDate.setColumns(10);
		
		txtDate.setEditable(true);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");      
		txtDate.setText(sdf.format(date)); 
		
		JLabel lblNewLabel_3 = new JLabel("Ngày thêm hóa đơn\r\n");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(12, 241, 126, 13);
		desktopPane.add(lblNewLabel_3);
		
		JComboBox cbbSoBan = new JComboBox();
		cbbSoBan.setBounds(86, 118, 148, 28);
		desktopPane.add(cbbSoBan);
		
		JComboBox cbbNhanVien = new JComboBox();
		cbbNhanVien.setBounds(121, 274, 113, 28);
		desktopPane.add(cbbNhanVien);
		
		txtMaHD = new JTextField();
		txtMaHD.setBackground(Color.WHITE);
		txtMaHD.setBounds(86, 84, 148, 28);
		desktopPane.add(txtMaHD);
		txtMaHD.setColumns(10);
		
		JButton btnNewButton = new JButton("Tính doanh thu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeDoanhThu tdt = new ThongKeDoanhThu();
				tdt.setLocationRelativeTo(null);
				tdt.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\invoice (1).png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(6, 339, 253, 55);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Thêm");
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtMaHD.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập mã hóa đơn!");
		                return;
		            }
					if (cbbSoBan.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Chưa thêm bàn!");
		                return;
		            }
					if (txtTongTien.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa tính tiền!");
		                return;
		            }
					if (cbbNhanVien.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Chưa thêm nhân viên!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO HoaDon(MaHD, SoBan, TongTien, NgayXuat, MaNV) VALUES (?,?,?,?,?)");		            
		            ps.setString(1, txtMaHD.getText());
		            ps.setString(2, (String) cbbSoBan.getSelectedItem());
		            ps.setString(3, txtTongTien.getText());    
		            ps.setString(4, txtDate.getText());
		            ps.setString(5, (String) cbbNhanVien.getSelectedItem());
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!");	
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Mã hóa đơn đã tồn tại!");	
		        }	
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		btnNewButton_1.setBounds(388, 349, 98, 45);
		desktopPane.add(btnNewButton_1);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(102, 412, 98, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblQuyen = new JLabel("");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(353, 412, 101, 16);
		desktopPane.add(lblQuyen);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
		            PreparedStatement ps= conn.prepareStatement("DELETE FROM HoaDon WHERE MaHD=?");
		            ps.setString(1, txtMaHD.getText());
		            if (txtMaHD.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Nhập mã hóa đơn muốn xóa!");
		                return;
		            }
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công!");	
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnNewButton_2.setBounds(491, 349, 95, 45);
		desktopPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Thoát");
		btnNewButton_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?","Exit!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					setVisible(false);
				}
			}
		});
		btnNewButton_4.setForeground(Color.RED);
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove.png"));
		btnNewButton_4.setBounds(593, 349, 95, 45);
		desktopPane.add(btnNewButton_4);
		
		JLabel lblDanhSchHa = new JLabel("Danh sách hóa đơn:");
		lblDanhSchHa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDanhSchHa.setForeground(Color.BLACK);
		lblDanhSchHa.setBounds(254, 57, 277, 13);
		desktopPane.add(lblDanhSchHa);
		
		JLabel lblNewLabel_4 = new JLabel("Tổng tiền");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(12, 160, 82, 13);
		desktopPane.add(lblNewLabel_4);
		
		txtTongTien = new JTextField();
		txtTongTien.setBounds(86, 152, 148, 28);
		desktopPane.add(txtTongTien);
		txtTongTien.setColumns(10);
		
		JButton btnTinhTien = new JButton("Tính tiền");
		btnTinhTien.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnTinhTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbbSoBan.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Chưa thêm bàn!");
					return;
				}
				
				TinhTien();

			}


			private void TinhTien() {
				// TODO Auto-generated method stub
				try {
					String sql = "SELECT SUM(ThucDon.GiaTien*PhieuGoiMon.SoLuong) AS TongTien FROM ThucDon,PhieuGoiMon WHERE PhieuGoiMon.SoBan = ? AND ThucDon.TenTD=PhieuGoiMon.TenTD";
		            PreparedStatement ps= conn.prepareStatement(sql);
		            ps.setString(1, (String) cbbSoBan.getSelectedItem());
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                txtTongTien.setText(rs.getString("TongTien"));
		            }
		            
		        } catch (SQLException ex) {
		        	JOptionPane.showMessageDialog(null, "Lỗi tính tiền!");
		        }
			}
		});
		btnTinhTien.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\bills.png"));
		btnTinhTien.setBounds(121, 186, 113, 34);
		desktopPane.add(btnTinhTien);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("Mã nhân viên");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBounds(12, 282, 81, 13);
		desktopPane.add(lblNewLabel_5);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSoBan();
				getMaNV();
			}

			private void getMaNV() {
				// TODO Auto-generated method stub
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT MaNV FROM NhanVien");
					while(rs.next()) {
						String MaNV = rs.getString("MaNV");
						cbbNhanVien.addItem(MaNV);
					}
				}catch(Exception e) {
					
				}
			}

			private void getSoBan() {
				// TODO Auto-generated method stub
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT SoBan FROM BanAn");
					while(rs.next()) {
						String Soban = rs.getString("Soban");
						cbbSoBan.addItem(Soban);
					}
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\refresh.png"));
		btnNewButton_3.setBounds(195, 304, 39, 31);
		desktopPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_6.setBounds(-2, 0, 690, 399);
		desktopPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("TênĐN:");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7.setBounds(32, 412, 65, 16);
		desktopPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_2 = new JLabel("Quyền:");
		lblNewLabel_7_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_7_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7_2.setBounds(283, 412, 65, 16);
		desktopPane.add(lblNewLabel_7_2);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_8.setBounds(0, 398, 688, 45);
		desktopPane.add(lblNewLabel_8);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		if (!TaiKhoanDAO.getInstance().DangNhap(lblTenDN.getText(),lblQuyen.getText())) {
			if(!TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN().equals("admin")) {
				btnNewButton_2.setVisible(false);
			}
		}
	}
}
