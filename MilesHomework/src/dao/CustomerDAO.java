package dao;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private final Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    // 註冊
    public void registerCustomer(Customer customer) {
        String query = "INSERT INTO Customer (name, phone, cardnumber, miles, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getCardNumber());
            stmt.setInt(4, customer.getMiles());
            stmt.setString(5, customer.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 登入
    public Customer login(String cardNumber, String password) {
        String query = "SELECT * FROM Customer WHERE cardnumber = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cardNumber);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("cardnumber"),
                    rs.getInt("miles"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 卡號找客戶
    public Customer findCustomerByCardNumber(String cardNumber) {
        String query = "SELECT * FROM Customer WHERE cardnumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("cardnumber"),
                    rs.getInt("miles"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 更新客戶里程
    public void updateCustomerMiles(Customer customer) {
        String query = "UPDATE Customer SET miles = ? WHERE cardnumber = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customer.getMiles());
            stmt.setString(2, customer.getCardNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}