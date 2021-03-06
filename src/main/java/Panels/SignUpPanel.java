package Panels;

import Central.Central;
import Handlers.Handler;
import Handlers.MongoDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SignUpPanel extends JPanel implements ActionListener {

    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel verifyPasswordLabel = new JLabel("VERIFY PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField verifyPasswordField = new JPasswordField();
    JButton signupButton = new JButton("SIGNUP");
    JButton backButton = new JButton("BACK");
    JCheckBox showPassword = new JCheckBox("Show Password");
    MongoDB mongo = new MongoDB();
    Handler handler = new Handler();
    private JPanel contentPane;

    public SignUpPanel(JPanel panel, Central cle) {
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
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        verifyPasswordLabel.setBounds(40, 290, 100, 30);
        passwordField.setBounds(150, 220, 150, 30);
        userTextField.setBounds(150, 150, 150, 30);
        verifyPasswordField.setBounds(150, 290, 150, 30);
        showPassword.setBounds(150, 320, 150, 30);
        signupButton.setBounds(50, 350, 100, 30);
        backButton.setBounds(200, 350, 100, 30);
    }

    public void addComponentsToContainer() {
        add(userLabel);
        add(passwordLabel);
        add(verifyPasswordLabel);
        add(userTextField);
        add(passwordField);
        add(verifyPasswordField);
        add(showPassword);
        add(backButton);
        add(signupButton);
    }

    public void addActionEvent() {
        backButton.addActionListener(this);
        signupButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(500, 500));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of SIGNUP button
        if (e.getSource() == signupButton) {
            String username = userTextField.getText();
            String password = passwordField.getText();
            String vpwdText = verifyPasswordField.getText();

            /** Check if any field has not being filled out */
            if (Objects.equals(username, "") || Objects.equals(password, "") || Objects.equals(vpwdText, "")) {
                JOptionPane.showMessageDialog(this, "Missing required field(s)");

            } else if (!Objects.equals(password, vpwdText)) {
                JOptionPane.showMessageDialog(this, "Password did not match!");
            } else {
                try {

                    /** Check if password is a strong password */
                    boolean IsPasswordGood = handler.IsPasswordGood(password);
                    if (!IsPasswordGood) {
                        JOptionPane.showMessageDialog(this, "Bad password\n" +
                                "Must contain:\n" +
                                "At least one upper case English Letter,\n" +
                                "At least one lower case English Letter,\n" +
                                "At least one digit,\n" +
                                "At least one special character,\n" +
                                "Minimum eight in length");
                    } else if (IsPasswordGood) {
                        /** Check if username is already in use */
                        boolean UsernameSuccess = mongo.ValidationSignUp(username);
                        if (UsernameSuccess) {
                            /**SHA is the Secure Hash Algorithm. It uses a cryptographic function that takes up the 32-bit
                             * plain-text password and converts it into a fixed size 256-bit hash value.
                             * This hashing technique is implemented using the MessageDiagest class of
                             * java.security package.
                             * It is a one-way encryption technique. Once the passphrase is encrypted it cannot be decrypted back.
                             * */
                            password = Handler.toHexString(handler.PasswordEncryption(password));
                            /** Create new user */
                            mongo.SignNewUser(username, password);

                            /** Success */
                            JOptionPane.showMessageDialog(this, "Successfully created your account (" + username + ")");

                            /** Load new screen on response */
                            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                            cardLayout.show(contentPane, "LoginPanel");
                        } else {
                            /** name already taken */
                            JOptionPane.showMessageDialog(this, "Name is already in use!");
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

        //Coding Part of BACK button
        if (e.getSource() == backButton) {
            /** Go to Panels.LoginPanel */
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "LoginPanel");
        }

        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                // replace all * with real characters.
                passwordField.setEchoChar((char) 0);
            } else {
                // replace all characters with *.
                passwordField.setEchoChar('*');
            }


        }
    }
}
