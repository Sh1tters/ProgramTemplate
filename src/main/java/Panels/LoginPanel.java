package Panels;

import Central.Central;
import Handlers.Handler;
import Handlers.MongoDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class LoginPanel extends JPanel implements ActionListener {
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton signupButton = new JButton("SIGNUP");
    JCheckBox showPassword = new JCheckBox("Show Password");
    MongoDB mongo = new MongoDB();
    Handler handler = new Handler();
    private JPanel contentPane;


    public LoginPanel(JPanel panel, Central cle) {
        /** Get background */
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
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        signupButton.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        add(userLabel);
        add(passwordLabel);
        add(userTextField);
        add(passwordField);
        add(showPassword);
        add(loginButton);
        add(signupButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public Dimension getPreferredSize() {
        return (new Dimension(500, 500));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {

            String username = userTextField.getText();
            String password = passwordField.getText();

            if (Objects.equals(username, "") || Objects.equals(password, "")) {
                JOptionPane.showMessageDialog(this, "Missing required field(s)");
                // make a red circle behind the missing fields
                // code goes here - either have 4 different if statements or then we check a smarter way
                // TODO add a error marker on the field that is incorrect
            } else {
                try {
                    boolean success = mongo.ValidationLogin(username, password);
                    if (success) {
                        /** Login user */
                        JOptionPane.showMessageDialog(this, "Login Successful");
                        //handler.Login();
                        /** Go to Panels.DashboardPanel */
                        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                        cardLayout.show(contentPane, "DashboardPanel");
                    } else {
                        /** Invalid credentials */
                        /** Errors made from ValidationSignUp */
                        JOptionPane.showMessageDialog(this, "Invalid username/password");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        }
        //Coding Part of SIGNUP button
        if (e.getSource() == signupButton) {
            /** Go to SignUpPanel */
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "SignUpPanel");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }
}
