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

public class EmpruntAddServlet extends HttpServlet {	
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
        LivreService livreService = livreService.getInstance();
        MembreService membreService = membreService.getInstance();

        List<Livre> livresDispo = new ArrayList<>();
        List<Membre> membresOk = new ArrayList<>();
        try {
			livresDispo = livreService.getListDispo();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
        }

        try {
			membresOk = MembreService.getListMembreEmpruntPossible();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

        request.setAttribute("livres_dispo", livresDispo);
        request.setAttribute("membres_ok", membresOk);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/emprunt_add.jsp");
		dispatcher.forward(request, response);
    	}
    
    	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		doGet(request, response);
		EmpruntService empruntService=empruntService.getInstance();
		int idDuMembre = (int) response.getAttribute("idDuMembre");
		int idDuLivre = (int) response.getAttribute("idDuLivre");
		LocalDate dateEmprunt= new LocalDate();
		empruntService.create(idDuMembre,idDuLivre,dateEmprunt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/emprunt_list.jsp");
	}
	
}
