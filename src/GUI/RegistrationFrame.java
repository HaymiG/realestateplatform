package gui;

import services.UserService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFrame extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public RegistrationFrame() {
        setTitle("Register");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 30);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 70, 100, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(120, 70, 200, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 120, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 120, 200, 30);
        add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(20, 170, 100, 30);
        add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"buyer", "seller", "agent"});
        roleComboBox.setBounds(120, 170, 200, 30);
        add(roleComboBox);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 220, 120, 30);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                UserService userService = new UserService();
                if (userService.registerUser(name, email, password, role)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed. Try again.");
                }
            }
        });

        setVisible(true);
    }
}
