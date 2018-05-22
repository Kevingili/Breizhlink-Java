package com.sdzee.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Suppression
 */
@WebServlet("/Suppression")
public class Suppression extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/restriction";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Suppression() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		System.out.println(id);
		 try {
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "toto", "toto");
				//System.out.println("ok");

				String query = "DELETE FROM Utilisateur WHERE id = ?";
				PreparedStatement pst = conn.prepareStatement(query);
			      pst.setString(1, id);
				pst.executeUpdate();
				pst.close();

			} catch (Exception e) {
				System.out.println("pas ok " + e);
			}
		 response.sendRedirect( request.getContextPath() + VUE );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
