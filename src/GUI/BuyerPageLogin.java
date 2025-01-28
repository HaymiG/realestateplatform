package gui;

import services.UserService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyerPageLogin extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public BuyerPageLogin() {
        setTitle("Buyer Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 20, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 20, 150, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 70, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 150, 30);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(90, 120, 100, 30);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                UserService userService = new UserService();

                String role = userService.loginUser(email, password);
                if ("buyer".equals(role)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose();
                    new BuyerPage();
                } else if (role != null) {
                    JOptionPane.showMessageDialog(null, "Access Denied: Only buyers can access this page.");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BuyerPageLogin();
    }
}
