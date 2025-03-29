package travel.management.system;

import java.awt.*;
import javax.swing.*;

public class Splash {
    public static void main(String[] args) {
        SplashFrame f1 = new SplashFrame();
        f1.setVisible(true);

        try {
            // Get screen dimensions
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            int width = 200, height = 150; // Start small

            // Expand smoothly to full screen
            for (int i = 0; i <= 40; i++) { // 40 steps for smooth effect
                width += (screenWidth - 200) / 40;
                height += (screenHeight - 150) / 40;

                int xPos = (screenWidth - width) / 2;
                int yPos = (screenHeight - height) / 2;

                f1.setLocation(xPos, yPos);
                f1.setSize(width, height);

                Thread.sleep(15); // Adjust sleep for smoother animation
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SplashFrame extends JFrame implements Runnable {
    Thread t1;

    SplashFrame() {
        setLayout(new BorderLayout());

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // Load and scale the image dynamically to fit full screen
        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/splash.jpg"));
        Image img = imgIcon.getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        JLabel l1 = new JLabel(new ImageIcon(img));

        add(l1, BorderLayout.CENTER);

        setUndecorated(true);
        setSize(200, 150); // Start small
        setLocationRelativeTo(null); // Center window initially

        t1 = new Thread(this);
        t1.start();
    }

    public void run() {
        try {
            Thread.sleep(4000); // Show splash for 4 seconds
            this.setVisible(false);

            Login l = new Login();
            l.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
