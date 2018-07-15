package com.sdzee.beans;

public class Lien {
	
	private String urlfull;
	private String urlshort;
	private String datecreation;
	private int nbclique;
	private int expiration;
	
	public void setUrlfull(String urlfull) {
		this.urlfull = urlfull;
	}
    public String getUrlfull() {
    		return urlfull;
    }
    
    public void setNbclique(int nbclique) {
		this.nbclique = nbclique;
	}
    public int getNbclique() {
    		return nbclique;
    }
    
    public void setExpiration(int expiration) {
		this.expiration = expiration;
	}
    public int getExpiration() {
    		return expiration;
    }
    
    
    public void setUrlshort(String urlshort) {
		this.urlshort = urlshort;
	}
    public String getUrlshort() {
    		return urlshort;
    }
    
    public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}
    public String getDatecreation() {
    		return datecreation;
    }
    
}
