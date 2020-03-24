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

public class DashboardServlet extends HttpServlet {	
	
	/*
	 *  La méthode doGet est le point d'entrée lors d'une requete GET
	 *  Dans notre cas on traite tous les cas de figure en passant par doGet
	 *  Cependant pour vraiment respecter les conventions Http, il est de bonne pratique
	 *  de gérer les suppressions dans la méthode doDelete, les modification dans la méthode
	 *  doPut etc ...
	 *  A noter qu'il existe des "logger" pour remplacer nos "Sysout" (System.out.println) qui permettent 
	 *  de formatter l'affichage lors du débug dans la console (Affichant la date, l'heure, la classe dans 
	 *  laquelle le logger effectue l'affichage). Plusieurs niveaux d'affichage peuvent être utilisés 
	 *  (info, debug, error, warn, etc...).
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LivreServiceImpl livreService = LivreServiceImpl.getInstance();
        MembreServiceImpl membreService = MembreServiceImpl.getInstance();
        EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
        
        List<Emprunt> emprunts = new ArrayList<>();
		int nbLivres = 0;
		int nbMembres = 0;
		int nbEmprunts = 0;
        
		try {
			emprunts = empruntService.getListCurrent();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			nbEmprunts = empruntService.count();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			nbLivres = livreService.count();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        try {
			nbMembres = membreService.count();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        request.setAttribute("nombre de livres", nbLivres);
        request.setAttribute("nombre de membres", nbMembres);
        request.setAttribute("nombre d'emprunts", nbEmprunts);
        request.setAttribute("emprunts en cours", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/dashboard.jsp");
		dispatcher.forward(request, response);

	}
	
}
