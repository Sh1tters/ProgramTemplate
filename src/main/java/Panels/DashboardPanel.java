package Panels;

import Central.DashboardCentral;
import Handlers.MongoDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;


public class DashboardPanel extends JPanel implements ActionListener {

    private JPanel contentPane;
    JLabel userLabel = new JLabel("null");
    JLabel avatar = new JLabel();
    private int x = 830;
    private int y = 22;
    Image image = null;
    private int widthImage = 38;
    private int heightImage = 38;
    private int xImage = 920;
    private int yImage = 10;


    public DashboardPanel(JPanel panel, DashboardCentral cle, String username) {
        setBorder(BorderFactory.createMatteBorder(60, 0, 0, 0, Color.GRAY)); // create top border
        /** Set username field to current online user */
        userLabel.setText(username);

        /** Get user profile picture */
        String ProfileAvatarURL = MongoDB.getProfileAvatar(username);

        try {
            URL url = new URL(ProfileAvatarURL);
            image = ImageIO.read(url);
        } catch (IOException e) {
        }
        Image ScaledImage = image.getScaledInstance(widthImage, heightImage, Image.SCALE_SMOOTH);
        avatar = new JLabel(new ImageIcon(ScaledImage));


        //TODO make a better algorithm that adds on x value so username will not get caught inside the avatar
        // maybe have a for loop and for every length - 20 of x so username cannot get caught
        if (username.length() <= 7) x = 840;
        if (username.length() >= 6) x = 830;
        contentPane = panel;
        setOpaque(true);
        setBackground(Color.GRAY.brighter());
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();


        /** Avatar click listener */
        avatar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                /** Make dropdown menu appear */
            }
        });
    }


    public void setLayoutManager() {
        setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(x, y, 100, 15);
        userLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));

        avatar.setBounds(xImage, yImage,
                widthImage, heightImage);

    }

    public void addComponentsToContainer() {
        add(userLabel);
        add(avatar, BorderLayout.CENTER);
    }

    public void addActionEvent() {
        add(avatar);
        add(userLabel);
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(1000, 700));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }




}