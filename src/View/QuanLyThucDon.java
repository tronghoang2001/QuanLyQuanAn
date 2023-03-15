package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseConn;
import DAO.LoaiThucDonDAO;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DAO.ThucDonDAO;
import DTO.LoaiThucDon;
import DTO.NhanVien;
import DTO.ThucDon;

import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class QuanLyThucDon extends JFrame {

	private JPanel contentPane;
	private JTable tblThucDon;
	private JTextField txtTenThucDon;
	private JTextField txtGiaTien;
	private JComboBox cbbLoaiThucDon;
	private ArrayList<ThucDon> list;
	DefaultTableModel model;
	Connection conn = DatabaseConn.openConnection();
	private JTextField txtTimKiem;
	String strTimKiem = "";
	int idSave = -1;
	
	public QuanLyThucDon() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\menu (2).png"));
        initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<ThucDon>) ThucDonDAO.getInstance().GetListThucDon();
        model = (DefaultTableModel) tblThucDon.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Tên thực đơn", "Giá tiền", "Tên loại TĐ"
            });
        ShowTable();
        LoadTable();
    }
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<ThucDon> list = ThucDonDAO.getInstance().GetListThucDon();
        for (int i = 0; i < list.size(); i++) {
        	ThucDon td = list.get(i);
            Object[] dt = {td.getTenTD(), td.getGiaTien(), td.getTenLoaiTD()};
            model.addRow(dt);
        }
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (ThucDon td : list) {
            model.addRow(new Object[]{
            	td.getTenTD(), td.getGiaTien(), td.getTenLoaiTD()
            });
        }
	}
	
	public void ShowDetail() {
		int i = tblThucDon.getSelectedRow();
		ThucDon td = list.get(i);
		idSave = list.get(i).getMaTD();
		txtTenThucDon.setText(String.valueOf(td.getTenTD()));
		txtGiaTien.setText(String.valueOf(td.getGiaTien()));
	}
	
	public void FillTable() {
		model.setRowCount(0);
		for (ThucDon td : ThucDonDAO.getInstance().TimKiemTheoTen(strTimKiem)) {
			Object[] dt = {td.getTenTD(), td.getGiaTien(), td.getTenLoaiTD()};
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
					QuanLyThucDon frame = new QuanLyThucDon();
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
		setBounds(100, 100, 706, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 255, 153));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("TH\u1EF0C \u0110\u01A0N");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 682, 59);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00EAn th\u1EF1c \u0111\u01A1n");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(10, 69, 84, 13);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gi\u00E1 ti\u1EC1n");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(340, 69, 45, 13);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Lo\u1EA1i th\u1EF1c \u0111\u01A1n");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(340, 97, 84, 13);
		desktopPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 206, 682, 262);
		desktopPane.add(scrollPane);
		
		tblThucDon = new JTable();
		tblThucDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
		});
		tblThucDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblThucDon);
		
		txtTenThucDon = new JTextField();
		txtTenThucDon.setBounds(104, 61, 180, 28);
		desktopPane.add(txtTenThucDon);
		txtTenThucDon.setColumns(10);
		
		txtGiaTien = new JTextField();
		txtGiaTien.setBounds(432, 61, 189, 28);
		desktopPane.add(txtGiaTien);
		txtGiaTien.setColumns(10);
		
		JComboBox cbbLoaiThucDon = new JComboBox();
		cbbLoaiThucDon.setBounds(432, 88, 189, 28);
		desktopPane.add(cbbLoaiThucDon);
		
		JButton btnThem = new JButton("Th\u00EAm\r\n");
		btnThem.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnThem.setForeground(Color.BLACK);
		btnThem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            if (txtTenThucDon.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập tên thực đơn!");
		                return;
		            }
		            if (txtGiaTien.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập giá tiền!");
		                return;
		            }
		            if (cbbLoaiThucDon.getSelectedItem()==null) {
		                JOptionPane.showMessageDialog(null, "Chưa thêm loại thực đơn!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO ThucDon(TenTD, GiaTien, TenLoaiTD) VALUES (?,?,?)");		            
		            ps.setString(1, txtTenThucDon.getText());    
		            ps.setInt(2, Integer.parseInt(txtGiaTien.getText()));	
		            ps.setString(3, (String) cbbLoaiThucDon.getSelectedItem());
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Thêm thực đơn thành công!");
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(ThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Tên thực đơn đã tồn tại!");
		        }	
			}

		});
		btnThem.setBounds(10, 115, 97, 44);
		desktopPane.add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idSave >= 0) {
					try {
			            PreparedStatement ps= conn.prepareStatement("DELETE FROM ThucDon WHERE MaTD=?");
			            ps.setInt(1, idSave);
			            int i = ps.executeUpdate();
			            if (i > 0) {
			                LoadTable();
			                JOptionPane.showMessageDialog(null, "Xóa thực đơn thành công!");
			            }
			        } catch (SQLException ex) {
			            Logger.getLogger(ThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
			            JOptionPane.showMessageDialog(null, "Thực đơn tồn tại ở bảng khác!");
			        }
				}
			}
		});
		btnXoa.setBounds(117, 115, 97, 44);
		desktopPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSua.setForeground(Color.BLACK);
		btnSua.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\fix.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idSave >= 0) {
					try {
						if (txtTenThucDon.getText().isEmpty()) {
			                JOptionPane.showMessageDialog(null, "Nhập tên thực đơn!");
			                return;
			            }
						if (cbbLoaiThucDon.getSelectedItem()==null) {
							JOptionPane.showMessageDialog(null, "Chưa thêm loại thực đơn!");
			                return;
						}
			            PreparedStatement ps = conn.prepareStatement("UPDATE ThucDon SET TenTD=?, GiaTien=?, TenLoaiTD=? WHERE MaTD=?");
			            ps.setString(1, txtTenThucDon.getText());
			            ps.setDouble(2, Integer.parseInt(txtGiaTien.getText()));
			            ps.setString(3, (String) cbbLoaiThucDon.getSelectedItem());
			            ps.setInt(4, idSave);
			            int i = ps.executeUpdate();
			            if (i > 0) {
			                LoadTable();
			                JOptionPane.showMessageDialog(null, "Cập nhật thực đơn thành công!");
			            }
			        } catch (SQLException ex) {
			            Logger.getLogger(ThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
			        }
				}
			}
		});
		btnSua.setBounds(224, 115, 97, 44);
		desktopPane.add(btnSua);
		
		JButton btnHuy = new JButton("H\u1EE7y");
		btnHuy.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnHuy.setForeground(Color.BLACK);
		btnHuy.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Huy.png"));
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenThucDon.setText("");
				txtGiaTien.setText("");
				txtTimKiem.setText("");
			}
		});
		btnHuy.setBounds(331, 115, 97, 44);
		desktopPane.add(btnHuy);
		
		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.setForeground(Color.RED);
		btnThoat.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove.png"));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?","Exit!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					setVisible(false);
				}
			}
		});
		btnThoat.setBounds(575, 115, 97, 44);
		desktopPane.add(btnThoat);
		
		JLabel lblNewLabel_5 = new JLabel("Danh sách thực đơn:");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBounds(6, 192, 133, 13);
		desktopPane.add(lblNewLabel_5);
		
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
					ResultSet rs = st.executeQuery("SELECT TenLoaiTD FROM LoaiThucDon");
					while(rs.next()) {
						String TenLoaiTD = rs.getString("TenLoaiTD");
						cbbLoaiThucDon.addItem(TenLoaiTD);
					}
				}catch(Exception e) {
					
				}
			}
			
		});
		btnNewButton.setBounds(621, 89, 30, 28);
		desktopPane.add(btnNewButton);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(419, 171, 153, 28);
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
		btnTimKiem.setBounds(575, 171, 96, 28);
		desktopPane.add(btnTimKiem);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_6.setBounds(0, 0, 682, 472);
		desktopPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("TênĐN:");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7.setBounds(67, 483, 72, 16);
		desktopPane.add(lblNewLabel_7);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(135, 483, 97, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblNewLabel_7_2 = new JLabel("Quyền:");
		lblNewLabel_7_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_7_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7_2.setBounds(295, 483, 78, 16);
		desktopPane.add(lblNewLabel_7_2);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(370, 483, 97, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_8.setBounds(0, 469, 682, 44);
		desktopPane.add(lblNewLabel_8);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
	}
}
