package com.excilys.librarymanager.dao.impl;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.dao.EmpruntDao;
import com.excilys.librarymanager.persistence.ConnectionManager;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpruntDaoImpl implements EmpruntDao
{

    private static EmpruntDaoImpl instance;
	private EmpruntDaoImpl() { }	
	public static EmpruntDaoImpl getInstance() {
		if(instance == null) {
			instance = new EmpruntDaoImpl();
		}
		return instance;
	}


    private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
    private static final String SELECT_ALL_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
    private static final String SELECT_ALL_CURRENT_BY_MEMBER_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";

    @Override
    public List<Emprunt> getList() throws DaoException
    {
        List<Emprunt> emprunts = new ArrayList<>();
		
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			 ResultSet res = preparedStatement.executeQuery();
            )
            {
			while(res.next()) {
				Emprunt e = new Emprunt(res.getInt("id"), res.getString("titre"), res.getString("realisateur"));
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
        return emprunts;
    }
    

    @Override
    public List<Emprunt> getListCurrent() throws DaoException
    {
        int idmembre;
        List<Emprunt> emprunts = new ArrayList<>();
		
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENT_QUERY);
			 ResultSet res = preparedStatement.executeQuery();
            )
            {
			while(res.next()) {
                idmembre = res.getInt("idmembre");
				Emprunt e = new Emprunt(res.getObject("membre"),res.getInt("id"), res.getString("titre"), res.getString("realisateur"));
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
        return emprunts;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException
    {
        List<Emprunt> emprunts = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
		
        try 
        {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENT_QUERY);
            preparedStatement.setInt(1, idMembre); //Pour le "?"
			ResultSet res = preparedStatement.executeQuery();
            
			while(res.next()) {
				Emprunt e = new Emprunt(res.getInt("id"), res.getString("titre"), res.getString("realisateur"));
				emprunts.add(e);
		}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
        return emprunts;
    }


}