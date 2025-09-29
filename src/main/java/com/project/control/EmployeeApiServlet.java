package com.project.control;

import com.google.gson.Gson;
import com.project.model.Employee;
import com.project.model.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


public class EmployeeApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeeDao dao;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dao = new EmployeeDao();
            System.out.println("EmployeeDao initialized successfully.");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL Driver not found.", e);
        }
    }

    // GET /api/employees or /api/employees/{id}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        System.out.println("doGet called, pathInfo=" + pathInfo);

        try (PrintWriter out = response.getWriter()) {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Employee> list = dao.getAllEmployees();
                out.write(gson.toJson(list));
            } else {
                int id = Integer.parseInt(pathInfo.substring(1));
                Employee e = dao.searchEmployee(id);
                if (e != null) {
                    out.write(gson.toJson(e));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.write("{\"error\":\"Employee not found\"}");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + ex.getMessage() + "\"}");
        }
    }

    // POST /api/employees
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Employee e = gson.fromJson(request.getReader(), Employee.class);
        System.out.println("doPost called, Employee=" + e);

        try {
            boolean result = dao.insertEmployee(e);
            if (result) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("{\"success\":true}");
            } else {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().write("{\"success\":false, \"error\":\"Insert failed\"}");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\":false, \"error\":\"" + ex.getMessage() + "\"}");
        }
    }

    // PUT /api/employees/{id}
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        System.out.println("doput pathinfo"+ pathInfo);
        int id = Integer.parseInt(pathInfo.substring(1));
        Employee e = gson.fromJson(request.getReader(), Employee.class);
        e.setId(id);
        System.out.println("doPut called, Employee=" + e);

        try {
            boolean result = dao.updateEmployee(e);
            if (result) {
                response.getWriter().write("{\"success\":true}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"success\":false, \"error\":\"Employee not found\"}");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\":false, \"error\":\"" + ex.getMessage() + "\"}");
        }
    }

    // DELETE /api/employees/{id}
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        int id = Integer.parseInt(pathInfo.substring(1));
        System.out.println("doDelete called with id=" + id);

        try {
            boolean result = dao.deleteEmployee(id);
            if (result) {
                response.getWriter().write("{\"success\":true}");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"success\":false, \"error\":\"Employee not found\"}");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\":false, \"error\":\"" + ex.getMessage() + "\"}");
        }
    }
}
