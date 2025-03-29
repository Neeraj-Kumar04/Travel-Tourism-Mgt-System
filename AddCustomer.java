package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCustomer extends JFrame {
	private JPanel contentPane;
	private JTextField t1, t2, t3, t5, t6, t8;
	private JComboBox<String> comboBox;
	private JRadioButton r1, r2;
	private JButton btnAdd, btnBack;

	public AddCustomer(String username) {
		setTitle("New Customer Form");
		setBounds(450, 100, 1090, 690); // Increased size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // Disable maximizing
		setLocationRelativeTo(null); // Center the window on screen

		// Gradient Background Panel
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				GradientPaint gradient = new GradientPaint(0, 0, new Color(30, 144, 255), 0, getHeight(), new Color(0, 0, 128));
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Title
		JLabel lblTitle = new JLabel("NEW CUSTOMER FORM");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(130, 20, 350, 30);
		contentPane.add(lblTitle);

		// Labels & Fields
		addLabel("Username:", 50, 80);
		JTextField t7 = addTextField(200, 80);

		addLabel("ID Type:", 50, 130);
		comboBox = new JComboBox<>(new String[]{"Passport", "Aadhar Card", "Voter ID", "Driving License"});
		comboBox.setBounds(200, 130, 200, 30);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPane.add(comboBox);

		addLabel("ID Number:", 50, 180);
		t1 = addTextField(200, 180);

		addLabel("Full Name:", 50, 230);
		t2 = addTextField(200, 230);

		addLabel("Gender:", 50, 280);
		r1 = createRadioButton("Male", 200, 280);
		r2 = createRadioButton("Female", 280, 280);
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(r1);
		genderGroup.add(r2);

		addLabel("Country:", 50, 330);
		t3 = addTextField(200, 330);

		addLabel("Address:", 50, 380);
		t5 = addTextField(200, 380);

		addLabel("Phone:", 50, 430);
		t6 = addTextField(200, 430);

		addLabel("Email:", 50, 480);
		t8 = addTextField(200, 480);

		// Buttons
		btnAdd = createButton("Add", 100, 530);
		btnAdd.addActionListener(e -> addCustomer(username));

		btnBack = createButton("Back", 250, 530);
		btnBack.addActionListener(e -> setVisible(false));

		// Image on Right Side
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/customer.png"));
		Image img = icon.getImage().getScaledInstance(350, 450, Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(650, 120, 350, 450);
		contentPane.add(imgLabel);

		// Fetch User Data
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("SELECT * FROM account WHERE username = '" + username + "'");
			if (rs.next()) {
				t7.setText(rs.getString("username"));
				t2.setText(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Add Label
	private void addLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setForeground(Color.WHITE);
		label.setBounds(x, y, 150, 30);
		contentPane.add(label);
	}

	// Add TextField with Modern Look
	private JTextField addTextField(int x, int y) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, 200, 30);
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		textField.setBackground(Color.WHITE);
		contentPane.add(textField);
		return textField;
	}

	// Create Radio Buttons
	private JRadioButton createRadioButton(String text, int x, int y) {
		JRadioButton radioButton = new JRadioButton(text);
		radioButton.setBounds(x, y, 80, 30);
		radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
		radioButton.setBackground(new Color(30, 144, 255)); // Match theme
		radioButton.setForeground(Color.WHITE);
		contentPane.add(radioButton);
		return radioButton;
	}

	// Create Modern Buttons
	private JButton createButton(String text, int x, int y) {
		JButton button = new JButton(text);
		button.setBounds(x, y, 120, 40);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(0, 102, 204)); // Deep blue
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setFocusPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 51, 153)); // Darker on hover
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0, 102, 204));
			}
		});
		contentPane.add(button);
		return button;
	}

	// Add Customer to Database
	private void addCustomer(String username) {
		Conn c = new Conn();
		String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;

		try {
			String q1 = "INSERT INTO customer VALUES ('" + username + "','" +
					comboBox.getSelectedItem() + "','" + t1.getText() + "','" +
					t2.getText() + "','" + gender + "','" + t3.getText() + "','" +
					t5.getText() + "','" + t6.getText() + "','" + t8.getText() + "')";
			c.s.executeUpdate(q1);
			JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
			setVisible(false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AddCustomer frame = new AddCustomer("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
