package com.excilys.librarymanager.Modele;


public class Membre {
	private Integer id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;
	
	public Membre() {
		super();
    }
    
	public Membre(String nom, String prenom, Abonnement abonnement) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.abonnement=abonnement;
    }

    public Membre(Integer id, String nom, String prenom, Abonnement abonnement) {
        this(nom, prenom, abonnement);
        this.id = id;
    }
    public Membre(Integer id, String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) {
        this(id,nom, prenom, abonnement);
        this.adresse = adresse;
	this.email = email;
	this.telephone = telephone;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
		this.adresse = adresse;
    }
    
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
		this.telephone = telephone;
    }
    
    public String getAbonnement() {
        return abonnement
    }

    public void setAbonnement(String email) {
		this.abonnement = abonnement;
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
