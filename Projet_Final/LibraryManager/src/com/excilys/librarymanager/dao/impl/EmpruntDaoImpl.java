package com.excilys.librarymanager.dao.impl;

import java.time.LocalDate;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.dao.EmpruntDao;
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


    private static final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
    private static final String SELECT_ALL_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
	private static final String SELECT_ALL_CURRENT_BY_MEMBER_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
	private static final String SELECT_ALL_CURRENT_BY_BOOK_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
	private static final String SELECT_ONE_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
	private static final String CREATE_QUERY = " INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
	private static final String UPDATE_QUERY = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";
	private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM emprunt;";

    @Override
    public List<Emprunt> getList() throws DaoException
    {
		List<Emprunt> emprunts = new ArrayList<>();
		MembreDaoImpl dataMembres = MembreDaoImpl.getInstance();
		LivreDaoImpl dataLivres = LivreDaoImpl.getInstance();
		dataMembres.getById(id);
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			 ResultSet res = preparedStatement.executeQuery();
			)
			
            {
			while(res.next()) {
				Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")) );

				Livre livre = new Livre(res.getInt("idLivre"),  res.getString("titre"), res.getString("auteur"), res.getString("isbn"));

				Emprunt emprunt = new Emprunt(res.getInt("id"), membre, livre, res.getDate("dateEmprunt", res.getDate("dateRetour")));
				emprunts.add(emprunt);
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
		List<Emprunt> emprunts = new ArrayList<>();
		MembreDaoImpl dataMembres = MembreDaoImpl.getInstance();
		LivreDaoImpl dataLivres = LivreDaoImpl.getInstance();
		dataMembres.getById(id);
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENT_QUERY);
			 ResultSet res = preparedStatement.executeQuery();
			)
			
            {
			while(res.next()) {
				Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")) );

				Livre livre = new Livre(res.getInt("idLivre"),  res.getString("titre"), res.getString("auteur"), res.getString("isbn"));

				Emprunt emprunt = new Emprunt(res.getInt("id"), membre, livre, res.getDate("dateEmprunt", res.getDate("dateRetour")));
				emprunts.add(emprunt);
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
            preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENT_BY_MEMBER_QUERY);
            preparedStatement.setInt(1, idMembre);
			ResultSet res = preparedStatement.executeQuery();
            
			while(res.next()) {
				Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")) );

				Livre livre = new Livre(res.getInt("idLivre"),  res.getString("titre"), res.getString("auteur"), res.getString("isbn"));

				Emprunt emprunt = new Emprunt(res.getInt("id"), membre, livre, res.getDate("dateEmprunt", res.getDate("dateRetour")));
				emprunts.add(emprunt);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
		return emprunts;
	}



	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException
	{
        List<Emprunt> emprunts = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
		
        try 
        {
            connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_CURRENT_BY_BOOK_QUERY);
            preparedStatement.setInt(1, idLivre);
			ResultSet res = preparedStatement.executeQuery();
            
			while(res.next()) {
				Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")) );

				Livre livre = new Livre(res.getInt("idLivre"),  res.getString("titre"), res.getString("auteur"), res.getString("isbn"));

				Emprunt emprunt = new Emprunt(res.getInt("id"), membre, livre, res.getDate("dateEmprunt", res.getDate("dateRetour")));
				emprunts.add(emprunt);
			}
			System.out.println("GET: " + emprunts);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des emprunts", exc);
		}
		return emprunts;
	}



	@Override
	public Emprunt getById(int id) throws DaoException
	{
		com.excilys.librarymanager.Modele.Emprunt emprunt = new Emprunt();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection =  ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
			preparedStatement.setInt(1, id);
			res = preparedStatement.executeQuery();
			if(res.next()) {
				Membre membre = new Membre(res.getInt("idMembre"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")) );

				Livre livre = new Livre(res.getInt("idLivre"),  res.getString("titre"), res.getString("auteur"), res.getString("isbn"));


				emprunt.setId(res.getInt(id));
				emprunt.setmembre(membre);
				emprunt.setlivre(livre);
				emprunt.setDateEmprunt(res.getDate("dateEmprunt"));
				emprunt.setDateRetour(res.getDate("dateRetour"));				
			}
			
			System.out.println("GET: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de l'emprunt numéro " + id, e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emprunt;
    }


	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException
	{
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection =  ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            
			preparedStatement.setString(1, idMembre);
            preparedStatement.setString(2, idLivre);
            preparedStatement.setString(3, dateEmprunt);
            preparedStatement.setString(4, null);
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);				
			}
			System.out.println("CREATE Emprunt numero " + id);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation de l'emprunt", e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }




	
	@Override
	public void update(Emprunt emprunt) throws DaoException
	{
        Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection =  ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, emprunt.getmembre().getId());
            preparedStatement.setString(2, emprunt.getlivre().getId());
            preparedStatement.setString(3, emprunt.getDateEmprunt());
            preparedStatement.setString(4, emprunt.getDateRetour());
            preparedStatement.setString(5, emprunt.getId());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + emprunt);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la mise a jour l'emprunt: " + emprunt, e);
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }




	@Override
	public int count() throws DaoException
	{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
			connection = ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(COUNT_QUERY);
            int count = -1;
            
			res = preparedStatement.executeQuery();
			if(res.next()) {
				count = res.getInt("count");
			}

		} catch (SQLException e) {
			throw new DaoException("Probleme lors du décompte du nombre d'emprunts'", e);
		} finally {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
    }








}
