package com.sdzee.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Visite extends HttpServlet {
	public static final String VUE = "/WEB-INF/visite.jsp";
	public static final String CHAMP_ETUDIANT = "nomEtudiant";
	public static final String CHAMP_DATED = "dateD";
	public static final String CHAMP_NOTE = "note";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération des champs du formulaire. */
		String nomEtudiant = request.getParameter(CHAMP_ETUDIANT);
		String dateD = request.getParameter(CHAMP_DATED);
		int note = Integer.parseInt(request.getParameter(CHAMP_NOTE));
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "toto", "toto");
			System.out.println("ok");

			//String query = "SELECT * FROM Visite";
			//PreparedStatement pst = conn.prepareStatement(query);
			//ResultSet rs = pst.executeQuery();

			//while (rs.next()) {
			//	System.out.println(rs.getString("nomEtudiant"));
			//}
			//rs.close();
			//pst.close();
			System.out.println(nomEtudiant);
			System.out.println(dateD);
			System.out.println(note);
			String query1 = "insert into Visite (nomEtudiant, dateD, note) values (?, ?, ?)";
			PreparedStatement pst1 = conn.prepareStatement(query1);
			pst1.setString(1, nomEtudiant);
			pst1.setString(2, dateD);
			pst1.setInt(3, note);
			pst1.execute();
			pst1.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}
	}

}