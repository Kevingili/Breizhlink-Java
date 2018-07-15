package com.sdzee.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.bdd.SqlConnection;

/**
 * Servlet implementation class Link
 */
@WebServlet("/Link")
public class Link extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/link.jsp";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String lien_full = "";
		String password = "";
		String lien_short = "";
		int nb_clique = 0;
		int nb_max_clique = 0;
		int expiration = 0;
		boolean verif_clique_is_ok = false;
		boolean verif_expiration = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = SqlConnection.dbConnector(); //Connexion à la base de données

			String query1 = "SELECT * FROM Lien WHERE url_short = ?";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, id);
			
			ResultSet rs = pst1.executeQuery();

			while (rs.next()) {
				lien_full = rs.getString("url_full");
				lien_short = rs.getString("url_short");
				password = rs.getString("password_url");
				nb_clique = rs.getInt("nb_clique");
				expiration = rs.getInt("expiration");
				nb_max_clique = rs.getInt("max_nb_clique");
			}
			rs.close();
			pst1.close();
			
			String query2 = "UPDATE Lien SET nb_clique = nb_clique+1 WHERE url_short = ?";
			PreparedStatement pst2 = conn.prepareStatement(query2);
			pst2.setString(1, lien_short);
			   
			pst2.execute();
			pst2.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}
				
		if(nb_max_clique == 0) {
			verif_clique_is_ok = true;
		} else if(nb_max_clique > nb_clique) {
			verif_clique_is_ok = true;
		}
		

		int unixTimeStamp = (int) (System.currentTimeMillis() / 1000L);
		
		System.out.println("ts actuel "+unixTimeStamp);
		
		if(expiration == 0) {
			verif_expiration = true;
		} else if(unixTimeStamp < expiration) {
			verif_expiration = true;
		}
		
		
	      if(password == "" && verif_clique_is_ok == true && verif_expiration == true) {
	    	  	response.sendRedirect(lien_full);
	      } else {
	    	  request.setAttribute("url", lien_short);
	    	  if(verif_clique_is_ok == false || verif_expiration == false) {
	    		  request.setAttribute("maxclique", "Désolée, cette URL n'est plus disponible");
	    	  }
	    		
	    	  	
	    	  this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	      }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password_url = request.getParameter("password");
		String url_short = request.getParameter("url_short");
		String lien_full = "";
		String password = "";
		

		//Verif en base
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = SqlConnection.dbConnector(); //Connexion à la base de données

			String query1 = "SELECT * FROM Lien WHERE url_short = ?";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, url_short);
			
			ResultSet rs = pst1.executeQuery();

			while (rs.next()) {
				lien_full = rs.getString("url_full");
				password = rs.getString("password_url");
			}
			rs.close();
			pst1.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}

	   
		//Si ok redirection sur url full
		//Si pas ok, redirection page saisi mot de passe
		   if(password.equals(password_url)) {
	    	  	response.sendRedirect(lien_full);
	      } else {
	    	  request.setAttribute("mdpwrong", "mot de passe incorrecte");
	    	  request.setAttribute("url", url_short);
	    	  	this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	      }
		
		
	}

}
