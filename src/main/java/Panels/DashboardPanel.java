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
    JButton button = new JButton("test");
    private int x = 1060;
    private int y = 22;
    Image image = null;
    private int widthImage = 38;
    private int heightImage = 38;
    private int xImage = 1140;
    private int yImage = 11;


    public DashboardPanel(JPanel panel, DashboardCentral cle, String username) {
        setBorder(BorderFactory.createMatteBorder(60, 0, 0, 0, Color.GRAY)); // create top border

        for (int i = 0; i < username.length(); i++) {
            x = x - 1;
        }

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


        setOpaque(false);
        setBackground(Color.GRAY.brighter());
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();


        /** Mouse event listener for avatar */
        AvatarListener avatarListener = new AvatarListener();
        avatar.addMouseListener(avatarListener);
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
        return (new Dimension(1200, 800));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}

class AvatarListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        // Do whatever you want when image is clicked
    }
}
