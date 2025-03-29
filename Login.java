package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginBtn, signupBtn, forgotPassBtn;

	public Login() {
		setTitle("Login - Travel Management System");

		// Center the window
		setSize(800, 450);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(15, 50, 80)); // Dark Blue Background
		panel.setLayout(null);
		setContentPane(panel);

		// Login Box
		JPanel loginBox = new JPanel();
		loginBox.setBounds(200, 80, 380, 250);
		loginBox.setBackground(new Color(30, 70, 120)); // Blue
		loginBox.setLayout(null);
		panel.add(loginBox);

		JLabel title = new JLabel("Login Here");
		title.setFont(new Font("Poppins", Font.BOLD, 18));
		title.setForeground(Color.WHITE);
		title.setBounds(30, 15, 200, 30);
		loginBox.add(title);

		JLabel userLabel = new JLabel("Username:");
		userLabel.setBounds(30, 60, 100, 20);
		userLabel.setForeground(Color.WHITE);
		loginBox.add(userLabel);

		// Username Field with Placeholder
		textField = new JTextField("Enter username");
		textField.setForeground(Color.GRAY);
		textField.setBounds(130, 60, 200, 25);
		textField.setBackground(new Color(240, 240, 240));
		textField.setBorder(null);
		textField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals("Enter username")) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText("Enter username");
					textField.setForeground(Color.GRAY);
				}
			}
		});
		loginBox.add(textField);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(30, 100, 100, 20);
		passLabel.setForeground(Color.WHITE);
		loginBox.add(passLabel);

		// Password Field with Placeholder
		passwordField = new JPasswordField("Enter password");
		passwordField.setForeground(Color.GRAY);
		passwordField.setBounds(130, 100, 200, 25);
		passwordField.setBackground(new Color(240, 240, 240));
		passwordField.setBorder(null);
		passwordField.setEchoChar((char) 0); // Show text by default

		passwordField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (new String(passwordField.getPassword()).equals("Enter password")) {
					passwordField.setText("");
					passwordField.setEchoChar('*');
					passwordField.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (passwordField.getPassword().length == 0) {
					passwordField.setText("Enter password");
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(Color.GRAY);
				}
			}
		});
		loginBox.add(passwordField);

		// Password Visibility Toggle
		JCheckBox showPass = new JCheckBox("Show Password");
		showPass.setBounds(130, 130, 150, 20);
		showPass.setBackground(new Color(30, 70, 120));
		showPass.setForeground(Color.WHITE);
		showPass.setFocusPainted(false);
		showPass.addActionListener(e -> {
			if (showPass.isSelected()) {
				passwordField.setEchoChar((char) 0);
			} else {
				passwordField.setEchoChar('*');
			}
		});
		loginBox.add(showPass);

		// Buttons
		loginBtn = new JButton("Login");
		loginBtn.setBounds(30, 170, 90, 30);
		loginBtn.setBackground(new Color(0, 122, 255)); // Blue
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBorder(null);
		loginBtn.setFocusPainted(false);
		loginBtn.addActionListener(this);
		addHoverEffect(loginBtn, new Color(0, 102, 204), new Color(0, 122, 255));
		loginBox.add(loginBtn);

		signupBtn = new JButton("SignUp");
		signupBtn.setBounds(140, 170, 90, 30);
		signupBtn.setBackground(new Color(255, 149, 0)); // Orange
		signupBtn.setForeground(Color.WHITE);
		signupBtn.setBorder(null);
		signupBtn.setFocusPainted(false);
		signupBtn.addActionListener(this);
		addHoverEffect(signupBtn, new Color(230, 129, 0), new Color(255, 149, 0));
		loginBox.add(signupBtn);

		forgotPassBtn = new JButton("Forgot Password");
		forgotPassBtn.setBounds(250, 170, 110, 30);
		forgotPassBtn.setBackground(new Color(255, 45, 85)); // Red
		forgotPassBtn.setForeground(Color.WHITE);
		forgotPassBtn.setBorder(null);
		forgotPassBtn.setFocusPainted(false);
		forgotPassBtn.addActionListener(this);
		addHoverEffect(forgotPassBtn, new Color(204, 0, 51), new Color(255, 45, 85));
		loginBox.add(forgotPassBtn);

		// User Icon
		ImageIcon icon = new ImageIcon(getClass().getResource("/travel/management/system/icons/user.png"));
		JLabel userIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		userIcon.setBounds(610, 120, 150, 150); // Adjusted position & size
		panel.add(userIcon);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Method to Add Hover Effect
	private void addHoverEffect(JButton button, Color hoverColor, Color defaultColor) {
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				button.setBackground(hoverColor);
			}

			public void mouseExited(MouseEvent evt) {
				button.setBackground(defaultColor);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == loginBtn) {
			try {
				Conn con = new Conn();
				String sql = "SELECT * FROM account WHERE username=? AND password=?";
				PreparedStatement st = con.c.prepareStatement(sql);

				st.setString(1, textField.getText());
				st.setString(2, new String(passwordField.getPassword()));

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					setVisible(false);
					new Loading(textField.getText()).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (ae.getSource() == signupBtn) {
			setVisible(false);
			new Signup().setVisible(true);
		}

		if (ae.getSource() == forgotPassBtn) {
			setVisible(false);
			new ForgotPassword().setVisible(true);
		}
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
