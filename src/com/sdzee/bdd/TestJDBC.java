package com.sdzee.bdd;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Lien;
import com.sdzee.beans.Utilisateur;

import java.sql.*;

public class TestJDBC {

    List<Lien> liens = new ArrayList<Lien>();

    public List<Lien> executerTests( HttpServletRequest request ) {

        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
        	System.out.println(e);
        }

        String url = "jdbc:mysql://localhost:3306/java";
        String utilisateur = "toto";
        String motDePasse = "toto";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            statement = connexion.createStatement();
            HttpSession session = request.getSession();
			System.out.println("session = "+session);
			int id_current = 0;
			 if ( session.getAttribute( "sessionUtilisateur" ) != null ) {
				 
				Utilisateur user_current = (Utilisateur) session.getAttribute(  "sessionUtilisateur" );
		        id_current = user_current.getId();
		       
		     } 
            resultat = statement.executeQuery( "SELECT * FROM Lien WHERE id_user ="+id_current );
           
            while ( resultat.next() ) {
                int idLien = resultat.getInt( "id" );
                String url_full = resultat.getString( "url_full" );
                String url_short = resultat.getString( "url_short" );
                String date_creation = resultat.getString( "date_creation" );
                int nbclique = resultat.getInt( "nb_clique" );

                Lien lien_temp = new Lien();
                lien_temp.setUrlfull(url_full);
                lien_temp.setUrlshort(url_short);
                lien_temp.setDatecreation(date_creation);
                lien_temp.setNbclique(nbclique);
                liens.add(lien_temp);
            }
        } catch ( SQLException e ) {

        } finally {
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }

        return liens;
    }
}