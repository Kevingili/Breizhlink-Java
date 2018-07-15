package com.sdzee.forms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.bdd.SqlConnection;
import com.sdzee.beans.Lien;
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
			Connection conn = null;
			conn = SqlConnection.dbConnector(); //Connexion à la base de données
			//java.sql.Connection conn = DriverManager.getConnection(request.getServletContext().getInitParameter("db-url"), request.getServletContext().getInitParameter("db-user"), request.getServletContext().getInitParameter("db-password"));

			String query = "SELECT * FROM Utilisateur WHERE email = ? AND password = ?";
			PreparedStatement pst = conn.prepareStatement(query);
		      pst.setString(1, email);
		      pst.setString(2, motDePasse);
			ResultSet rs = pst.executeQuery();
			
			if(!rs.next()) {
				 resultat = "Échec de la connexion.";
				 setErreur( CHAMP_PASS, "Utilisateur ou mot de passe incorrecte");
			} else {
				 resultat = "Succès de la connexion.";
		          int id = rs.getInt( "id" );
		          utilisateur.setId(id);

				
			}

			rs.close();
			pst.close();

		} catch (Exception e) {
			System.out.println("pas ok " + e);
		}

        return utilisateur;
    }


    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }


    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }


    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }


    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}