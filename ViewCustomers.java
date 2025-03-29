package travel.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ViewCustomers extends JFrame {
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ViewCustomers frame = new ViewCustomers();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ViewCustomers() throws SQLException {
		setTitle("View Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null); // Open in center
		setResizable(false);

		// Modern Panel with Gradient Background
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
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("Customer Details");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(350, 20, 300, 30);
		contentPane.add(lblTitle);

		// Table Panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 80, 800, 350);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBackground(new Color(255, 51, 51));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		// Load data into table
		try {
			Conn c = new Conn();
			ResultSet rs = c.s.executeQuery("SELECT * FROM customer");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Back Button
		JButton btnBack = createStyledButton("Back", 390, 460);
		btnBack.addActionListener((ActionEvent e) -> setVisible(false));

		contentPane.add(btnBack);
	}

	// Create modern buttons with hover effect
	private JButton createStyledButton(String text, int x, int y) {
		JButton button = new JButton(text);
		button.setBounds(x, y, 120, 40);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 51, 51));
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setFocusPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(204, 0, 0));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(255, 51, 51));
			}
		});

		return button;
	}
}
