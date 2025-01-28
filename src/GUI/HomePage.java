package gui;

import services.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HomePage extends JFrame {
    private static String loggedInUserRole = null;

    public HomePage() {
        setTitle("Home Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel("images/re.jpg");
        setContentPane(backgroundPanel);

        JPanel overlayPanel = new JPanel(new GridBagLayout());
        overlayPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridwidth = 2;
        JLabel headerLabel = new JLabel("Welcome To Your Home", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        headerLabel.setForeground(Color.magenta);
        gbc.gridx = 0;
        gbc.gridy = 0;
        overlayPanel.add(headerLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        JButton registerButton = createStyledButton("Register");
        overlayPanel.add(registerButton, gbc);

        gbc.gridx++;
        JButton sellerPageButton = createStyledButton("Seller Page");
        overlayPanel.add(sellerPageButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton buyerPageButton = createStyledButton("Buyer Page");
        overlayPanel.add(buyerPageButton, gbc);

        gbc.gridx++;
        JButton agentPageButton = createStyledButton("Agent Page");
        overlayPanel.add(agentPageButton, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        JButton logoutButton = createStyledButton("Logout");
        overlayPanel.add(logoutButton, gbc);

        registerButton.addActionListener(e -> new RegistrationFrame());

        sellerPageButton.addActionListener(e -> {
            if (loggedInUserRole == null) {
                promptLogin();
            }
            if ("seller".equals(loggedInUserRole)) {
                new SellerPage(1);
            } else {
                JOptionPane.showMessageDialog(null, "You do not have access to this page.");
            }
        });

        buyerPageButton.addActionListener(e -> {
            if (loggedInUserRole == null) {
                promptLogin();
            }
            if ("buyer".equals(loggedInUserRole)) {
                new BuyerPage();
            } else {
                JOptionPane.showMessageDialog(null, "You do not have access to this page.");
            }
        });

        agentPageButton.addActionListener(e -> {
            if (loggedInUserRole == null) {
                promptLogin();
            }
            if ("agent".equals(loggedInUserRole)) {
                new AgentPage();
            } else {
                JOptionPane.showMessageDialog(null, "You do not have access to this page.");
            }
        });

        logoutButton.addActionListener(e -> {
            loggedInUserRole = null;
            JOptionPane.showMessageDialog(null, "Logged out successfully.");
        });

        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(overlayPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(50, 150, 250));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    private void promptLogin() {
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] loginFields = {
                "Email:", emailField,
                "Password:", passwordField
        };
        int option = JOptionPane.showConfirmDialog(null, loginFields, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            UserService userService = new UserService();
            String role = userService.loginUser(email, password);

            if (role != null) {
                loggedInUserRole = role;
                JOptionPane.showMessageDialog(null, "Login Successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        new HomePage();
    }
}

class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
