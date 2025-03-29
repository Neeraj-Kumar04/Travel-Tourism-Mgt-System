package travel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ViewBookedHotel extends JFrame {
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewBookedHotel frame = new ViewBookedHotel("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ViewBookedHotel(String username) {
		setTitle("Booked Hotel Details"); // 🏨 Add Title
		setSize(900, 600);
		setLocationRelativeTo(null); // Center Window

		// Gradient Background
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				GradientPaint gp = new GradientPaint(0, 0, new Color(0, 51, 153), getWidth(), getHeight(), new Color(0, 102, 204));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Title Label
		JLabel lblTitle = new JLabel("VIEW BOOKED HOTEL DETAILS");
		lblTitle.setFont(new Font("Yu Mincho", Font.BOLD, 22));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(50, 20, 500, 40);
		contentPane.add(lblTitle);

		// Labels
		String[] labels = {"Username :", "Name :", "Number of Persons :", "Number of Days :", "AC / Non-AC :",
				"Food Included (Yes/No) :", "ID :", "Number :", "Phone :", "Cost :"};

		JLabel[] labelArray = new JLabel[labels.length];
		JLabel[] valueLabels = new JLabel[labels.length];

		int yOffset = 80;
		for (int i = 0; i < labels.length; i++) {
			labelArray[i] = new JLabel(labels[i]);
			labelArray[i].setForeground(Color.WHITE);
			labelArray[i].setFont(new Font("Tahoma", Font.BOLD, 16));
			labelArray[i].setBounds(35, yOffset, 250, 25);
			contentPane.add(labelArray[i]);

			valueLabels[i] = new JLabel();
			valueLabels[i].setForeground(Color.YELLOW);
			valueLabels[i].setFont(new Font("Tahoma", Font.PLAIN, 16));
			valueLabels[i].setBounds(290, yOffset, 250, 25);
			contentPane.add(valueLabels[i]);

			yOffset += 40;
		}

		// Load database values
		Conn c = new Conn();
		try {
			ResultSet rs = c.s.executeQuery("SELECT * FROM bookHotel WHERE username = '" + username + "'");
			if (rs.next()) {
				for (int i = 0; i < labels.length; i++) {
					valueLabels[i].setText(rs.getString(i + 1));
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Image
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/bookedDetails.jpg"));
		Image i3 = i1.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel imageLabel = new JLabel(i2);
		imageLabel.setBounds(500, 100, 350, 350); // Adjust Image Position
		contentPane.add(imageLabel);

		// Back Button (Improved Styling)
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(e -> setVisible(false));
		btnExit.setBounds(150, 500, 120, 35);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFocusPainted(false);
		btnExit.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		contentPane.add(btnExit);
	}
}
