package Panels;

import Central.Central;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DashboardPanel extends JPanel implements ActionListener {

    private JPanel contentPane;


    public DashboardPanel(JPanel panel, Central cle) {
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
    }

    public void addComponentsToContainer() {
    }

    public void addActionEvent() {
    }


    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(1280, 768));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}