package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;	
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import DAO.BanAnDAO;
import DAO.DatabaseConn;
import DAO.PhieuGoiMonDAO;
import DAO.TaiKhoanDAO;
import DAO.ThucDonDAO;
import DTO.BanAn;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class QuanLyBanAn extends javax.swing.JFrame {
	
	private ArrayList<BanAn> list;
    DefaultTableModel model;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JTable tblBanAn;
	private JScrollPane scrollPane;
	private JTextField txtSoBan;
	private JTextField txtSoLuongGhe;
	Connection conn = DatabaseConn.openConnection();
	public QuanLyBanAn() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Downloads\\circle-table.png"));
		initComponents();
        this.setLocationRelativeTo(null);
        list = (ArrayList<BanAn>) new BanAnDAO().GetListBanAn();
        model = (DefaultTableModel) tblBanAn.getModel();
        model.setColumnIdentifiers(new Object[]{
                "Số Bàn", "Số Lượng Ghế"
            });
        ShowTable();
        LoadTable();
    }
	private void ShowTable() {
		// TODO Auto-generated method stub
		for (BanAn b : list) {
            model.addRow(new Object[]{
                 b.getSoBan(), b.getSoLuongGhe()
            });
        }
	}
	
	private void LoadTable() {
        model.setRowCount(0);
        List<BanAn> list = BanAnDAO.getInstance().GetListBanAn();
        for (int i = 0; i < list.size(); i++) {
            BanAn ba = list.get(i);
            Object[] dt = {ba.getSoBan(), ba.getSoLuongGhe()};
            model.addRow(dt);
        }

    }
	
	public void ShowDetail() {
		int i = tblBanAn.getSelectedRow();
		BanAn ba = list.get(i);
		txtSoBan.setText(String.valueOf(ba.getSoBan()));
		txtSoLuongGhe.setText(String.valueOf(ba.getSoLuongGhe()));
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
					QuanLyBanAn frame = new QuanLyBanAn();
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
		setBounds(100, 100, 664, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GREEN);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 81, 246, 249);
		desktopPane.add(scrollPane);
		
		tblBanAn = new JTable();
		tblBanAn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowDetail();
			}
		});
		scrollPane.setViewportView(tblBanAn);
		tblBanAn.setSurrendersFocusOnKeystroke(true);
		tblBanAn.setBackground(Color.WHITE);
		tblBanAn.setColumnSelectionAllowed(true);
		tblBanAn.setCellSelectionEnabled(true);
		tblBanAn.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		
		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD B\u00C0N \u0102N\r\n");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 640, 57);
		desktopPane.add(lblNewLabel);
		
		txtSoBan = new JTextField();
		txtSoBan.setBounds(128, 78, 254, 28);
		desktopPane.add(txtSoBan);
		txtSoBan.setColumns(10);
		
		txtSoLuongGhe = new JTextField();
		txtSoLuongGhe.setBounds(128, 119, 254, 28);
		desktopPane.add(txtSoLuongGhe);
		txtSoLuongGhe.setColumns(10);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnThem.setForeground(Color.BLACK);
		btnThem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Them.png"));
		
		
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtSoBan.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập số bàn!");
		                return;
		            }
		            if (txtSoLuongGhe.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập số lượng ghế!");
		                return;
		            }
		            PreparedStatement ps = conn.prepareStatement("INSERT INTO BanAn(SoBan, SoLuongGhe ) VALUES (?,?)");		              
		            ps.setString(1, txtSoBan.getText());
		            ps.setInt(2, Integer.parseInt(txtSoLuongGhe.getText()));
		            
		            int i = ps.executeUpdate();		       
		            if (i > 0) {
		            	LoadTable();
		            	JOptionPane.showMessageDialog(null, "Thêm bàn thành công!");		             
		            }		            
		        } catch (SQLException ex) {
		            Logger.getLogger(BanAnDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Số bàn đã tồn tại!");
		        }	
			}
			
			
			
		});
		btnThem.setBounds(0, 161, 88, 45);
		desktopPane.add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnXoa.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove.png"));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
		            PreparedStatement ps= conn.prepareStatement("DELETE FROM BanAn WHERE SoBan=?");
		            ps.setString(1, txtSoBan.getText());
		            if (txtSoBan.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Nhập số bàn muốn xóa!");
		                return;
		            }
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Xóa bàn thành công!");	
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(BanAnDAO.class.getName()).log(Level.SEVERE, null, ex);
		            JOptionPane.showMessageDialog(null, "Bàn ăn tồn tại ở bảng khác!");	
		        }
			}
		});
		
		
		btnXoa.setBounds(98, 161, 88, 45);
		desktopPane.add(btnXoa);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSua.setForeground(Color.BLACK);
		btnSua.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\fix.png"));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtSoBan.getText().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Chưa nhập số bàn muốn sửa!");
		                return;
		            }		            
		            PreparedStatement ps = conn.prepareStatement("UPDATE BanAn SET SoLuongGhe=? WHERE SoBan=?");
		            ps.setInt(1, Integer.parseInt(txtSoLuongGhe.getText()));
		            ps.setString(2, txtSoBan.getText());  
		            int i = ps.executeUpdate();
		            if (i > 0) {
		                LoadTable();
		                JOptionPane.showMessageDialog(null, "Cập nhật bàn thành công!");	
		            }
		        } catch (SQLException ex) {
		            Logger.getLogger(BanAnDAO.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnSua.setBounds(196, 161, 88, 45);
		desktopPane.add(btnSua);
		
		JButton btnHuy = new JButton("H\u1EE7y");
		btnHuy.setForeground(Color.BLACK);
		btnHuy.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnHuy.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\Huy.png"));
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSoBan.setText("");
				txtSoLuongGhe.setText("");	
			}
		});
		btnHuy.setBounds(294, 161, 88, 45);
		desktopPane.add(btnHuy);
		
		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.setFont(new Font("SansSerif", Font.BOLD, 12));
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
		btnThoat.setBounds(542, 347, 90, 46);
		desktopPane.add(btnThoat);
		
		JLabel lblNewLabel_1 = new JLabel("Nh\u1EADp s\u1ED1 b\u00E0n:");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 83, 77, 19);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nh\u1EADp s\u1ED1 l\u01B0\u1EE3ng gh\u1EBF:");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 124, 121, 19);
		desktopPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyGoiMon().setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\order-food (1).png"));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(6, 257, 376, 73);
		desktopPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Danh sách bàn ăn:");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(404, 67, 236, 16);
		desktopPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Tên ĐN:");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(22, 362, 66, 16);
		desktopPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Quyền:");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_5.setBounds(218, 362, 66, 16);
		desktopPane.add(lblNewLabel_5);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTenDN.setForeground(Color.BLACK);
		lblTenDN.setBounds(98, 362, 98, 16);
		desktopPane.add(lblTenDN);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setForeground(Color.BLACK);
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblQuyen.setBounds(283, 362, 98, 16);
		desktopPane.add(lblQuyen);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		lblNewLabel_6.setBackground(Color.WHITE);
		lblNewLabel_6.setBounds(0, 347, 640, 46);
		desktopPane.add(lblNewLabel_6);
		
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\image\\mau-vang.jpg"));
		lblNewLabel_7.setBounds(0, 0, 640, 350);
		desktopPane.add(lblNewLabel_7);
	}
}
