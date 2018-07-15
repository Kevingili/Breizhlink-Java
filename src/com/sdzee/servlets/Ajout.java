package com.sdzee.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.bdd.SqlConnection;
import com.sdzee.beans.Utilisateur;

/**
 * Servlet implementation class Ajout
 */
@WebServlet("/Ajout")
public class Ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/connexion";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_MDP = "mdp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter(CHAMP_EMAIL);
		String mdp = request.getParameter(CHAMP_MDP);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = SqlConnection.dbConnector(); //Connexion à la base de données

			String query1 = "insert into Utilisateur (email, password) values (?, ?)";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, email);
			pst1.setString(2, mdp);
			   
			pst1.execute();
			pst1.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}
		 response.sendRedirect( request.getContextPath() + VUE );
	}

}
