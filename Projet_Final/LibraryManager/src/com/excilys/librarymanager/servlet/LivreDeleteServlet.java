package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.service.impl.*;

public class LivreDeleteServlet extends HttpServlet {	
	
	/*
	 *  La mÃ©thode doGet est le point d'entrÃ©e lors d'une requete GET
	 *  Dans notre cas on traite tous les cas de figure en passant par doGet
	 *  Cependant pour vraiment respecter les conventions Http, il est de bonne pratique
	 *  de gÃ©rer les suppressions dans la mÃ©thode doDelete, les modification dans la mÃ©thode
	 *  doPut etc ...
	 *  A noter qu'il existe des "logger" pour remplacer nos "Sysout" (System.out.println) qui permettent 
	 *  de formatter l'affichage lors du dÃ©bug dans la console (Affichant la date, l'heure, la classe dans 
	 *  laquelle le logger effectue l'affichage). Plusieurs niveaux d'affichage peuvent Ãªtre utilisÃ©s 
	 *  (info, debug, error, warn, etc...).
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idDuLivre = (int) request.getAttribute("idDuLivre");
		try{
			LivreServiceImpl livreService = LivreServiceImpl.getInstance();
			Livre livre = livreService.getById(idDuLivre);
			request.setAttribute("idDuLivre", idDuLivre);
			request.setAttribute("titre", livre.getTitre());
			request.setAttribute("auteur", livre.getAuteur());
			request.setAttribute("isbn", livre.getIsbn());
		}
		catch (ServiceException e){System.out.println(e.getMessage());}

        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_delete.jsp");
		dispatcher.forward(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		doGet(request, response);
		int idDuLivre = (int) request.getAttribute("idDuLivre");
		try {
			LivreServiceImpl livreService = LivreServiceImpl.getInstance();
			livreService.delete(idDuLivre);
		}
		catch (ServiceException e){System.out.println(e.getMessage());}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_list.jsp");

	}
    
}
