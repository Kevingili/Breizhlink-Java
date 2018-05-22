package com.sdzee.forms;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.beans.Utilisateur;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setEmail( email );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMotDePasse( motDePasse );

        
        //verif base
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(request.getServletContext().getInitParameter("db-user"));
			java.sql.Connection conn = DriverManager.getConnection(request.getServletContext().getInitParameter("db-url"), request.getServletContext().getInitParameter("db-user"), request.getServletContext().getInitParameter("db-password"));
			//System.out.println("ok");

			String query = "SELECT * FROM Utilisateur WHERE email = ? AND password = ?";
			PreparedStatement pst = conn.prepareStatement(query);
		      pst.setString(1, email);
		      pst.setString(2, motDePasse);
			ResultSet rs = pst.executeQuery();
			
			if(!rs.next()) {
				 resultat = "Échec de la connexion.";
				// throw new Exception( "Utilisateur ou mot de passe incorrecte" );
				 setErreur( CHAMP_PASS, "Utilisateur ou mot de passe incorrecte");
			} else {
				 resultat = "Succès de la connexion.";
			}
			
			//while (rs.next()) {
			//	System.out.println(rs.getString("nomEtudiant"));
			//}
			//System.out.println(rs);
			rs.close();
			pst.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}

        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}