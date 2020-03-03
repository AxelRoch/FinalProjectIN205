package com.excilys.librarymanager.Modele;

import java.sql.Date;

public class Emprunt {
	private Integer id;
    private Integer idMembre;
    private Integer idLivre;
    private Date dateEmprunt;
    private	Date dateRetour;
	
	public Emprunt() {
		super();
	}
	public Emprunt(???) {
		this();
		
	}
	public Emprunt(???) {
		this(??);
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
    }
    public Integer getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(Integer idMembre) {
		this.idMembre = idMembre;
    }
    public Integer getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(Integer idLivre) {
		this.idLivre = idLivre;
	}
	public Date getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public String getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" 
				+ "Id: " + id + ", "
                + "IdMembre:" + idMembre + ", "
                + "IdLivre:" + idLivre + ", "
                + "DateEmprunt: " + dateEmprunt + ", "
                + "DateRetour: " + dateRetour
				+ "}";
	}
}