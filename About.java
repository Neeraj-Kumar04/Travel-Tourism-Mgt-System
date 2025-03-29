package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class About extends JFrame implements ActionListener {

    JButton exitButton;
    JLabel titleLabel, developedByLabel;
    JTextArea aboutText;
    String aboutContent;
    float opacity = 0f; // Initial transparency for fade-in effect

    public About() {
        // Set title and layout
        setTitle("About - Travel and Tourism Management System");
        setLayout(null);
        setSize(650, 550);
        setLocationRelativeTo(null); // Center the window
        setUndecorated(false); // Allow window decorations

        // Create blue gradient background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 51, 102));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 650, 550);
        add(backgroundPanel);

        // Title Label
        titleLabel = new JLabel("About Project");
        titleLabel.setBounds(200, 20, 300, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        backgroundPanel.add(titleLabel);

        // Developed By Label
        developedByLabel = new JLabel("Developed by Neeraj");
        developedByLabel.setBounds(220, 60, 300, 30);
        developedByLabel.setForeground(Color.LIGHT_GRAY);
        developedByLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        backgroundPanel.add(developedByLabel);

        // About Content (Expanded)
        aboutContent = "The Travel and Tourism Management System is an advanced software\n"
                + "solution designed to simplify travel agency operations by automating\n"
                + "booking management, package handling, and customer engagement.\n\n"
                + "Key Features:\n"
                + "✔ Comprehensive Travel Booking System\n"
                + "✔ User-Friendly Interface with a Modern UI Design\n"
                + "✔ Secure Payment Integration for Seamless Transactions\n"
                + "✔ Efficient Tour Package and Hotel Reservation Management\n"
                + "✔ Automated Customer Notifications and Alerts\n"
                + "✔ Detailed Reporting and Analytics for Business Insights\n"
                + "✔ Scalable Architecture for Future Enhancements\n"
                + "✔ 24/7 Support and Assistance for Customers\n\n"
                + "This project ensures an intuitive and hassle-free experience for both\n"
                + "customers and travel agencies, streamlining administrative tasks and\n"
                + "enhancing overall efficiency in travel management.";

        aboutText = new JTextArea(aboutContent);
        aboutText.setBounds(50, 100, 550, 300);
        aboutText.setFont(new Font("Arial", Font.PLAIN, 16));
        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setBounds(50, 100, 550, 300);
        backgroundPanel.add(scrollPane);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(275, 420, 100, 35);
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(new Color(255, 69, 0));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitButton.addActionListener(this);

        // Hover Effect for Exit Button
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(new Color(200, 0, 0)); // Darker red on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(new Color(255, 69, 0)); // Original color
            }
        });

        backgroundPanel.add(exitButton);

        // Fade-in effect
        fadeIn();

        // Make window visible
        setVisible(true);
    }

    // Fade-in effect using Timer
    private void fadeIn() {
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (opacity < 1f) {
                    opacity += 0.05f; // Increase opacity gradually
                    setOpacity(opacity);
                } else {
                    ((Timer) e.getSource()).stop(); // Stop timer when fully visible
                }
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        dispose(); // Close window on exit button click
    }

    public static void main(String[] args) {
        new About();
    }
}
