package services;

import db.DatabaseConnection;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PropertyService {

    // Method to add a property (existing)
    public boolean addProperty(int ownerId, String title, String location, double price, String type, String description) {
        String query = "INSERT INTO properties (owner_id, title, location, price, type, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ownerId);
            stmt.setString(2, title);
            stmt.setString(3, location);
            stmt.setDouble(4, price);
            stmt.setString(5, type);
            stmt.setString(6, description);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get available properties for buyers
    public void getAvailableProperties(DefaultTableModel model) {
        String query = "SELECT id, title, location, price, type FROM properties WHERE status = 'available'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("location"),
                        rs.getDouble("price"),
                        rs.getString("type")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get properties with owner email for agents
    public void getPropertiesWithOwnerEmail(DefaultTableModel model) {
        String query = "SELECT p.id, p.title, p.location, p.price, p.type, u.email FROM properties p JOIN users u ON p.owner_id = u.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("location"),
                        rs.getDouble("price"),
                        rs.getString("type"),
                        rs.getString("email")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get inquiries related to properties
    public void getPropertyInquiries(DefaultTableModel model) {
        String query = "SELECT i.id, i.property_id, i.inquiry FROM inquiries i JOIN properties p ON i.property_id = p.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("property_id"),
                        rs.getString("inquiry")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
