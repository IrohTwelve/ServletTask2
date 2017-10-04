package com.osoleksandr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class MassageFromDB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1> Massage form Data Base  </h1>");
        out.println("<h1> " + printMassage() + " </h1>");
    }

    public static String printMassage() {

        String sql = "SELECT * FROM USERS WHERE USERNAME = 'Vova123'";
        Statement statement;
        ResultSet resultSet;
        String result = "";

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String email = resultSet.getString("EMAIL");
                result = id + " " + username + " " + password + " " + email;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
