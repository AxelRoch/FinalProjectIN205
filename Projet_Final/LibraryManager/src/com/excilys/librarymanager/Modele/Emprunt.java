package com.excilys.librarymanager.modele;

import java.sql.Date;
import java.time.LocalDate;

public class Emprunt {
	private Integer id;
    private Membre membre;
    private Livre livre;
    private LocalDate dateEmprunt;
    private	LocalDate dateRetour;
	
	public Emprunt() {
		super();
	}
	
	public Emprunt(Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
		this();
		this.membre = membre;
		this.livre = livre;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}
	public Emprunt(Integer id, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
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
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
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