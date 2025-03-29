package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewPackage extends JFrame {
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewPackage frame = new ViewPackage("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ViewPackage(String username) {
		setTitle("View Package Details");
		setSize(900, 500);
		setLocationRelativeTo(null); // Center window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Custom Gradient Background
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, new Color(0, 80, 180), getWidth(), getHeight(), new Color(0, 30, 100));
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Title Label
		JLabel lblTitle = new JLabel("VIEW PACKAGE DETAILS", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Yu Mincho", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(200, 10, 500, 40);
		contentPane.add(lblTitle);

		// Glass Effect Panel
		JPanel detailsPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(new Color(255, 255, 255, 70)); // Transparent white
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
			}
		};
		detailsPanel.setLayout(new GridBagLayout());
		detailsPanel.setBounds(50, 70, 400, 300);
		detailsPanel.setOpaque(false); // Keep it transparent
		detailsPanel.setBorder(new EmptyBorder(15, 15, 15, 15)); // Remove visible border
		contentPane.add(detailsPanel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Labels & Values
		String[] labels = {"Username:", "Package:", "Number of Persons:", "ID:", "Number:", "Phone:", "Price:"};
		JLabel[] valueLabels = new JLabel[labels.length];

		for (int i = 0; i < labels.length; i++) {
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.WEST;
			JLabel lbl = new JLabel(labels[i]);
			lbl.setFont(new Font("Arial", Font.BOLD, 14));
			lbl.setForeground(Color.WHITE); // White text for contrast
			detailsPanel.add(lbl, gbc);

			gbc.gridx = 1;
			valueLabels[i] = new JLabel();
			valueLabels[i].setFont(new Font("Arial", Font.PLAIN, 14));
			valueLabels[i].setForeground(Color.WHITE);
			detailsPanel.add(valueLabels[i], gbc);
		}

		// Fetch user details
		Conn c = new Conn();
		try {
			ResultSet rs = c.s.executeQuery("SELECT * FROM bookPackage WHERE username = '" + username + "'");
			if (rs.next()) {
				for (int i = 0; i < valueLabels.length; i++) {
					valueLabels[i].setText(rs.getString(i + 1));
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Image on the right
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/bookedDetails.jpg"));
		Image i3 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel la1 = new JLabel(i2);
		la1.setBounds(480, 90, 380, 280);
		contentPane.add(la1);

		// Back Button with Shadow & Hover Effect
		JButton btnExit = new JButton("Back");
		btnExit.setFont(new Font("Arial", Font.BOLD, 16));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(30, 144, 255));
		btnExit.setBorder(null);
		btnExit.setFocusPainted(false);
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.setBounds(150, 390, 150, 40);
		btnExit.setOpaque(true);

		// Button Hover Effect
		btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(new Color(0, 120, 215));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnExit.setBackground(new Color(30, 144, 255));
			}
		});

		btnExit.addActionListener(e -> setVisible(false));
		contentPane.add(btnExit);
	}
}
