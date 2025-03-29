package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteCustomer extends JFrame {
	private JPanel contentPane;
	private Choice c1;
	private JLabel l2, l3, l4, l5, l6, l7, l8, l9;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DeleteCustomer frame = new DeleteCustomer();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public DeleteCustomer() throws SQLException {
		setTitle("Delete Customer");
		setBounds(450, 150, 900, 600); // Centered & resized
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); // Open in center

		// Gradient Background Panel
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 51, 102));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("DELETE CUSTOMER DETAILS");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(50, 20, 400, 30);
		contentPane.add(lblTitle);

		addLabel("Username:", 50, 80);
		c1 = new Choice();
		c1.setBounds(200, 80, 200, 30);
		contentPane.add(c1);

		// Load usernames
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("SELECT username FROM customer");
			while (rs.next()) {
				c1.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		addLabel("ID:", 50, 130);
		l2 = addValueLabel(200, 130);

		addLabel("ID Number:", 50, 170);
		l3 = addValueLabel(200, 170);

		addLabel("Name:", 50, 210);
		l4 = addValueLabel(200, 210);

		addLabel("Gender:", 50, 250);
		l5 = addValueLabel(200, 250);

		addLabel("Country:", 50, 290);
		l6 = addValueLabel(200, 290);

		addLabel("Address:", 50, 330);
		l7 = addValueLabel(200, 330);

		addLabel("Phone:", 50, 370);
		l8 = addValueLabel(200, 370);

		addLabel("Email:", 50, 410);
		l9 = addValueLabel(200, 410);

		// Buttons
		JButton btnCheck = createButton("Check", 420, 80);
		btnCheck.addActionListener(e -> fetchCustomerDetails());

		JButton btnDelete = createButton("Delete", 150, 480);
		btnDelete.addActionListener(e -> deleteCustomer());

		JButton btnBack = createButton("Back", 320, 480);
		btnBack.addActionListener(e -> setVisible(false));

		// Image on Right
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/delete.png"));
		Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		JLabel imgLabel = new JLabel(new ImageIcon(img));
		imgLabel.setBounds(550, 150, 300, 300);
		contentPane.add(imgLabel);
	}

	// Method to add labels
	private void addLabel(String text, int x, int y) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setForeground(Color.WHITE);
		label.setBounds(x, y, 150, 30);
		contentPane.add(label);
	}

	// Method to add value labels
	private JLabel addValueLabel(int x, int y) {
		JLabel label = new JLabel();
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(Color.YELLOW);
		label.setBounds(x, y, 200, 30);
		contentPane.add(label);
		return label;
	}

	// Create modern buttons with hover effect
	private JButton createButton(String text, int x, int y) {
		JButton button = new JButton(text);
		button.setBounds(x, y, 120, 40);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 51, 51)); // Red color for delete actions
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setFocusPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(204, 0, 0)); // Darker red on hover
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(255, 51, 51));
			}
		});

		contentPane.add(button);
		return button;
	}

	// Fetch customer details
	private void fetchCustomerDetails() {
		Conn c = new Conn();
		String username = c1.getSelectedItem();

		try {
			ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
			if (rs.next()) {
				l2.setText(rs.getString("id"));
				l3.setText(rs.getString("id_number"));
				l4.setText(rs.getString("name"));
				l5.setText(rs.getString("gender"));
				l6.setText(rs.getString("country"));
				l7.setText(rs.getString("address"));
				l8.setText(rs.getString("phone"));
				l9.setText(rs.getString("email"));
			} else {
				JOptionPane.showMessageDialog(null, "Customer not found!");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error fetching details: " + e.getMessage());
		}
	}

	// Delete customer
	private void deleteCustomer() {
		Conn c = new Conn();
		String username = c1.getSelectedItem();

		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirm", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			try {
				String query = "DELETE FROM customer WHERE username = '" + username + "'";
				c.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Customer Deleted Successfully!");
				setVisible(false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error deleting customer: " + e.getMessage());
			}
		}
	}
}
