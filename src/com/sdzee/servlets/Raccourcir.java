package com.sdzee.servlets;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.lang.Math.toIntExact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.bdd.SqlConnection;
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
		String password_url = "";
		password_url = request.getParameter("mdpfield");
		String maxfield = request.getParameter("maxfield");
		String expiration = request.getParameter("expiration");
		
		int date_expiration = 0;
		if(expiration != "" && expiration != null) {
			SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			
			try {
				date = formate.parse(expiration);
				long timestamp = date.getTime();
				timestamp = timestamp / 1000;
				date_expiration = toIntExact(timestamp);
				System.out.println("timetstamp = "+date_expiration);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
		if(password_url == null) {
			password_url = "";
		}
		if(maxfield == null) {
			maxfield = "0";
		}
		if(maxfield == "") {
			maxfield = "0";
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = SqlConnection.dbConnector(); //Connexion à la base de données

			String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			SecureRandom rnd = new SecureRandom();
			int len = 5;
			StringBuilder sb = new StringBuilder( len );
			for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
			String url_short = sb.toString();
			url_raccourci = url_short;
			
			

			String query1 = "insert into Lien (url_full, url_short, id_user, password_url, max_nb_clique, expiration) values (?, ?, ?, ?, ?, ?)";
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
			 pst1.setString(5, maxfield);
			 pst1.setInt(6, date_expiration);
			
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
