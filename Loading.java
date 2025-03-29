package travel.management.system;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame implements Runnable {

    private JProgressBar progressBar;
    private String username;
    private Thread th;

    public static void main(String[] args) {
        new Loading("User").setVisible(true);
    }

    public void setUploading() {
        th.start();
    }

    public void run() {
        try {
            for (int i = 0; i <= 100; i++) {
                progressBar.setValue(i);
                Thread.sleep(50);
            }
            setVisible(false);
            new Home(username).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Loading(String username) {
        this.username = username;
        th = new Thread(this);

        setTitle("Loading - Travel Management System");
        setSize(500, 300);
        setLocationRelativeTo(null); // Center the window
        setUndecorated(true);
        setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 144, 255)); // DodgerBlue
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Welcome to Travel & Tourism");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(90, 30, 400, 30);
        contentPane.add(titleLabel);

        JLabel subText = new JLabel("Loading, please wait...");
        subText.setFont(new Font("Poppins", Font.BOLD, 16));
        subText.setForeground(Color.WHITE);
        subText.setBounds(160, 80, 200, 25);
        contentPane.add(subText);

        // Modern Progress Bar
        progressBar = new JProgressBar();
        progressBar.setBounds(100, 130, 300, 20);
        progressBar.setForeground(new Color(0, 255, 127)); // Spring Green
        progressBar.setBackground(Color.WHITE);
        progressBar.setBorder(null);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(progressBar);

        // Exit Button
        JButton exitBtn = new JButton("X");
        exitBtn.setBounds(450, 10, 40, 30);
        exitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBorder(null);
        exitBtn.setFocusPainted(false);
        exitBtn.addActionListener(e -> System.exit(0));
        contentPane.add(exitBtn);

        setUploading();
    }
}
