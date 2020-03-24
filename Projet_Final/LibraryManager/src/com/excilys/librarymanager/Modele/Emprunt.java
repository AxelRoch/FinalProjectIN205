package com.excilys.librarymanager.modele;

import java.sql.Date;

public class Emprunt {
	private Integer id;
    private Membre membre;
    private Livre livre;
    private Date dateEmprunt;
    private	Date dateRetour;
	
	public Emprunt() {
		super();
	}
	
	public Emprunt(Membre membre, Livre livre, Date dateEmprunt, Date dateRetour) {
		this();
		this.membre = membre;
		this.livre = livre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	public Emprunt(Integer id, Membre membre, Livre livre, Date dateEmprunt, Date dateRetour) {
		this(membre, livre, dateEmprunt, dateRetour);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
    }
    public Membre getmembre() {
		return membre;
	}
	public void setmembre(Membre membre) {
		this.membre = membre;
    }
    public Livre getlivre() {
		return livre;
	}
	public void setlivre(Livre livre) {
		this.livre = livre;
	}
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
                + "membre:" + membre + ", "
                + "livre:" + livre + ", "
                + "DateEmprunt: " + dateEmprunt + ", "
                + "DateRetour: " + dateRetour
				+ "}";
	}
}