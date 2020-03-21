package com.excilys.librarymanager.dao.impl;

import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.modele.Abonnement;
import com.excilys.librarymanager.dao.MembreDao;
import com.excilys.librarymanager.persistence.ConnectionManager;

import java.sql.ResultSet;
import java.sql.Statement;

public class MembreDaoImpl implements MembreDao
{
    private static MembreDaoImpl instance;
	private MembreDaoImpl() { }	
	public static MembreDaoImpl getInstance() {
		if(instance == null) {
			instance = new MembreDaoImpl();
		}
		return instance;
	}

    private static final String SELECT_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;");
    private static final String SELECT_ONE_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";
    private static final String CREATE_QUERY =  "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM membre WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";


    @Override
    public List<Membre> getList() throws DaoException
    {
        List<Membre> membres = new ArrayList<>();
		
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
			 ResultSet res = preparedStatement.executeQuery();
            )
            {
			while(res.next()) {
				Membre membre = new Membre(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
				membres.add(membre);
			}
			System.out.println("GET: " + membres);
		} catch (SQLException exc) {
			throw new DaoException("Probleme lors de la recuperation de la liste des membres", exc);
		}
        return membres;
    }

    @Override
    public Membre getById(int id) throws DaoException
    {
		Membre membre = new Membre();
		ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection =  ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
			preparedStatement.setInt(1, id);
			res = preparedStatement.executeQuery();
			if(res.next()) {
				membre.setId(res.getInt("id"));
				membre.setNom(res.getString("nom"));
                membre.setPrenom(res.getString("prenom"));
                membre.setAdresse(res.getString("adresse"))
                membre.setEmail(res.getString("email");
				membre.setTelephone(res.getString("telephone"));
				membre.setAbonnment(Abonnement.valueOf(res.getString("abonnement")));							
			}
			
			System.out.println("GET: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation du membre: id=" + id, e);
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
		return membre;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement) throws DaoException
    {
        ResultSet res = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int id = -1;
		try {
			connection =  ConnectionManager.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            
			preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telephone);
			preparedStatement.setString(6, abonnement.name());
			preparedStatement.executeUpdate();
			res = preparedStatement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);				
			}
            Membre membre = new Membre(nom, prenom, adresse, email, telephone, abonnement);
			System.out.println("CREATE: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la creation du film: " + film, e);
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
		return id;
    }

    @Override
    public void update(Membre membre) throws DaoException
    {
        Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection =  ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getAdresse());
            preparedStatement.setString(4, membre.getEmail());
            preparedStatement.setString(5, membre.getTelephone());
            preparedStatement.setString(6, membre.getAbonnement().name());
            preparedStatement.setString(7, membre.getId());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE: " + membre);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la mise a jour du membre: " + membre, e);
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
		return membre.getId();
    }

    @Override
    public void delete(int id) throws DaoException
    {
        Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setInt(1, id));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			System.out.println("DELETE: membre numéro " + id);
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la suppression du membre: " + membre, e);
		}  finally {
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
			throw new DaoException("Probleme lors du décompte du nombre de membres", e);
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
		return membre;
    }

}