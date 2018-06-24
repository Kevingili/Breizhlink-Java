package com.sdzee.servlets;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Utilisateur;
@WebServlet("/Raccourcir")
public class Raccourcir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CHAMP_URL = "url";
	public static final String VUE = "";
	public String url_raccourci = "aaa";
	public String ATT_MESSAGES = "messages";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getParameter(CHAMP_URL);
		String password_url = request.getParameter("mdpfield");
		System.out.println("mot de passe = "+password_url);
		System.out.println(url);
		System.out.println("raccourcir ok");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "toto", "toto");
			System.out.println("ok");

			String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			int len = 5;
			StringBuilder sb = new StringBuilder( len );
			for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
			String url_short = sb.toString();
			url_raccourci = url_short;
			
			

			String query1 = "insert into Lien (url_full, url_short, id_user, password_url) values (?, ?, ?, ?)";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, url);
			pst1.setString(2, url_short);
			
			HttpSession session = request.getSession();
			System.out.println("session = "+session);
			int id_current = 0;
			 if ( session.getAttribute( "sessionUtilisateur" ) != null ) {
				 
				Utilisateur user_current = (Utilisateur) session.getAttribute(  "sessionUtilisateur" );
		        id_current = user_current.getId();
		       
		     } 
			 System.out.println("id = "+id_current);
			 pst1.setInt(3, id_current);
			 pst1.setString(4, password_url);
			
			pst1.execute();
			pst1.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}
		//request.setAttribute( ATT_MESSAGES, url_raccourci );
		 request.setAttribute("url",url_raccourci);
		 System.out.println(url_raccourci);
		 //response.sendRedirect( request.getContextPath() + VUE );
		 request.getRequestDispatcher("accesPublic.jsp").forward(request, response); 
		 
	}

}
