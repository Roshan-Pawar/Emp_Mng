
### Prerequites  
- Eclipse IDE
- MySQL Database

## Follow these steps to run the project locally:
1. Clone the repository in Eclipse (clone git repository) tab.
  
2. Add Apache Tomcat server v9.0 in the same workspace
  > download from here <a href="https://drive.google.com/drive/folders/1d3Z7PGqJHsYd9YLQ7JUmF3b265kHu2ep?usp=drive_link">Apache Tomcat server v9.0</a>.  

3.Change user & pass in EmployeeDao.java (default is root) and perform following queries.  
  `CREATE DATABASE roshan_verto;`  
    `CREATE TABLE employee (
      id INT PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      department VARCHAR(50) NOT NULL, 
      salary DOUBLE NOT NULL
  );`  
- Update your database username and password in EmployeeDao.java (default: root).
  
4. Run the index.jsp.

