package com.excilys.librarymanager.dao.impl;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.EmpruntDao;
import com.excilys.librarymanager.modele.Abonnement;
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

    private static final String SELECT_ALL_QUERY = "SELECT id, membre, livre, dateEmprunt, dateRetour;");
    private static final String SELECT_ONE_QUERY = "SELECT id, membre, livre, dateEmprunt, dateRetour FROM membre WHERE id = ?;";
    private static final String CREATE_QUERY =  "INSERT INTO emprunt(membre, livre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE emprunt SET membre = ?, livre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM emprunt WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";


    @Override
    public List<Emprunt> getList() throws DaoException
    {
        List<Emprunt> emprunts = new ArrayList<>();
		
		try (Connection connection = EstablishConnection.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			 ResultSet res = preparedStatement.executeQuery();
            )
            {
			while(res.next()) {
				Emprunt e = new Emprunt(res.getInt("id"), res.getString("titre"), res.getString("realisateur"));   ///à changer
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
		return emprunts;
	}
	
	public List<Emprunt> getList() throws DaoException{
	}
	
	public List<Emprunt> getListCurrent() throws DaoException{
	}
	
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
	}
	
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
	}
	
	public Emprunt getById(int id) throws DaoException{
	}
	
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
	}
	
	public void update(Emprunt emprunt) throws DaoException{
	}
	
	public int count() throws DaoException{
	}
	
}
