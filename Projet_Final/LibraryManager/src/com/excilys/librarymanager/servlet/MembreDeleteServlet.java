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
import com.excilys.librarymanager.Modele.Livre;
import com.excilys.librarymanager.Modele.Membre;
import com.excilys.librarymanager.Modele.Emprunt;
import com.excilys.librarymanager.service.impl.LivreService;
import com.excilys.librarymanager.service.impl.MembreService;
import com.excilys.librarymanager.service.impl.EmpruntService;

public class MembreDeleteServlet extends HttpServlet {	
	
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
		int idDuMembre = (int) request.getAttribute("idDuMembre");
		MembreService membreService = membreService.getInstance();
		Membre membre = membreService.getById(idDuMembre);
		request.setAttribute("membre", membre);
        
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_delete.jsp");
		dispatcher.forward(request, response);

    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		doGet(request, response);
		int idDuMembre = (int) request.getAttribute("id");
		MembreService membreService = membreService.getInstance();
		int i=membreService.delete(idDuMembre);
		//if i =!-1 ... ?
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/membre_list.jsp");

	}
    
}