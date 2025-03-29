package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class CheckHotels extends JFrame implements Runnable {

    JLabel[] hotelImages;
    JLabel caption;
    Thread th;

    public void run() {
        try {
            for (int i = 0; i < hotelImages.length; i++) {
                hotelImages[i].setVisible(true);
                caption.setText(getHotelName(i));
                Thread.sleep(2800);
                hotelImages[i].setVisible(false);
            }
            this.setVisible(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public CheckHotels() {
        setTitle("Check Hotels"); // 🏨 Added Title
        setSize(900, 700);
        setLocationRelativeTo(null); // 🌍 Open in Center
        getContentPane().setBackground(new Color(220, 250, 250));
        setLayout(null);

        th = new Thread(this);

        caption = new JLabel();
        caption.setBounds(50, 550, 900, 70);
        caption.setForeground(Color.WHITE);
        caption.setFont(new Font("Tahoma", Font.PLAIN, 40));
        add(caption);

        String[] imagePaths = {
                "hotel1.jpg", "hotel2.jpg", "hotel3.jpg", "hotel4.jpg",
                "hotel5.jpg", "hotel6.jpg", "hotel7.jpg", "hotel8.jpg",
                "hotel9.jpg", "hotel10.jpg"
        };

        hotelImages = new JLabel[imagePaths.length];

        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/" + imagePaths[i]));
            Image img = icon.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
            hotelImages[i] = new JLabel(new ImageIcon(img));
            hotelImages[i].setBounds(0, 0, 900, 700);
            hotelImages[i].setVisible(false);
            add(hotelImages[i]);
        }

        th.start();
    }

    private String getHotelName(int index) {
        String[] names = {
                "JW Marriott Hotel", "Mandarin Oriental Hotel", "Four Seasons Hotel", "Radisson Hotel",
                "Classio Hotel", "The Bay Club Hotel", "Breeze Blows Hotel", "Quick Stop Hotel",
                "Happy Mornings Motel", "Moss View Hotel"
        };
        return names[index];
    }

    public static void main(String args[]) {
        new CheckHotels().setVisible(true);
    }
}
