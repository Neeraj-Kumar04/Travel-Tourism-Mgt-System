package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField usernameField, nameField, passwordField, answerField;
	private JButton createButton, backButton;
	private JComboBox<String> securityQuestionBox;

	public static void main(String[] args) {
		new Signup().setVisible(true);
	}

	public Signup() {
		setTitle("Signup - Travel Management System");
		setSize(750, 450);
		setLocationRelativeTo(null); // ✅ Center the window
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 60, 90)); // Modern Blue Shade
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Panel Border
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 2), "Create Your Acc...",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18), Color.WHITE));
		panel.setBounds(50, 50, 650, 300);
		panel.setBackground(new Color(50, 100, 150)); // Lighter blue
		panel.setLayout(null);
		contentPane.add(panel);

		// Labels
		JLabel lblUsername = createLabel("Username:", 50, 50);
		JLabel lblName = createLabel("Name:", 50, 90);
		JLabel lblPassword = createLabel("Password:", 50, 130);
		JLabel lblSecurityQuestion = createLabel("Security Question:", 50, 170);
		JLabel lblAnswer = createLabel("Answer:", 50, 210);

		panel.add(lblUsername);
		panel.add(lblName);
		panel.add(lblPassword);
		panel.add(lblSecurityQuestion);
		panel.add(lblAnswer);

		// Input Fields
		usernameField = createTextField(200, 50);
		nameField = createTextField(200, 90);
		passwordField = createTextField(200, 130);
		answerField = createTextField(200, 210);

		panel.add(usernameField);
		panel.add(nameField);
		panel.add(passwordField);
		panel.add(answerField);

		// Dropdown (Security Question)
		securityQuestionBox = new JComboBox<>(new String[]{
				"Your Nickname?", "Your Lucky Number?",
				"Your Childhood SuperHero?", "Your Childhood Name?"
		});
		securityQuestionBox.setBounds(200, 170, 200, 30);
		securityQuestionBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		securityQuestionBox.setBackground(Color.WHITE);
		securityQuestionBox.setFocusable(false);
		panel.add(securityQuestionBox);

		// Buttons
		createButton = new JButton("Create Account");
		createButton.setBounds(120, 260, 150, 30);
		createButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		createButton.setBackground(new Color(0, 128, 255));
		createButton.setForeground(Color.WHITE);
		createButton.addActionListener(this);
		panel.add(createButton);

		backButton = new JButton("Back");
		backButton.setBounds(320, 260, 100, 30);
		backButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		backButton.setBackground(new Color(255, 69, 0));
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);
		panel.add(backButton);

		// Icon Image
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/signup.png"));
		Image scaledIcon = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));
		iconLabel.setBounds(450, 90, 120, 120);
		panel.add(iconLabel);
	}

	// Create Label
	private JLabel createLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(x, y, 150, 30);
		return label;
	}

	// Create TextField
	private JTextField createTextField(int x, int y) {
		JTextField textField = new JTextField();
		textField.setBounds(x, y, 200, 30);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		return textField;
	}

	// Action Events
	public void actionPerformed(ActionEvent ae) {
		try {
			Conn con = new Conn();

			if (ae.getSource() == createButton) {
				String sql = "INSERT INTO account(username, name, password, question, answer) VALUES(?, ?, ?, ?, ?)";
				PreparedStatement st = con.c.prepareStatement(sql);

				st.setString(1, usernameField.getText());
				st.setString(2, nameField.getText());
				st.setString(3, passwordField.getText());
				st.setString(4, (String) securityQuestionBox.getSelectedItem());
				st.setString(5, answerField.getText());

				int i = st.executeUpdate();
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "Account Created Successfully!");
				}

				usernameField.setText("");
				nameField.setText("");
				passwordField.setText("");
				answerField.setText("");
			}

			if (ae.getSource() == backButton) {
				this.setVisible(false);
				new Login().setVisible(true);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
