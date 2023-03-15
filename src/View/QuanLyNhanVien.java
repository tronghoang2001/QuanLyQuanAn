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
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

import DTO.BanAn;
import DTO.NhanVien;
import DAO.DatabaseConn;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class QuanLyNhanVien extends javax.swing.JFrame {

	private ArrayList<NhanVien> list;
	private JPanel contentPane;
	DefaultTableModel model;
	private JTable tblNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtSDT;
	private JCheckBox cbxGioiTinh;
	Connection conn = DatabaseConn.openConnection();
	int idSave = -1;
	private JTextField txtTimKiem;
	String strTimKiem = "";
	public QuanLyNhanVien() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\teamwork.png"));
        initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<NhanVien>) NhanVienDAO.getInstance().GetListNhanVien();
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setColumnIdentifiers(new Object[]{
        		"Mã nhân viên", "Tên NV", "Chức vụ", "Giới tính", "SĐT"
            });
        ShowTable();
        LoadTable();
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
					QuanLyNhanVien frame = new QuanLyNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (NhanVien nv : list) {
            model.addRow(new Object[]{
                 nv.getMaNV() ,nv.getTenNV(), nv.getChucVu(), nv.getGioiTinh(), nv.getSDT()
            });
        }
	}
	private void LoadTable() {
        model.setRowCount(0);
        List<NhanVien> list = NhanVienDAO.getInstance().GetListNhanVien();
        for (int i = 0; i < list.size(); i++) {
            NhanVien nv = list.get(i);
            Object[] dt = {nv.getMaNV(), nv.getTenNV(), nv.getChucVu(), nv.getGioiTinh(), nv.getSDT()};
            model.addRow(dt);
        }
    }
	public void ShowDetail() {
		int i = tblNhanVien.getSelectedRow();
		NhanVien nv = list.get(i);
		idSave = list.get(i).getMaNV();
		txtTenNhanVien.setText(String.valueOf(nv.getTenNV()));
		txtSDT.setText(String.valueOf(nv.getSDT()));
	}
	public void FillTable() {
		model.setRowCount(0);
		for (NhanVien nv : NhanVienDAO.getInstance().TimKiemTheoTen(strTimKiem)) {
			Object[] dt = {nv.getMaNV() ,nv.getTenNV(), nv.getChucVu(), nv.getGioiTinh(), nv.getSDT()};
            model.addRow(dt);
		}
	}
	/**
	 * Create the frame.
	 * @return 
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 713, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 255, 153));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD NH\u00C2N VI\u00CAN");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 689, 62);
		desktopPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 259, 689, 287);
		desktopPane.add(scrollPane);
		
		tblNhanVien = new JTable();
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
		});
		tblNhanVien.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblNhanVien);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00EAn nh\u00E2n vi\u00EAn");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(40, 74, 75, 13);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ch\u1EE9c v\u1EE5");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setBounds(40, 116, 53, 13);
		desktopPane.add(lblNewLabel_3);
		
		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setBounds(127, 64, 125, 30);
		desktopPane.add(txtTenNhanVien);
		txtTenNhanVien.setColumns(10);
		
		JCheckBox cbxGioiTinh = new JCheckBox("Check = Nam");
		cbxGioiTinh.setForeground(Color.BLACK);
		cbxGioiTinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			});
		cbxGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbxGioiTinh.setBounds(472, 114, 154, 31);
		desktopPane.add(cbxGioiTinh);
		
		JComboBox cbbChucVu = new JComboBox();
		cbbChucVu.setBounds(127, 106, 125, 30);
		cbbChucVu.addItem("Quản lý");
		cbbChucVu.addItem("Thu ngân");
		desktopPane.add(cbbChucVu);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setForeground(Color.BLACK);
		btnThem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnThem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
		            if (txtTenNhanVien.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập tên nhân viên!");
		                return;
		            }
		            if (txtSDT.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO NhanVien(TenNV, ChucVu, GioiTinh, SĐT) VALUES (?,?,?,?)");
		            ps.setString(1, txtTenNhanVien.getText());
		            ps.setString(2, (String) cbbChucVu.getSelectedItem());
		            ps.setString(3, cbxGioiTinh.isSelected() == true?"Nam":"Nữ");
		            ps.setString(4, txtSDT.getText());
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại!");
		        }
				return;
				
			}
			
		});
		btnThem.setBounds(0, 172, 85, 45);
		desktopPane.add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idSave >= 0) {
					try {
			            PreparedStatement ps= conn.prepareStatement("DELETE FROM NhanVien WHERE MaNV=?");
			            ps.setInt(1, idSave);
			            int i = ps.executeUpdate();
			            if (i > 0) {
			                LoadTable();
			                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
			            }
			        } catch (SQLException ex) {
			            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
			        }
				}
			}
		});
		btnXoa.setBounds(97, 172, 85, 45);
		desktopPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.setForeground(Color.BLACK);
		btnSua.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\fix.png"));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idSave>=0) {
					try {
			            PreparedStatement ps = conn.prepareStatement("UPDATE NhanVien SET TenNV=?,ChucVu=?,GioiTinh=?,SĐT=? WHERE MaNV=?");
			            ps.setString(1, txtTenNhanVien.getText());
			            ps.setString(2, (String) cbbChucVu.getSelectedItem());
			            ps.setString(3, cbxGioiTinh.isSelected()?"Nam":"Nữ");
			            ps.setString(4, txtSDT.getText());
			            ps.setInt(5, idSave);
			            int i = ps.executeUpdate();
			            if (i > 0) {
			                LoadTable();
			                JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!");
			            }
			        } catch (SQLException ex) {
			            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
			        }
				}
			}
		});
		btnSua.setBounds(192, 172, 85, 45);
		desktopPane.add(btnSua);
		
		JButton btnHuy = new JButton("H\u1EE7y");
		btnHuy.setForeground(Color.BLACK);
		btnHuy.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Huy.png"));
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenNhanVien.setText("");
				txtSDT.setText("");
				txtTimKiem.setText("");
			}
		});
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnHuy.setBounds(289, 172, 85, 45);
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
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnThoat.setBounds(594, 172, 85, 45);
		desktopPane.add(btnThoat);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(472, 64, 154, 30);
		desktopPane.add(txtSDT);
		txtSDT.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_4.setBounds(379, 75, 81, 13);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gi\u1EDBi t\u00EDnh");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_5.setBounds(379, 123, 45, 13);
		desktopPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("Danh sách nhân viên:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setBounds(10, 236, 172, 13);
		desktopPane.add(lblNewLabel_7);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(414, 229, 144, 30);
		desktopPane.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\search.png"));
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				strTimKiem = txtTimKiem.getText();
				FillTable();
			}

			private void TimKiemTheoTen() {
				// TODO Auto-generated method stub
				model.getRowCount();
				List<NhanVien> list = NhanVienDAO.getInstance().GetListNhanVien();
				for (int i = 0; i< list.size(); i++){
					if (tblNhanVien.getValueAt(i,1).toString().equals(txtTimKiem.getText())){
						LoadTable();
				    }
				   }
			}
		});
		btnTimKiem.setBounds(568, 228, 106, 31);
		desktopPane.add(btnTimKiem);
		
		JLabel lblNewLabel_8 = new JLabel("TênĐN:");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_8.setBounds(35, 558, 65, 16);
		desktopPane.add(lblNewLabel_8);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(112, 558, 95, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(400, 558, 95, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_8_3 = new JLabel("Quyền:");
		lblNewLabel_8_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_8_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_8_3.setBounds(324, 558, 64, 16);
		desktopPane.add(lblNewLabel_8_3);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_9.setBounds(0, 546, 689, 43);
		desktopPane.add(lblNewLabel_9);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_1.setBounds(0, 0, 689, 546);
		desktopPane.add(lblNewLabel_1);
		
	}
}
