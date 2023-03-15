package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseConn;
import DAO.NhanVienDAO;
import DAO.PhieuGoiMonDAO;
import DAO.TaiKhoanDAO;
import DAO.ThucDonDAO;
import DTO.BanAn;
import DTO.NhanVien;
import DTO.PhieuGoiMon;
import DTO.ThucDon;

import javax.swing.ImageIcon;
import java.awt.Color;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class QuanLyGoiMon extends JFrame {

	private JPanel contentPane;
	private JTable tblPhieuGoiMon;
	private ArrayList<PhieuGoiMon> list;
	DefaultTableModel model;
	Connection conn = DatabaseConn.openConnection();
	private JTextField txtSoLuong;
	private JTextField txtTimKiem;
	String strTimKiem = "";
	private JTextField txtNgayLapPhieu;
	
	public QuanLyGoiMon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\order-food.png"));
		setResizable(false);
		initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<PhieuGoiMon>) PhieuGoiMonDAO.getInstance().GetListPhieuGoiMon();
        model = (DefaultTableModel) tblPhieuGoiMon.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Số bàn", "Tên loại TD", "Tên thực đơn", "Số lượng", "Ngày lập phiếu"
            });
        ShowTable();
        LoadTable();
	}
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<PhieuGoiMon> list = PhieuGoiMonDAO.getInstance().GetListPhieuGoiMon();
        for (int i = 0; i < list.size(); i++) {
        	PhieuGoiMon pgm = list.get(i);
            Object[] dt = {pgm.getSoBan(), pgm.getTenLoaiTD(), pgm.getTenTD(), pgm.getSoLuong(), pgm.getNgayLapPhieu()};
            model.addRow(dt);
        }
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (PhieuGoiMon pgm : list) {
            model.addRow(new Object[]{
            	pgm.getSoBan(), pgm.getTenLoaiTD(), pgm.getTenTD(), pgm.getSoLuong(), pgm.getNgayLapPhieu()
            });
        }
	}
	
	public void ShowDetail() {
		int i = tblPhieuGoiMon.getSelectedRow();
		PhieuGoiMon pgm = list.get(i);
		txtSoLuong.setText(String.valueOf(pgm.getSoLuong()));
		txtNgayLapPhieu.setText(String.valueOf(pgm.getNgayLapPhieu()));
	}
	
	public void FillTable() {
		model.setRowCount(0);
		for (PhieuGoiMon pgm : PhieuGoiMonDAO.getInstance().TimKiemTheoTen(strTimKiem)) {
			Object[] dt = {pgm.getSoBan(), pgm.getTenLoaiTD(), pgm.getTenTD(), pgm.getSoLuong(), pgm.getNgayLapPhieu()};
            model.addRow(dt);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyGoiMon frame = new QuanLyGoiMon();
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
		setBounds(100, 100, 951, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setForeground(new Color(51, 204, 255));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("G\u1ECCI M\u00D3N");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(0, 0, 927, 53);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("S\u1ED1 b\u00E0n");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(5, 84, 45, 13);
		desktopPane.add(lblNewLabel_1);
		
		JComboBox cbbSoBan = new JComboBox();
		cbbSoBan.setBounds(62, 76, 73, 28);
		desktopPane.add(cbbSoBan);
		
		JLabel lblNewLabel_2 = new JLabel("Lo\u1EA1i th\u1EF1c \u0111\u01A1n");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(147, 84, 93, 13);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Th\u1EF1c \u0111\u01A1n");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(147, 124, 74, 13);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(6, 121, 56, 18);
		desktopPane.add(lblNewLabel_4);
		
		JComboBox cbbLoaiThucDon = new JComboBox();
		cbbLoaiThucDon.setBounds(234, 76, 151, 28);
		desktopPane.add(cbbLoaiThucDon);
		
		JComboBox cbbThucDon = new JComboBox();
		cbbThucDon.setBounds(234, 116, 151, 28);
		desktopPane.add(cbbThucDon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 105, 530, 317);
		desktopPane.add(scrollPane);
		
		tblPhieuGoiMon = new JTable();
		tblPhieuGoiMon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ShowDetail();
			}
		});
		tblPhieuGoiMon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblPhieuGoiMon);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cbbSoBan.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Chưa thêm bàn!");
		                return;
		            }
					if (cbbLoaiThucDon.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Chưa thêm loại thực đơn!");
		                return;
		            }
					if (cbbThucDon.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Chưa thêm thực đơn!");
		                return;
		            }
		            if (txtSoLuong.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập số lượng!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO PhieuGoiMon(SoBan, TenLoaiTD, TenTD, SoLuong, NgayLapPhieu) VALUES (?,?,?,?,?)");		            
		            ps.setString(1, (String) cbbSoBan.getSelectedItem());
		            ps.setString(2, (String) cbbLoaiThucDon.getSelectedItem());    
		            ps.setString(3, (String) cbbThucDon.getSelectedItem());	
		            ps.setInt(4, Integer.parseInt(txtSoLuong.getText()));
		            ps.setString(5, txtNgayLapPhieu.getText());
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Thêm phiếu gọi món thành công!");	
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(PhieuGoiMonDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Số phiếu đã tồn tại!");	
		        }	
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(5, 203, 92, 42);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("X\u00F3a");
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            PreparedStatement ps= conn.prepareStatement("DELETE FROM PhieuGoiMon WHERE SoBan=?");
		            ps.setString(1, (String) cbbSoBan.getSelectedItem());
		            if (cbbSoBan.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Nhập số bàn để xóa!");
		                return;
		            }
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Xóa phiếu gọi món thành công!");	
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(PhieuGoiMonDAO.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(147, 203, 92, 42);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Tho\u00E1t");
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
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove.png"));
		btnNewButton_4.setForeground(Color.RED);
		btnNewButton_4.setBounds(829, 434, 92, 46);
		desktopPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Thanh to\u00E1n");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemHoaDon().setVisible(true);
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\credit-card.png"));
		btnNewButton_5.setForeground(new Color(0, 102, 255));
		btnNewButton_5.setBounds(10, 332, 364, 76);
		desktopPane.add(btnNewButton_5);
		
		JLabel lblNewLabel_6 = new JLabel("Danh s\u00E1ch phi\u1EBFu g\u1ECDi m\u00F3n:");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setBounds(397, 79, 156, 20);
		desktopPane.add(lblNewLabel_6);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSoBan();
				getLoaiThucDon();
				getThucDon();				
			}

			private void getThucDon() {
				// TODO Auto-generated method stub
				try {
					String sql = "SELECT TenTD FROM LoaiThucDon,ThucDon WHERE LoaiThucDon.TenLoaiTD = ? AND ThucDon.TenLoaiTD = LoaiThucDon.TenLoaiTD";
		            PreparedStatement ps= conn.prepareStatement(sql);
		            ps.setString(1, (String) cbbLoaiThucDon.getSelectedItem());
		            ResultSet rs = ps.executeQuery();
		            cbbThucDon.removeAllItems();
		            while (rs.next()) {
		                cbbThucDon.addItem(rs.getString("TenTD"));
		            }
		            
		        } catch (SQLException ex) {
		        	
		        }
			}

			private void getLoaiThucDon() {
				// TODO Auto-generated method stub
				try {
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT TenLoaiTD FROM LoaiThucDon");
					while(rs.next()) {					
						String TenLoaiTD = rs.getString("TenLoaiTD");
						cbbLoaiThucDon.addItem(TenLoaiTD);						
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
						String SoBan = rs.getString("SoBan");
						cbbSoBan.addItem(SoBan);
					}
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\refresh.png"));
		btnNewButton_3.setBounds(234, 156, 45, 28);
		desktopPane.add(btnNewButton_3);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(62, 116, 63, 28);
		desktopPane.add(txtSoLuong);
		txtSoLuong.setColumns(10);
		
		JButton btnNewButton_2_1 = new JButton("Hủy");
		btnNewButton_2_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSoLuong.setText("");	
				txtTimKiem.setText("");	
			}
		});
		btnNewButton_2_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Huy.png"));
		btnNewButton_2_1.setForeground(Color.BLACK);
		btnNewButton_2_1.setBounds(293, 261, 92, 42);
		desktopPane.add(btnNewButton_2_1);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(649, 76, 112, 28);
		desktopPane.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strTimKiem = txtTimKiem.getText();
				FillTable();
			}
		});
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\search.png"));
		btnTimKiem.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnTimKiem.setBounds(761, 76, 98, 28);
		desktopPane.add(btnTimKiem);
		
		JLabel lblNewLabel_8 = new JLabel("Tên ĐN:");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_8.setBounds(25, 449, 66, 16);
		desktopPane.add(lblNewLabel_8);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(95, 449, 126, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblNewLabel_10 = new JLabel("Quyền:");
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_10.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_10.setBounds(265, 449, 73, 16);
		desktopPane.add(lblNewLabel_10);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(335, 449, 134, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_12.setBounds(0, 428, 927, 52);
		desktopPane.add(lblNewLabel_12);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		
		txtNgayLapPhieu = new JTextField();
		txtNgayLapPhieu.setBounds(91, 156, 112, 28);
		desktopPane.add(txtNgayLapPhieu);
		txtNgayLapPhieu.setColumns(10);
		
		txtNgayLapPhieu.setEditable(true);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");      
		txtNgayLapPhieu.setText(sdf.format(date)); 
		
		JLabel lblNewLabel_7 = new JLabel("Ngày lập phiếu");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7.setBounds(5, 162, 93, 16);
		desktopPane.add(lblNewLabel_7);
		
		JButton btnNewButton_6 = new JButton("Thực đơn bán nhiều nhất");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKeThucDon tdt = new ThongKeThucDon();
				tdt.setLocationRelativeTo(null);
				tdt.setVisible(true);
			}
		});
		btnNewButton_6.setForeground(Color.BLACK);
		btnNewButton_6.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_6.setBounds(0, 261, 240, 42);
		desktopPane.add(btnNewButton_6);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_9.setBounds(0, 0, 927, 429);
		desktopPane.add(lblNewLabel_9);
	}
}
