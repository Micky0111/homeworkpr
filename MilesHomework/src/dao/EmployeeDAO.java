package dao;

import model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    private final Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    // 員工登入
    public Employee login(int employeeId, String password) {
        String query = "SELECT * FROM Employee WHERE employeeid = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                    rs.getInt("employeeid"),
                    rs.getString("employeename"),
                    rs.getString("employeecontact"),
                    rs.getString("department"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}