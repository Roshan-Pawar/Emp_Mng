package com.project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
	
	String url = "jdbc:mysql://localhost:3306/roshan_jdbc", user = "root", pass = "root", q;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs;
	
	public EmployeeDao() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public boolean insertEmployee(Employee e) throws SQLException {
		String q = "insert into Employee values(?, ?, ?, ?, ?, ?)";
		ps = con.prepareStatement(q);

		ps.setInt(1, e.getId());
		ps.setString(2, e.getName());
		ps.setString(3, e.getDepartment());
		ps.setDouble(4, e.getSalary());
		ps.setDate(5, e.getDob());
		ps.setDate(6, e.getJdate());
		
		int rows = ps.executeUpdate();

		if (rows > 0)
			return true;
		else
			return false;
	}
	
	public ResultSet searchEmployee(int i) throws SQLException {
		String q = "select * from employee where id = ?";
		ps = con.prepareStatement(q);

		ps.setInt(1, i);

		rs = ps.executeQuery();
		
		return rs;
	}
	
	public ResultSet getAllEmployees() throws SQLException {
		String q = "select * from employee";
		ps = con.prepareStatement(q);
		
		rs = ps.executeQuery();
		
		return rs;
	}
	
	public boolean deleteEmployee(int i) throws SQLException {
		String q = "delete from employee where id = ?";
		ps = con.prepareStatement(q);

		ps.setInt(1, i);

		int rows = ps.executeUpdate();

		if (rows > 0)
			return true;
		else
			return false;
	}
	

}
