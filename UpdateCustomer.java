package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCustomer extends JFrame {
	private JPanel contentPane;
	private JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateCustomer frame = new UpdateCustomer("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UpdateCustomer(String username) throws SQLException {
		setTitle("Update Customer");
		setBounds(500, 200, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Modern Gradient Panel
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

		JLabel lblTitle = new JLabel("UPDATE CUSTOMER DETAILS");
		lblTitle.setFont(new Font("Yu Mincho", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(250, 20, 400, 40);
		contentPane.add(lblTitle);

		String[] labels = {"Username:", "ID:", "Number:", "Name:", "Gender:", "Country:", "Permanent Address:", "Phone:", "Email:"};
		JTextField[] textFields = {t1 = new JTextField(), t2 = new JTextField(), t3 = new JTextField(), t4 = new JTextField(),
				t5 = new JTextField(), t6 = new JTextField(), t7 = new JTextField(), t8 = new JTextField(), t9 = new JTextField()};

		int y = 70;
		for (int i = 0; i < labels.length; i++) {
			JLabel label = new JLabel(labels[i]);
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Tahoma", Font.BOLD, 14));
			label.setBounds(50, y, 200, 25);
			contentPane.add(label);

			textFields[i].setBounds(250, y, 250, 30);
			textFields[i].setBackground(new Color(240, 248, 255));
			textFields[i].setForeground(Color.BLACK);
			textFields[i].setFont(new Font("Tahoma", Font.PLAIN, 14));
			textFields[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			contentPane.add(textFields[i]);
			y += 45;
		}

		// Fetch data from database
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
			if (rs.next()) {
				for (int i = 0; i < textFields.length; i++) {
					textFields[i].setText(rs.getString(i + 1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(100, 500, 140, 40);
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setFocusPainted(false);
		btnUpdate.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		btnUpdate.addActionListener(e -> {
			try {
				Conn c = new Conn();
				String q = "UPDATE customer SET id_type = '" + t2.getText() + "', number = '" + t3.getText() + "', name = '" + t4.getText() + "'," +
						" gender = '" + t5.getText() + "', country = '" + t6.getText() + "', address = '" + t7.getText() + "', phone = '" + t8.getText() + "', email = '" + t9.getText() + "'" +
						" WHERE username = '" + t1.getText() + "'";
				c.s.executeUpdate(q);
				JOptionPane.showMessageDialog(null, "Customer Details Updated Successfully");
				setVisible(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		contentPane.add(btnUpdate);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(260, 500, 140, 40);
		btnBack.setBackground(Color.BLACK);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setFocusPainted(false);
		btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		btnBack.addActionListener(e -> setVisible(false));
		contentPane.add(btnBack);

		// Adding Image with padding
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/update.png"));
		Image i2 = i1.getImage().getScaledInstance(200, 400, Image.SCALE_SMOOTH);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel imageLabel = new JLabel(i3);
		imageLabel.setBounds(550, 100, 220, 400);
		contentPane.add(imageLabel);
	}
}
