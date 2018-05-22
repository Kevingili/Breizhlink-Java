package com.sdzee.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Link
 */
@WebServlet("/Link")
public class Link extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Passage dans le raccourci");
		String id = request.getParameter("id");
		System.out.println(id);
		
		String lien_full = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "toto", "toto");
			System.out.println("ok");

			String query1 = "SELECT * FROM Lien WHERE url_short = ?";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, id);
			
			ResultSet rs = pst1.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("url_full"));
				lien_full = rs.getString("url_full");
			}
			rs.close();
			pst1.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}
	       
	        
	        System.out.println("Lien =" +lien_full);
		
		response.sendRedirect(lien_full);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
