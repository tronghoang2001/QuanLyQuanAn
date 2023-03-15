package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.Font;

public class DangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPasswordField txtMatKhau;

	/**
	 * Launch the application.
	 */
	
	public DangNhap() {
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
					DangNhap frame = new DangNhap();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\12a9n\\Desktop\\DN.png"));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 423, 165);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u0110\u0103ng nh\u1EADp");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\login.png"));
		lblNewLabel.setBackground(Color.ORANGE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_1 = new JLabel("T\u00E0i kho\u1EA3n");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\user.png"));
		lblNewLabel_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtTaiKhoan = new JTextField();
		GridBagConstraints gbc_txtTaiKhoan = new GridBagConstraints();
		gbc_txtTaiKhoan.insets = new Insets(0, 0, 5, 0);
		gbc_txtTaiKhoan.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTaiKhoan.gridx = 1;
		gbc_txtTaiKhoan.gridy = 1;
		contentPane.add(txtTaiKhoan, gbc_txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		lblNewLabel_2 = new JLabel("M\u1EADt kh\u1EA9u");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\padlock.png"));
		lblNewLabel_2.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtMatKhau = new JPasswordField();
		GridBagConstraints gbc_txtMatKhau = new GridBagConstraints();
		gbc_txtMatKhau.insets = new Insets(0, 0, 5, 0);
		gbc_txtMatKhau.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMatKhau.gridx = 1;
		gbc_txtMatKhau.gridy = 2;
		contentPane.add(txtMatKhau, gbc_txtMatKhau);
		
		JButton btnDangNhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangNhap.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnDangNhap.setForeground(Color.BLACK);
		btnDangNhap.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\login (1).png"));
		
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XacThuc();
				
			}

			private void XacThuc() {
				// TODO Auto-generated method stub
				if (txtTaiKhoan.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bạn Chưa nhập tài khoản!");
		            return;
		        }
		        if (txtMatKhau.getText().isEmpty()) {
		        	JOptionPane.showMessageDialog(null, "Bạn chưa nhập mật khẩu!");
		            return;
		        }

		        if (!TaiKhoanDAO.getInstance().DangNhap(txtTaiKhoan.getText(), txtMatKhau.getText())) {
		        	JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu!!");
		            return;
		        }
		        
		        JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");		        
		        GiaoDien gd = new GiaoDien();	
		        gd.setLocationRelativeTo(null);
		        gd.setVisible(true);
		        dispose();
			}
		});
		GridBagConstraints gbc_btnDangNhap = new GridBagConstraints();
		gbc_btnDangNhap.insets = new Insets(0, 0, 0, 5);
		gbc_btnDangNhap.gridx = 0;
		gbc_btnDangNhap.gridy = 3;
		contentPane.add(btnDangNhap, gbc_btnDangNhap);
		
		JButton btnNewButton_1 = new JButton("Tho\u00E1t");
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\12a9n\\Downloads\\button.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kq=JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không?","Exit!",JOptionPane.YES_NO_OPTION);
				if(kq==0)
				{
					setVisible(false);
				}
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
	}

}
