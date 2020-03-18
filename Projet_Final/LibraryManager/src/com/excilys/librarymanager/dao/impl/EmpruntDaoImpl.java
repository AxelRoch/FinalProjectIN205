package com.excilys.librarymanager.dao.impl;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.EmpruntDao;
import com.excilys.librarymanager.persistence.ConnectionManager;

import java.sql.Statement;

public class EmpruntDaoImpl implements EmpruntDaoImpl
{




    private static final String SELECT_ALL_QUERY = "SELECT * FROM Emprunt;";


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
				Emprunt e = new Emprunt(res.getInt("id"), res.getString("titre"), res.getString("realisateur"));
				emprunts.add(e);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
		return emprunts;
	}