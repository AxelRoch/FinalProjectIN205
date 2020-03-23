package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.*;
import com.excilys.librarymanager.dao.impl.*;
import com.excilys.librarymanager.exception.*;
import com.excilys.librarymanager.modele.*;
import com.excilys.librarymanager.service.*;



public class MembreServiceImpl implements MembreService {

    private static MembreServiceImpl instance;
	private MembreServiceImpl() { }	
	public static MembreService getInstance() {
		if(instance == null) {
			instance = new MembreServiceImpl();
		}
		return instance;
	}
    
    @Override
    public List<Membre> getList() {
        MembreDao membreDao = membreDao.getInstance();
        List<Membre> membres = new ArrayList<>();		
        try {
            membres = membreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return membres;
    }
    
    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException{    //pas sure 
        MembreDao membreDao = membreDao.getInstance();
        List<Membre> membres = new ArrayList<>();	
        EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
        try {
            membres = membreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        for (int i=0; i<membres.size();i++){
            if (empruntService.isEmpruntPossible(membres.get(i))){
                membres.add(membres.get(i));
            }
        }
    }
    
    @Override
    public Membre getById(int id) throws ServiceException {
        MembreDao membreDao = membreDao.getInstance();
        Membre membre = new Membre();
        try {
            membre = membreDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return membre;
    }
    
	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
		if(nom == null)
        {
            throw new ServiceException("Erreur : nom du membre à preciser");
		}
		if(prenom == null)
        {
            throw new ServiceException("Erreur : prenom du membre à preciser");
        }
		MembreDao membreDao = membreDao.getInstance();
		int i = -1;
		try {
			i = membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return i;
	}
    
	@Override
	public void update(Membre membre) throws ServiceException {
		if(membre.getNom()== null)
        {
            throw new ServiceException("Erreur : nom du membre à preciser");
		}
		if(membre.getPrenom() == null)
        {
            throw new ServiceException("Erreur : prenom du membre à preciser");
        }
		MembreDao membreDao = membreDao.getInstance();
		membre.setNom(membre.getNom().toUpperCase());
		try {
			membreDao.update(membre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}


	@Override
	public void delete(int id) throws ServiceException {
		MembreDao membreDao = membreDao.getInstance();
		int i = -1;
		try {
			membreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

    @Override
    public int count() throws ServiceException{
        MembreDao membreDao = membreDao.getInstance();
		int i = -1;
		try {
			i = membreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
    }
}