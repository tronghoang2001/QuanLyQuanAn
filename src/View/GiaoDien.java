package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DatabaseConn;
import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;

import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Cursor;
import javax.swing.JSplitPane;
import java.awt.Toolkit;

public class GiaoDien extends JFrame {
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem btnDX;
	private JMenuItem btnTTK;
	private JMenuItem mntmNewMenuItem_3;
	private JDesktopPane desktopPane;
	Connection conn = DatabaseConn.openConnection();
	
	/**
	 * Launch the application.
	 */
	public GiaoDien() {
		setResizable(false);
		initComponents();
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
					GiaoDien frame = new GiaoDien();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Desktop\\a.png"));
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 975, 689);
		
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(51, 255, 102));
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("H\u1EC7 th\u1ED1ng");
		mnNewMenu.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\content-management-system.png"));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenu.setBackground(new Color(255, 102, 0));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("\u0110\u0103ng nh\u1EADp");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap dn = new DangNhap();
				dn.setLocationRelativeTo(null);
				dn.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\login (2).png"));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			
			
		});
		mntmNewMenuItem.setBackground(new Color(102, 255, 51));
		mnNewMenu.add(mntmNewMenuItem);
		
		btnDX = new JMenuItem("\u0110\u0103ng xu\u1EA5t");
		btnDX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?","Log out!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					DangNhap dn = new DangNhap();
					dn.setLocationRelativeTo(dn);
					dn.setVisible(true); 
					dispose();
				}		
			}
		});
		btnDX.addMouseListener(new MouseAdapter() {
			
		});
		btnDX.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\logout (1).png"));
		btnDX.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDX.setBackground(new Color(102, 255, 102));
		mnNewMenu.add(btnDX);
		
		btnTTK = new JMenuItem("T\u1EA1o t\u00E0i kho\u1EA3n m\u1EDBi");
		btnTTK.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\add-user.png"));
		btnTTK.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTTK.addMouseListener(new MouseAdapter() {
			
		});
		btnTTK.setBackground(new Color(102, 255, 153));
		mnNewMenu.add(btnTTK);
		
		mntmNewMenuItem_3 = new JMenuItem("Thoát");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?",
                        null, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
			}
		});
		mntmNewMenuItem_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\button (1).png"));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmNewMenuItem_3.setBackground(new Color(102, 255, 204));
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ QUÁN ĂN");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 961, 83);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên ĐN:");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(75, 577, 80, 16);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quyền:");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\permission.png"));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(374, 577, 80, 16);
		desktopPane.add(lblNewLabel_2);
		
		JLabel lblTenDN = new JLabel("New label");
		lblTenDN.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblTenDN.setBounds(158, 577, 128, 16);
		lblTenDN.setForeground(Color.BLACK);
		desktopPane.add(lblTenDN);
		
		JLabel lblQuyen = new JLabel("New label");
		lblQuyen.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblQuyen.setBounds(454, 577, 128, 16);
		lblQuyen.setForeground(Color.BLACK);
		desktopPane.add(lblQuyen);
		
		JButton btnQLNV = new JButton("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\team.png"));
		btnQLNV.setForeground(new Color(0, 0, 0));
		btnQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
		        new QuanLyNhanVien().setVisible(true);
			}
		});
		btnQLNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQLNV.setBackground(new Color(0, 255, 102));
		btnQLNV.setBounds(6, 95, 465, 129);
		desktopPane.add(btnQLNV);
		
		JButton btnQLTK = new JButton("Quản lý tài khoản");
		btnQLTK.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\account.png"));
		btnQLTK.setForeground(new Color(0, 0, 0));
		btnQLTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyTaiKhoan().setVisible(true);
			}
		});
		btnQLTK.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQLTK.setBackground(new Color(0, 255, 102));
		btnQLTK.setBounds(483, 95, 470, 129);
		desktopPane.add(btnQLTK);
		
		btnTTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyTaiKhoan().setVisible(true);
			}
		});
		
		JButton btnQLBA = new JButton("Quản lý bàn ăn");
		btnQLBA.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\table.png"));
		btnQLBA.setForeground(new Color(0, 0, 0));
		btnQLBA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyBanAn().setVisible(true);
			}
		});
		btnQLBA.setBackground(new Color(0, 255, 102));
		btnQLBA.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQLBA.setBounds(6, 371, 947, 123);
		desktopPane.add(btnQLBA);
		
		JButton btnQLLTD = new JButton("Quản lý loại thực đơn");
		btnQLLTD.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\menu (1).png"));
		btnQLLTD.setForeground(new Color(0, 0, 0));
		btnQLLTD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyLoaiThucDon().setVisible(true);
			}
		});
		btnQLLTD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQLLTD.setBackground(new Color(0, 255, 102));
		btnQLLTD.setBounds(483, 236, 470, 123);
		desktopPane.add(btnQLLTD);
		
		JButton btnQLTD = new JButton("Quản lý thực đơn");
		btnQLTD.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\restaurant-menu.png"));
		btnQLTD.setForeground(new Color(0, 0, 0));
		btnQLTD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuanLyThucDon().setVisible(true);
			}
		});
		btnQLTD.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnQLTD.setBackground(new Color(0, 255, 102));
		btnQLTD.setBounds(7, 236, 464, 123);
		desktopPane.add(btnQLTD);
		
		JButton btnNewButton_1 = new JButton("Thoát");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?","Exit!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					setVisible(false);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\remove-button.png"));
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton_1.setBackground(new Color(255, 0, 51));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(6, 500, 947, 47);
		desktopPane.add(btnNewButton_1);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\12a9n\\Desktop\\Giaodien.jpg");
			
		lblTenDN.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN());
		lblQuyen.setText(TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN());
		
		JLabel label_1 = new JLabel("New label");
		label_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\pure-white-background-85a2a7fd.jpg"));
		label_1.setBounds(0, 559, 961, 54);
		desktopPane.add(label_1);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\12a9n\\Desktop\\BanGo.jpg"));
		lblNewLabel_3.setBounds(0, 0, 961, 561);
		desktopPane.add(lblNewLabel_3);
		
		if (!TaiKhoanDAO.getInstance().DangNhap(TaiKhoanDAO.getInstance().GetTaiKhoan().getTenDN(), TaiKhoanDAO.getInstance().GetTaiKhoan().getMatKhau())) {
        	btnQLNV.setEnabled(false);
        	btnQLTK.setEnabled(false);
        	btnQLTD.setEnabled(false);
        	btnQLLTD.setEnabled(false);
        	btnQLBA.setEnabled(false);
        	btnDX.setEnabled(false);
        	btnTTK.setEnabled(false);
        }
		if (!TaiKhoanDAO.getInstance().DangNhap(lblTenDN.getText(),lblQuyen.getText())) {
			if(!TaiKhoanDAO.getInstance().GetTaiKhoan().getQuyenDN().equals("admin")) {
				btnQLNV.setVisible(false);
				btnQLTK.setVisible(false);
				btnQLLTD.setVisible(false);
				btnQLTD.setVisible(false);
				btnTTK.setVisible(false);
			}
		}
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
