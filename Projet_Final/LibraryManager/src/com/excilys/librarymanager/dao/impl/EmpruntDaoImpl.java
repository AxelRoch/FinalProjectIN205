package com.excilys.librarymanager.dao.impl;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.dao.EmpruntDao;
import com.excilys.librarymanager.persistence.ConnectionManager;

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



    @Override
    public List<Emprunt> getList() throws DaoException
    {
        List<Emprunt> emprunts = new ArrayList<>();
		
    
    @Override
    public List<Emprunt> getListCurrent() throws DaoException
    {
        List<Emprunt> emprunts = new ArrayList<>();
		
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENT_QUERY);
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
