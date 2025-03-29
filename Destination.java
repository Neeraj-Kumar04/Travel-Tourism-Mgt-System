package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Destination extends JFrame implements Runnable {

    JLabel[] images;
    JLabel caption;
    Thread th;

    public void run() {
        try {
            for (int i = 0; i < images.length; i++) {
                images[i].setVisible(true);
                caption.setText(getDestinationName(i));
                Thread.sleep(2800);
                images[i].setVisible(false);
            }
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Destination() {
        setTitle("Popular Destinations");
        setSize(900, 700);
        setLocationRelativeTo(null); // 🌍 Open in Center
        getContentPane().setBackground(new Color(220, 250, 250));
        setLayout(null);

        th = new Thread(this);

        caption = new JLabel();
        caption.setBounds(50, 550, 900, 70);
        caption.setForeground(Color.BLACK); // 🖤 Changed to Black
        caption.setFont(new Font("Tahoma", Font.PLAIN, 40));
        add(caption);

        String[] imagePaths = {
                "dest1.jpeg", "dest2.jpg", "dest3.jpg", "dest4.jpg", "dest5.jpg",
                "dest6.jpg", "dest7.jpeg", "dest8.jpg", "dest9.jpg", "dest10.jpeg"
        };

        images = new JLabel[imagePaths.length];

        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/" + imagePaths[i]));
            Image img = icon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
            images[i] = new JLabel(new ImageIcon(img));
            images[i].setBounds(0, 0, 900, 700);
            images[i].setVisible(false);
            add(images[i]);
        }

        th.start();
    }

    private String getDestinationName(int index) {
        String[] names = {
                "Paris, France", "Bali, Indonesia", "Santorini, Greece", "Kyoto, Japan", "New York City, USA",
                "Cape Town, South Africa", "Dubai, UAE", "Rome, Italy", "Sydney, Australia", "Machu Picchu, Peru"
        };
        return names[index];
    }

    public static void main(String args[]) {
        new Destination().setVisible(true);
    }
}
