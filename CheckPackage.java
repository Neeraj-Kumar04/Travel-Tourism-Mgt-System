package travel.management.system;

import java.awt.*;
import javax.swing.*;

public class CheckPackage extends JFrame {
    public static void main(String[] args) {
        new CheckPackage().setVisible(true);
    }

    CheckPackage() {
        setTitle("Check Travel Packages");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[][] packages = {
                {"package1.jpg", "GOLD PACKAGE", "6 days and 7 Nights", "Airport Assistance", "Half Day City Tour",
                        "Welcome drinks on Arrival", "Daily Buffet", "Full Day 3 Island Cruise", "English Speaking Guide",
                        "BOOK NOW", "Summer Special", "Rs 12000 only"},
                {"package2.jpg", "SILVER PACKAGE", "4 days and 3 Nights", "Toll Free and Entrance Free Tickets",
                        "Meet and Greet at Airport", "Welcome drinks on Arrival", "Night Safari", "Full Day 3 Island Cruise",
                        "Cruise with Dinner", "BOOK NOW", "Winter Special", "Rs 25000 only"},
                {"package3.jpg", "BRONZE PACKAGE", "6 days and 5 Nights", "Return Airfare",
                        "Free Clubbing, Horse Riding & other Games", "Welcome drinks on Arrival", "Daily Buffet",
                        "Stay in 5 Star Hotel", "BBQ Dinner", "BOOK NOW", "Winter Special", "Rs 32000 only"}
        };

        JTabbedPane tabbedPane = new JTabbedPane();
        for (String[] pack : packages) {
            JPanel panel = createPackagePanel(pack);
            tabbedPane.addTab(pack[1], panel);
        }

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createPackagePanel(String[] pack) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 51, 102));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(null);

        JPanel cardPanel = new JPanel();
        cardPanel.setBounds(20, 20, 840, 520);
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 255), 2));
        panel.add(cardPanel);

        JLabel titleLabel = new JLabel(pack[1]);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 28));
        titleLabel.setBounds(50, 20, 350, 40);
        cardPanel.add(titleLabel);

        ImageIcon icon = new ImageIcon(getClass().getResource("/travel/management/system/icons/" + pack[0]));
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(400, 50, 400, 300);
        cardPanel.add(imageLabel);

        int yPosition = 70;
        for (int i = 2; i < 9; i++) {
            JLabel featureLabel = new JLabel("• " + pack[i]);
            featureLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            featureLabel.setBounds(50, yPosition, 300, 25);
            featureLabel.setForeground((i % 2 == 0) ? Color.BLUE : Color.RED);
            cardPanel.add(featureLabel);
            yPosition += 30;
        }

        JButton bookNowButton = new JButton(pack[9]);
        bookNowButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookNowButton.setBackground(new Color(30, 144, 255));
        bookNowButton.setForeground(Color.WHITE);
        bookNowButton.setBounds(50, yPosition + 20, 140, 40);
        bookNowButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cardPanel.add(bookNowButton);

        JLabel specialOfferLabel = new JLabel(pack[10]);
        specialOfferLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        specialOfferLabel.setBounds(50, yPosition + 70, 200, 30);
        cardPanel.add(specialOfferLabel);

        JLabel priceLabel = new JLabel(pack[11]);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 22));
        priceLabel.setForeground(Color.RED);
        priceLabel.setBounds(600, 460, 300, 40);
        cardPanel.add(priceLabel);

        return panel;
    }
}
