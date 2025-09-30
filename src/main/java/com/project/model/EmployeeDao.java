package com.project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private final String url = "jdbc:mysql://localhost:3306/roshan_verto";
    private final String user = "root";
    private final String pass = "root";

    public EmployeeDao() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public boolean insertEmployee(Employee e) throws SQLException {
        String q = "INSERT INTO employee (id, name, department, salary) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getDepartment());
            ps.setDouble(4, e.getSalary());

            int rows = ps.executeUpdate();
            System.out.println("Inserted rows: " + rows);
            return rows > 0;
        }
    }

    public Employee searchEmployee(int id) throws SQLException {
        String q = "SELECT id, name, department, salary FROM employee WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee e = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getDouble("salary"));
                    System.out.println("Found employee: " + e);
                    return e;
                }
            }
        }
        return null;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        String q = "SELECT id, name, department, salary FROM employee";
        List<Employee> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(q);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee e = new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"), rs.getDouble("salary"));
                list.add(e);
            }
        }
        return list;
    }

    public boolean updateEmployee(Employee e) throws SQLException {
        String q = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getDepartment());
            ps.setDouble(3, e.getSalary());
            ps.setInt(4, e.getId());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public boolean deleteEmployee(int id) throws SQLException {
        String q = "DELETE FROM employee WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(q)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }
}
