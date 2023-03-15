package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseConn;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVien;
import DTO.TaiKhoan;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class QuanLyTaiKhoan extends JFrame {

	private JPanel contentPane;
	private JTable tblTaiKhoan;
	private ArrayList<TaiKhoan> list;
	DefaultTableModel model;
	Connection conn = DatabaseConn.openConnection();
	private JTextField txtTenDN;
	private JPasswordField txtMatKhau;
	private JPasswordField txtXacNhanMK;
	private JTextField txtTimKiem;
	String strTimKiem = "";
	int idSave = -1;
	
	
	public QuanLyTaiKhoan() {
		setResizable(false);
		initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<TaiKhoan>) TaiKhoanDAO.getInstance().GetListTaiKhoan();
        model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Tên đăng nhập", "Mật khẩu", "Quyền", "Mã nhân viên"
            });
        ShowTable();
        LoadTable();
	}
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<TaiKhoan> list = TaiKhoanDAO.getInstance().GetListTaiKhoan();
        for (int i = 0; i < list.size(); i++) {
        	TaiKhoan tk = list.get(i);
            Object[] dt = {tk.getTenDN(), tk.getMatKhau(), tk.getQuyenDN(), tk.getMaNV()};
            model.addRow(dt);
        }
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (TaiKhoan tk : list) {
            model.addRow(new Object[]{
            	tk.getTenDN(), tk.getMatKhau(), tk.getQuyenDN(), tk.getMaNV()
            });
        }
		
	}
	public void ShowDetail() {
		int i = tblTaiKhoan.getSelectedRow();
		TaiKhoan tk = list.get(i);
		idSave = list.get(i).getMaTK();
		txtTenDN.setText(String.valueOf(tk.getTenDN()));
	}
	
	public void FillTable() {
		model.setRowCount(0);
		for (TaiKhoan tk : TaiKhoanDAO.getInstance().TimKiemTheoTen(strTimKiem)) {
			Object[] dt = {tk.getTenDN(), tk.getMatKhau(), tk.getQuyenDN(), tk.getMaNV()};
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
					QuanLyTaiKhoan frame = new QuanLyTaiKhoan();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\account.png"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 794, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 255, 153));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD T\u00C0I KHO\u1EA2N");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, -4, 770, 69);
		desktopPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 263, 770, 265);
		desktopPane.add(scrollPane);
		
		tblTaiKhoan = new JTable();
		tblTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ShowDetail();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
		});
		tblTaiKhoan.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblTaiKhoan);
		
		JButton btnHy = new JButton("H\u1EE7y");
		btnHy.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnHy.setForeground(Color.BLACK);
		btnHy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenDN.setText("");
				txtMatKhau.setText("");
				txtXacNhanMK.setText("");
				txtTimKiem.setText("");
			}
		});
		btnHy.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Huy.png"));
		btnHy.setBounds(314, 174, 91, 50);
		desktopPane.add(btnHy);
		
		JButton btnThot = new JButton("Tho\u00E1t");
		btnThot.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnThot.setForeground(Color.RED);
		btnThot.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove.png"));
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?","Exit!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					setVisible(false);
				}
			}
		});
		btnThot.setBounds(673, 174, 91, 50);
		desktopPane.add(btnThot);
		
		JLabel lblNewLabel_5 = new JLabel("Danh sách tài khoản:");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBounds(0, 243, 194, 16);
		desktopPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Tên ĐN");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(10, 77, 50, 16);
		desktopPane.add(lblNewLabel_2);
		
		txtTenDN = new JTextField();
		txtTenDN.setBounds(61, 71, 132, 28);
		desktopPane.add(txtTenDN);
		txtTenDN.setColumns(10);
		
		
		JLabel lblNewLabel_3 = new JLabel("Mật khẩu");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(231, 77, 67, 16);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Xác nhận MK");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(231, 112, 91, 16);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Mã NV");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setBounds(493, 77, 50, 16);
		desktopPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Quyền");
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setBounds(493, 112, 50, 16);
		desktopPane.add(lblNewLabel_7);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(310, 71, 145, 28);
		desktopPane.add(txtMatKhau);
		
		txtXacNhanMK = new JPasswordField();
		txtXacNhanMK.setBounds(310, 106, 145, 28);
		desktopPane.add(txtXacNhanMK);
		
		JComboBox cbbNhanVien = new JComboBox();
		cbbNhanVien.setBounds(544, 72, 132, 27);
		desktopPane.add(cbbNhanVien);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNhanVien();
			}

			private void getNhanVien() {
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
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\refresh.png"));
		btnNewButton_1.setBounds(681, 71, 42, 28);
		desktopPane.add(btnNewButton_1);
		
		JComboBox cbbQuyenDN = new JComboBox();
		cbbQuyenDN.setBounds(544, 107, 132, 27);
		desktopPane.add(cbbQuyenDN);
		
		cbbQuyenDN.addItem("admin");
	    cbbQuyenDN.addItem("users");
		
		JButton btnNewButton = new JButton("Tạo");
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\add-user.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            if (txtTenDN.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập tên đăng nhập!");
		                return;
		            }
		            if (txtMatKhau.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu!");
		                return;
		            }		           
		            if (txtXacNhanMK.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập xác nhận mật khẩu!");
		                return;
		            }
		            if (!txtMatKhau.getText().equals(txtXacNhanMK.getText())) {
		                JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu chưa chính xác!");
		                return;
		            }
		            if (cbbNhanVien.getSelectedItem() == null) {
		            	JOptionPane.showMessageDialog(null, "Chưa thêm mã nhân viên!");
		                return;
		            }
            
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO TaiKhoan(TenDN, MatKhau, QuyenDN, MaNV) VALUES (?,?,?,?)");		            
		            ps.setString(1, txtTenDN.getText());   
		            ps.setString(2, txtMatKhau.getText());
		            ps.setString(3, (String) cbbQuyenDN.getSelectedItem()); 
		            ps.setString(4, (String) cbbNhanVien.getSelectedItem());	
		            
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công!");
		            }		            
		        } catch (SQLException ex) {	        	
		            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại, vui lòng sử dụng tên khác!");
		        }	
			}

		});
		btnNewButton.setBounds(11, 174, 86, 50);
		desktopPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Xóa");
		btnNewButton_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            PreparedStatement ps= conn.prepareStatement("DELETE FROM TaiKhoan WHERE TenDN=?");
		            ps.setString(1, txtTenDN.getText());
		            if (txtTenDN.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Nhập tên đăng nhập muốn xóa!");
		                return;
		            }
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnNewButton_2.setBounds(109, 174, 86, 50);
		desktopPane.add(btnNewButton_2);
		
		JButton btniMtKhu = new JButton("Sửa");
		btniMtKhu.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\fix.png"));
		btniMtKhu.setFont(new Font("SansSerif", Font.BOLD, 12));
		btniMtKhu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtTenDN.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Nhập tên đăng nhập muốn sửa!");
		                return;
		            }
					if (txtMatKhau.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu!");
		                return;
		            }
					if (txtXacNhanMK.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập xác nhận mật khẩu!");
		                return;
		            }
		            if (!txtMatKhau.getText().equals(txtXacNhanMK.getText())) {
		                JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu chưa chính xác!");
		                return;
		            }
		            if (cbbNhanVien.getSelectedItem() == null) {
		            	JOptionPane.showMessageDialog(null, "Chưa thêm mã nhân viên!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("UPDATE TaiKhoan SET MatKhau=?, QuyenDN=?, MaNV=? WHERE TenDN=?");		            
		            ps.setString(1, txtMatKhau.getText());
		            ps.setString(2, (String) cbbQuyenDN.getSelectedItem()); 
		            ps.setString(3, (String) cbbNhanVien.getSelectedItem());
		            ps.setString(4, txtTenDN.getText());		         
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công!");
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btniMtKhu.setForeground(Color.BLACK);
		btniMtKhu.setBounds(207, 174, 91, 50);
		desktopPane.add(btniMtKhu);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(514, 237, 145, 28);
		desktopPane.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strTimKiem = txtTimKiem.getText();
				FillTable();
			}
		});
		btnTimKiem.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\search.png"));
		btnTimKiem.setBounds(660, 237, 104, 28);
		desktopPane.add(btnTimKiem);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_8.setBounds(0, -4, 770, 532);
		desktopPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("TênĐN:");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_9.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_9.setBounds(61, 540, 71, 16);
		desktopPane.add(lblNewLabel_9);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(132, 540, 104, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblNewLabel_9_2 = new JLabel("Quyền:");
		lblNewLabel_9_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_9_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_9_2.setBounds(381, 540, 74, 16);
		desktopPane.add(lblNewLabel_9_2);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(462, 540, 104, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_10.setBounds(0, 528, 770, 43);
		desktopPane.add(lblNewLabel_10);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		
	}
}
