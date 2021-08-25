package Central;

import Panels.LoginPanel;
import Panels.SignUpPanel;

import javax.swing.*;
import java.awt.*;


public class Central {
    private LoginPanel loginPanel;
    private SignUpPanel signupPanel;
    //private DashboardPanel dashboardPanel;

    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Central().displayGUI();
            }
        });
    }

    public void displayGUI() {
        JFrame frame = new JFrame("Advanced Program Template");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new CardLayout());

        loginPanel = new LoginPanel(contentPane, this);
        signupPanel = new SignUpPanel(contentPane, this);

        contentPane.add(loginPanel, "LoginPanel");
        contentPane.add(signupPanel, "SignUpPanel");

        frame.getContentPane().add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}