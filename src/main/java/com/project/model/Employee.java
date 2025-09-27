package com.project.model;

import java.sql.Date;

public class Employee {
	
	private int id;
	private String name, department;
	private double salary;
	private Date dob, jdate;
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Employee(int id, String name, String department, double salary, Date dob, Date jdate) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.dob = dob;
		this.jdate = jdate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public Date getJdate() {
		return jdate;
	}


	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + ", dob="
				+ dob + ", jdate=" + jdate + "]";
	}
	
}
