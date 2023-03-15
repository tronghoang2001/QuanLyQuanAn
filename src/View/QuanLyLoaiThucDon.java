package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import DAO.DatabaseConn;
import DAO.LoaiThucDonDAO;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.LoaiThucDon;
import DTO.NhanVien;
import DTO.ThucDon;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;


public class QuanLyLoaiThucDon extends JFrame {

	private ArrayList<LoaiThucDon> list;
	DefaultTableModel model;
	private JPanel contentPane;
	private JTextField txtTenLoaiTD;
	private JTable tblLoaiThucDon;
	Connection conn = DatabaseConn.openConnection();
	int idSave = -1;
	
	public QuanLyLoaiThucDon() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\menu (1).png"));
        initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<LoaiThucDon>) LoaiThucDonDAO.getInstance().GetListLoaiThucDon();
        model = (DefaultTableModel) tblLoaiThucDon.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Tên loại TĐ"
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
					QuanLyLoaiThucDon frame = new QuanLyLoaiThucDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void LoadTable() {
		// TODO Auto-generated method stub
		model.setRowCount(0);
        List<LoaiThucDon> list = LoaiThucDonDAO.getInstance().GetListLoaiThucDon();
        for (int i = 0; i < list.size(); i++) {
        	LoaiThucDon ltd = list.get(i);
            Object[] dt = {ltd.getTenLoaiTD()};
            model.addRow(dt);
        }
		
	}
	
	
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (LoaiThucDon ltd : list) {
            model.addRow(new Object[]{
                 ltd.getTenLoaiTD()
            });
        }
	}
	
	private void ShowDetail() {
		int i = tblLoaiThucDon.getSelectedRow();
		LoaiThucDon ltd = list.get(i);
		idSave = list.get(i).getMaLoaiTD();
		txtTenLoaiTD.setText(String.valueOf(ltd.getTenLoaiTD()));		
	}
	
	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 585, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(51, 255, 153));
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("LO\u1EA0I TH\u1EF0C \u0110\u01A0N");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 561, 56);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00EAn lo\u1EA1i T\u0110");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(14, 96, 75, 13);
		desktopPane.add(lblNewLabel_2);
		
		txtTenLoaiTD = new JTextField();
		txtTenLoaiTD.setBounds(101, 87, 237, 28);
		desktopPane.add(txtTenLoaiTD);
		txtTenLoaiTD.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 68, 211, 199);
		desktopPane.add(scrollPane);
		
		tblLoaiThucDon = new JTable();
		tblLoaiThucDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				ShowDetail();
			}
		});
		tblLoaiThucDon.setFont(new Font("Tahoma", Font.BOLD, 10));
		tblLoaiThucDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 lo\u1EA1i T\u0110", "T\u00EAn lo\u1EA1i T\u0110"
			}
		));
		scrollPane.setViewportView(tblLoaiThucDon);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setForeground(Color.BLACK);
		btnThem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {				
		            if (txtTenLoaiTD.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập tên loại thực đơn!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO LoaiThucDon(TenLoaiTD) VALUES (?)");  
		            ps.setString(1, txtTenLoaiTD.getText());
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Thêm loại thực đơn thành công!");
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(LoaiThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Mã loại thực đơn đã tồn tại!");
		        }
			}

		});
		btnThem.setBounds(0, 177, 85, 45);
		desktopPane.add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\delete.png"));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idSave >=0) {
					try {
			            PreparedStatement ps= conn.prepareStatement("DELETE FROM LoaiThucDon WHERE MaLoaiTD=?");
			            ps.setInt(1, idSave);
			            int i = ps.executeUpdate();
			            if (i > 0) {
			                LoadTable();
			                JOptionPane.showMessageDialog(null, "Xóa loại thực đơn thành công!");
			            }
			        } catch (SQLException ex) {
			            Logger.getLogger(LoaiThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
			            JOptionPane.showMessageDialog(null, "Loại thực đơn tồn tại ở bảng khác!");
			        }
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnXoa.setBounds(86, 177, 85, 45);
		desktopPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.setForeground(Color.BLACK);
		btnSua.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\fix.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idSave >= 0) {
					try {
						if (txtTenLoaiTD.getText().isEmpty()) {
			                JOptionPane.showMessageDialog(null, "Nhập tên loại thực đơn muốn sửa!");
			                return;
			            }
			            PreparedStatement ps = conn.prepareStatement("UPDATE LoaiThucDon SET TenLoaiTD=? WHERE MaLoaiTD=?");
			            ps.setString(1, txtTenLoaiTD.getText());
			            ps.setInt(2, idSave);
			            int i = ps.executeUpdate();
			            if (i > 0) {
			                LoadTable();
			                JOptionPane.showMessageDialog(null, "Cập nhật loại thực đơn thành công!");
			            }
			        } catch (SQLException ex) {
			            Logger.getLogger(LoaiThucDonDAO.class.getName()).log(Level.SEVERE, null, ex);
			        }
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSua.setBounds(172, 177, 85, 45);
		desktopPane.add(btnSua);
		
		JButton btnHuy = new JButton("H\u1EE7y");
		btnHuy.setForeground(Color.BLACK);
		btnHuy.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Huy.png"));
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenLoaiTD.setText("");
			}
		});
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnHuy.setBounds(258, 177, 85, 45);
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
		btnThoat.setBounds(476, 279, 85, 45);
		desktopPane.add(btnThoat);
		
		JLabel lblNewLabel_3 = new JLabel("Danh sách loại thực đơn:");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(350, 52, 147, 16);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_1.setBounds(0, 0, 561, 280);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("TênĐN:");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 292, 75, 16);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setBounds(86, 292, 85, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblNewLabel_6 = new JLabel("Quyền:");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_6.setBounds(207, 292, 75, 16);
		desktopPane.add(lblNewLabel_6);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(288, 292, 85, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_5.setBounds(0, 279, 561, 45);
		desktopPane.add(lblNewLabel_5);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
	}
}
