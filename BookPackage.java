package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BookPackage extends JFrame {
	private JPanel contentPane;
	JTextField t1;
	Choice c1;
	JLabel l2, l3, l4, l5;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BookPackage frame = new BookPackage("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BookPackage(String username) {
		setTitle("Book Travel Package");
		setBounds(350, 150, 900, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 51, 102));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Title
		JLabel lblTitle = new JLabel("BOOK YOUR PACKAGE");
		lblTitle.setFont(new Font("Poppins", Font.BOLD, 28));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(50, 20, 400, 40);
		contentPane.add(lblTitle);

		// Image
		ImageIcon i1 = new ImageIcon(getClass().getResource("/travel/management/system/icons/bookpackage.jpg"));
		Image i3 = i1.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel imgLabel = new JLabel(i2);
		imgLabel.setBounds(450, 100, 400, 250);
		contentPane.add(imgLabel);

		// Labels & Fields
		JLabel lblUser = new JLabel("Username:");
		lblUser.setFont(new Font("Arial", Font.BOLD, 16));
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(50, 80, 150, 30);
		contentPane.add(lblUser);

		JLabel userLabel = new JLabel(username);
		userLabel.setFont(new Font("Arial", Font.BOLD, 16));
		userLabel.setForeground(Color.CYAN);
		userLabel.setBounds(220, 80, 200, 30);
		contentPane.add(userLabel);

		JLabel lblPackage = new JLabel("Select Package:");
		lblPackage.setFont(new Font("Arial", Font.BOLD, 16));
		lblPackage.setForeground(Color.WHITE);
		lblPackage.setBounds(50, 130, 150, 30);
		contentPane.add(lblPackage);

		c1 = new Choice();
		c1.add("Gold Package");
		c1.add("Silver Package");
		c1.add("Bronze Package");
		c1.setBounds(220, 135, 200, 25);
		c1.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(c1);

		JLabel lblPersons = new JLabel("Total Persons:");
		lblPersons.setFont(new Font("Arial", Font.BOLD, 16));
		lblPersons.setForeground(Color.WHITE);
		lblPersons.setBounds(50, 180, 150, 30);
		contentPane.add(lblPersons);

		t1 = new JTextField("1");
		t1.setBounds(220, 185, 200, 25);
		t1.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(t1);

		JLabel lblId = new JLabel("ID Type:");
		lblId.setFont(new Font("Arial", Font.BOLD, 16));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(50, 230, 150, 30);
		contentPane.add(lblId);

		l2 = new JLabel();
		l2.setFont(new Font("Arial", Font.BOLD, 14));
		l2.setForeground(Color.YELLOW);
		l2.setBounds(220, 230, 200, 30);
		contentPane.add(l2);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Arial", Font.BOLD, 16));
		lblPhone.setForeground(Color.WHITE);
		lblPhone.setBounds(50, 280, 150, 30);
		contentPane.add(lblPhone);

		l4 = new JLabel();
		l4.setFont(new Font("Arial", Font.BOLD, 14));
		l4.setForeground(Color.YELLOW);
		l4.setBounds(220, 280, 200, 30);
		contentPane.add(l4);

		JLabel lblPrice = new JLabel("Total Price:");
		lblPrice.setFont(new Font("Arial", Font.BOLD, 16));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(50, 330, 150, 30);
		contentPane.add(lblPrice);

		l5 = new JLabel();
		l5.setFont(new Font("Arial", Font.BOLD, 18));
		l5.setForeground(Color.RED);
		l5.setBounds(220, 330, 200, 30);
		contentPane.add(l5);

		// Fetch user details
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
			if (rs.next()) {
				l2.setText(rs.getString("id_type"));
				l4.setText(rs.getString("phone"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Buttons
		JButton btnPrice = new JButton("Check Price");
		btnPrice.setBounds(50, 390, 130, 35);
		btnPrice.setBackground(new Color(30, 144, 255));
		btnPrice.setForeground(Color.WHITE);
		btnPrice.setFont(new Font("Arial", Font.BOLD, 14));
		btnPrice.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrice.addActionListener(e -> {
			String p = c1.getSelectedItem();
			int cost = switch (p) {
				case "Gold Package" -> 12000;
				case "Silver Package" -> 25000;
				case "Bronze Package" -> 32000;
				default -> 0;
			};
			cost *= Integer.parseInt(t1.getText());
			l5.setText("Rs " + cost);
		});
		contentPane.add(btnPrice);

		JButton btnBook = new JButton("Book");
		btnBook.setBounds(200, 390, 130, 35);
		btnBook.setBackground(new Color(46, 204, 113));
		btnBook.setForeground(Color.WHITE);
		btnBook.setFont(new Font("Arial", Font.BOLD, 14));
		btnBook.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBook.addActionListener(e -> {
			try {
				Conn c = new Conn();
				String query = "INSERT INTO bookPackage VALUES ('" + username + "', '" + c1.getSelectedItem() + "', '" + t1.getText() + "', '" + l2.getText() + "', '" + l4.getText() + "', '" + l5.getText() + "')";
				c.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Package Booked Successfully");
				setVisible(false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		contentPane.add(btnBook);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(350, 390, 130, 35);
		btnBack.setBackground(new Color(231, 76, 60));
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.addActionListener(e -> setVisible(false));
		contentPane.add(btnBack);
	}
}
