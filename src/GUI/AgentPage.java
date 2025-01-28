package gui;

import services.PropertyService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgentPage extends JFrame {
    private JTable propertiesTable;
    private JTable inquiriesTable;

    public AgentPage() {
        setTitle("Agent Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Properties Tab
        JPanel propertiesPanel = new JPanel(new BorderLayout());
        propertiesTable = new JTable();
        JScrollPane propertiesScrollPane = new JScrollPane(propertiesTable);
        propertiesPanel.add(propertiesScrollPane, BorderLayout.CENTER);

        loadProperties();

        tabbedPane.add("Properties", propertiesPanel);

        // Inquiries Tab
        JPanel inquiriesPanel = new JPanel(new BorderLayout());
        inquiriesTable = new JTable();
        JScrollPane inquiriesScrollPane = new JScrollPane(inquiriesTable);
        inquiriesPanel.add(inquiriesScrollPane, BorderLayout.CENTER);

        loadInquiries();

        tabbedPane.add("Inquiries", inquiriesPanel);

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadProperties() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Title", "Location", "Price", "Type", "Email"}, 0);
        PropertyService propertyService = new PropertyService();
        propertyService.getPropertiesWithOwnerEmail(model);
        propertiesTable.setModel(model);
    }

    private void loadInquiries() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Inquiry ID", "Property ID", "Name", "Email", "Message"}, 0);
        PropertyService propertyService = new PropertyService();
        propertyService.getPropertyInquiries(model);
        inquiriesTable.setModel(model);
    }

    public static void main(String[] args) {
        new AgentPage();
    }
}
