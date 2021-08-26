package Panels;

import Central.DashboardCentral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DashboardPanel extends JPanel implements ActionListener {

    private JPanel contentPane;
    JLabel userLabel = new JLabel("null");


    public DashboardPanel(JPanel panel, DashboardCentral cle, String username) {
        userLabel.setText(username);

        contentPane = panel;
        setOpaque(true);
        setBackground(Color.lightGray);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }


    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(830, 20, 100, 15);
        userLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
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