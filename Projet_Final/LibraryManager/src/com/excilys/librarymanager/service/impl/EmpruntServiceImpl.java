package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.*;
import com.excilys.librarymanager.dao.impl.*;
import com.excilys.librarymanager.exception.*;
import com.excilys.librarymanager.modele.*;

import java.util.ArrayList;
import java.util.List;


public class EmpruntServiceImpl implements EmpruntService {

    private static EmpruntServiceImpl instance;
	private EmpruntServiceImpl() { }	
	public static EmpruntService getInstance() {
		if(instance == null) {
			instance = new EmpruntServiceImpl();
		}
		return instance;
	}
    
    @Override
    public List<Emprunt> getList() {
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();		
        try {
            emprunts = empruntDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return emprunts;
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException{
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrent();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return emprunts;
    }
    
    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return emprunts;
    }


    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            emprunts = empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return emprunts;
    }
    
    @Override
    public Emprunt getById(int id) throws ServiceException {
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        Emprunt emprunt = new Emprunt();
        try {
            emprunt = empruntDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return emprunt;
    }
    
	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
		try {
			empruntDao.create(idMembre, idLivre, dateEmprunt);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
    }
    
    @Override
    public void returnBook(int id) throws ServiceException{
        EmpruntDao empruntDao = empruntDaoImpl.getInstance();
        Emprunt emprunt = new Emprunt();
        try {
            emprunt = empruntDao.getById(id);
            
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
        } 
        
        emprunt.setDateRetour(LocalDate.now());
        dao.update(emprunt);

        try {
			empruntDao.update(emprunt);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
    }
    
    @Override
    public int count() throws ServiceException{
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
		int i = -1;
		try {
			i = empruntDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
    }
    
    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException{
        //Regarder si dans listCurrent
        EmpruntDaoImpl empruntDao = EmpruntDaoImpl.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        try {
			emprunts = empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
        }
        return emprunts.isEmpty();
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException{
        EmpruntDao empruntDao = empruntDao.getInstance();
        List<Emprunt> emprunts = new ArrayList<>();
        //MembreDao membreDao =membreDao.getInstance();

        int nbMax;
        try {
			nbEmprunts = empruntDao.getListCurrentByMembre(membre.getId()).size();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
        }
        switch (membre.getAbonnement()){
            case "BASIC" :
                nbMax = 2;
                break;
            case "PREMIUM" :
                nbMax = 5;
                break;
            case "VIP" :
                nbMax = 20;
                break;
        }
        return nbEmprunts < nbMax;
    }
}