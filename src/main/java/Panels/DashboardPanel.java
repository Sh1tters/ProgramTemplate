package Panels;

import Central.DashboardCentral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DashboardPanel extends JPanel implements ActionListener {

    private JPanel contentPane;
    JLabel userLabel = new JLabel("null");
    private int x = 830;
    private int y = 22;


    public DashboardPanel(JPanel panel, DashboardCentral cle, String username) {
        setBorder(BorderFactory.createMatteBorder(60, 0, 0, 0, Color.GRAY)); // create top border
        userLabel.setText(username);
        if (username.length() <= 7) x = 840;
        if (username.length() >= 6) x = 830;
        contentPane = panel;
        setOpaque(true);
        setBackground(Color.GRAY.brighter());
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }


    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(x, y, 100, 15);
        userLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
    }

    public void addComponentsToContainer() {
        add(userLabel);
    }

    public void addActionEvent() {

    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(1000, 700));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }




}