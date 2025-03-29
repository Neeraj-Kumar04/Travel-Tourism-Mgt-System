package travel.management.system;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookHotel extends JFrame {
	private JPanel contentPane;
	JTextField t1, t2;
	Choice c1, c2, c3;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BookHotel frame = new BookHotel("");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public BookHotel(String username) {
		setSize(1100, 600);
		setLocationRelativeTo(null); // Center the window

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

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/book.jpg"));
		Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel la1 = new JLabel(i2);
		la1.setBounds(450, 100, 700, 300);
		add(la1);

		JLabel lblName = new JLabel("BOOK HOTEL");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 22));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(118, 11, 300, 53);
		contentPane.add(lblName);

		JLabel la2 = new JLabel("Username :");
		la2.setForeground(Color.WHITE);
		la2.setBounds(35, 70, 200, 16);
		contentPane.add(la2);

		JLabel l1 = new JLabel(username);
		l1.setForeground(Color.WHITE);
		l1.setBounds(271, 70, 200, 16);
		contentPane.add(l1);

		JLabel lblId = new JLabel("Select Hotel :");
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(35, 110, 200, 16);
		contentPane.add(lblId);

		c1 = new Choice();
		Conn c = new Conn();
		try {
			ResultSet rs = c.s.executeQuery("select * from hotels");
			while (rs.next()) {
				c1.add(rs.getString("name"));
			}
			rs.close();
		} catch (SQLException e) {}

		c1.setBounds(271, 110, 150, 30);
		add(c1);

		JLabel la3 = new JLabel("Total Persons");
		la3.setForeground(Color.WHITE);
		la3.setBounds(35, 150, 200, 16);
		contentPane.add(la3);

		t1 = new JTextField("0");
		t1.setBounds(271, 150, 150, 20);
		contentPane.add(t1);

		JLabel la4 = new JLabel("Number of Days");
		la4.setForeground(Color.WHITE);
		la4.setBounds(35, 190, 200, 16);
		contentPane.add(la4);

		t2 = new JTextField("0");
		t2.setBounds(271, 190, 150, 24);
		contentPane.add(t2);

		JLabel la5 = new JLabel("AC / Non-AC");
		la5.setForeground(Color.WHITE);
		la5.setBounds(35, 230, 200, 16);
		contentPane.add(la5);

		c2 = new Choice();
		c2.add("AC");
		c2.add("Non-AC");
		c2.setBounds(271, 230, 150, 30);
		add(c2);

		JLabel la6 = new JLabel("Food Included :");
		la6.setForeground(Color.WHITE);
		la6.setBounds(35, 270, 200, 16);
		contentPane.add(la6);

		c3 = new Choice();
		c3.add("Yes");
		c3.add("No");
		c3.setBounds(271, 270, 150, 30);
		add(c3);

		JLabel lblDeposite = new JLabel("Total Price :");
		lblDeposite.setForeground(Color.WHITE);
		lblDeposite.setBounds(35, 430, 200, 16);
		contentPane.add(lblDeposite);

		JLabel l5 = new JLabel();
		l5.setBounds(271, 430, 200, 16);
		l5.setForeground(Color.YELLOW);
		contentPane.add(l5);

		JButton b1 = new JButton("Check Price");
		b1.addActionListener(e -> {
			try {
				ResultSet rs = c.s.executeQuery("select * from hotels where name = '" + c1.getSelectedItem() + "'");
				if (rs.next()) {
					int cost = rs.getInt("cost_per_day");
					int food = rs.getInt("food_charges");
					int ac = rs.getInt("ac_charges");
					int persons = Integer.parseInt(t1.getText());
					int days = Integer.parseInt(t2.getText());
					int total = cost * persons * days;
					if (c2.getSelectedItem().equals("AC")) total += ac * persons * days;
					if (c3.getSelectedItem().equals("Yes")) total += food * persons * days;
					l5.setText("Rs " + total);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		b1.setBounds(50, 470, 120, 30);
		contentPane.add(b1);

		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(e -> {
			try {
				c.s.executeUpdate("insert into bookHotel values('" + l1.getText() + "', '" + c1.getSelectedItem() + "', '" + t1.getText() + "', '" + t2.getText() + "', '" + c2.getSelectedItem() + "', '" + c3.getSelectedItem() + "', '" + l5.getText() + "')");
				JOptionPane.showMessageDialog(null, "Hotel Booked Successfully");
				setVisible(false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		btnBook.setBounds(200, 470, 120, 30);
		contentPane.add(btnBook);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(e -> setVisible(false));
		btnExit.setBounds(350, 470, 120, 30);
		contentPane.add(btnExit);
	}
}
