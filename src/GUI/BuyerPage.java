package gui;

import services.PropertyService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyerPage extends JFrame {
    private JTable propertiesTable;

    public BuyerPage() {
        setTitle("Buyer Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Available Properties");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerLabel, BorderLayout.NORTH);

        propertiesTable = new JTable();
        propertiesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        propertiesTable.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(propertiesTable);
        add(scrollPane, BorderLayout.CENTER);

        loadProperties();

        JButton inquireButton = new JButton("Inquire");
        inquireButton.setFont(new Font("Arial", Font.PLAIN, 18));
        inquireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = propertiesTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String propertyId = propertiesTable.getValueAt(selectedRow, 0).toString();
                    openInquiryDialog(propertyId);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a property to inquire about.");
                }
            }
        });
        add(inquireButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadProperties() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Title", "Location", "Price", "Type"}, 0);
        PropertyService propertyService = new PropertyService();
        propertyService.getAvailableProperties(model);
        propertiesTable.setModel(model);
    }

    private void openInquiryDialog(String propertyId) {
        JDialog inquiryDialog = new JDialog(this, "Property Inquiry", true);
        inquiryDialog.setSize(400, 300);
        inquiryDialog.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel messageLabel = new JLabel("Message:");
        JTextArea messageArea = new JTextArea();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String message = messageArea.getText();

                // Handle the inquiry details (e.g., send email, save to database)
                // ...

                JOptionPane.showMessageDialog(null, "Inquiry submitted successfully!");
                inquiryDialog.dispose();
            }
        });

        inquiryDialog.add(nameLabel);
        inquiryDialog.add(nameField);
        inquiryDialog.add(emailLabel);
        inquiryDialog.add(emailField);
        inquiryDialog.add(messageLabel);
        inquiryDialog.add(new JScrollPane(messageArea));
        inquiryDialog.add(submitButton);

        inquiryDialog.setLocationRelativeTo(this);
        inquiryDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new BuyerPage();
    }
}
