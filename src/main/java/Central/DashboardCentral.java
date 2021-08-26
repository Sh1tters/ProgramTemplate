package Central;

import Panels.DashboardPanel;

import javax.swing.*;
import java.awt.*;

public class DashboardCentral {
    private DashboardPanel dashboardPanel;

    public static void main(String username) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DashboardCentral().displayGUI(username);
            }
        });
    }

    public void displayGUI(String username) {
        JFrame frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(-1, -1, -1, -1));
        contentPane.setLayout(new CardLayout());

        dashboardPanel = new DashboardPanel(contentPane, this, username);

        contentPane.add(dashboardPanel, "DashboardPanel");

        frame.getContentPane().add(contentPane, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}