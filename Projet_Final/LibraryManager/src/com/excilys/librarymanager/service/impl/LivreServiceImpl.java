package com.excilys.librarymanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.dao.*;
import com.excilys.librarymanager.dao.impl.*;
import com.excilys.librarymanager.exception.*;
import com.excilys.librarymanager.modele.*;
import com.excilys.librarymanager.service.*;


public class LivreServiceImpl implements LivreService {

    private static LivreServiceImpl instance;
	private LivreServiceImpl() { }	
	public static LivreServiceImpl getInstance() {
		if(instance == null) {
			instance = new LivreServiceImpl();
		}
		return instance;
	}


    @Override
    public List<Livre> getList() {
        LivreDao livreDao = livreDao.getInstance();
        List<Livre> livres = new ArrayList<>();		
        try {
            livres = livreDao.getList();
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return livres;
    }
    
    @Override
    public List<Livre> getListDispo() throws ServiceException{

		EmpruntServiceImpl emprunt = EmpruntServiceImpl.getInstance();
		LivreDaoImpl livreDao = LivreDaoImpl.getInstance();
		List<Livre> livres = new ArrayList<>();
		List<Livre> livresDispo = new ArrayList<>();

		try{
			livres = livreDao.getList();

			for (Livre livre : livres)
			{
				if (emprunt.isLivreDispo(livre.getId())){livresDispo.add(livre);}
			}
			} catch (DaoException e1) {
			System.out.println(e1.getMessage());
		}
		return livresDispo;		
    }
    
    @Override
    public Livre getById(int id) throws ServiceException {
        LivreDao livreDao = livreDao.getInstance();
        Livre livre = new Livre();
        try {
            livre = livreDao.getById(id);
        } catch (DaoException e1) {
            System.out.println(e1.getMessage());			
        }
        return livre;
    }
    
	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		if(titre == null)
        {
            throw new ServiceException("Erreur : Titre du Livre à preciser");
        }
		LivreDao livreDao = livreDao.getInstance();
		int i = -1;
		try {
			i = livreDao.create(titre, auteur, isbn);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return i;
	}
    
	@Override
	public void update(Livre livre) throws ServiceException {
		if(livre.getTitre() == null)
        {
            throw new ServiceException("Erreur : Titre du Livre à preciser");
        }
		try {
			LivreDao livreDao = livreDao.getInstance();
			livreDao.update(livre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}


	@Override
	public void delete(int id) throws ServiceException {
		
		try {
			LivreDao livreDao = livreDao.getInstance();
			livreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

    @Override
    public int count() throws ServiceException{
		int i = -1;
		try {
			LivreDao livreDao = livreDao.getInstance();
			i = livreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return i;
    }
}
