package com.excilys.librarymanager.Modele;


public class Livre {
	private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;
	
	public Livre() {
		super();
	}
	public Livre(String nom, String prenom, Abonnement abonnement) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.abonnement=abonnement;
    }

    public Livre(Integer id, String nom, String prenom, Abonnement abonnement) {
        this(nom, prenom);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
                + "nom:" + nom + ", "
                + "prenom:" + prenom + ", "
                + "adresse:" + prenom + ", "
                + "email: " + email + ", "
                + "telephone:" + prenom + ", "
                + "abonnement:" + abonnement
				+ "}";
	}
}