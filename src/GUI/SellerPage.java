package gui;

import services.PropertyService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerPage extends JFrame {
    private JTextField titleField, locationField, priceField;
    private JTextArea descriptionArea;
    private JComboBox<String> typeComboBox;

    public SellerPage(int ownerId) {
        setTitle("Seller Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 20, 100, 30);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(120, 20, 250, 30);
        add(titleField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setBounds(20, 70, 100, 30);
        add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(120, 70, 250, 30);
        add(locationField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 120, 100, 30);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(120, 120, 250, 30);
        add(priceField);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(20, 170, 100, 30);
        add(typeLabel);

        typeComboBox = new JComboBox<>(new String[]{"rental", "sale"});
        typeComboBox.setBounds(120, 170, 250, 30);
        add(typeComboBox);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(20, 220, 100, 30);
        add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(120, 220, 250, 100);
        add(descriptionArea);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 340, 100, 30);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String location = locationField.getText();
                double price = Double.parseDouble(priceField.getText());
                String type = (String) typeComboBox.getSelectedItem();
                String description = descriptionArea.getText();

                PropertyService propertyService = new PropertyService();
                if (propertyService.addProperty(ownerId, title, location, price, type, description)) {
                    JOptionPane.showMessageDialog(null, "Property Listed Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to List Property.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SellerPage(1); // For testing purposes, replace with the actual user ID if needed
    }
}
