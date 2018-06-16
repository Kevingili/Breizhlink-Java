package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.bdd.TestJDBC;
import com.sdzee.beans.Lien;
import com.sdzee.beans.Utilisateur;

public class Restriction extends HttpServlet {
    public static final String ACCES_PUBLIC     = "/connexion";
    public static final String ACCES_RESTREINT  = "/WEB-INF/accesRestreint.jsp";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_MESSAGES = "liens";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
       
        TestJDBC test = new TestJDBC();
        List<Lien> liens = test.executerTests( request );
        request.setAttribute( ATT_MESSAGES, liens );
        /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            response.sendRedirect( request.getContextPath() + ACCES_PUBLIC );
        } else {
        	Utilisateur user_current = (Utilisateur) session.getAttribute( ATT_SESSION_USER );
            System.out.println("current id = "+user_current.getId() );
            /* Affichage de la page restreinte */
        	
            this.getServletContext().getRequestDispatcher( ACCES_RESTREINT ).forward( request, response );
        }
    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
    }
}