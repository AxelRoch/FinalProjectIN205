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

public class LivreDetailsServlet extends HttpServlet {	
	
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
		int id =(int) request.getAttribute("idDuLivre");
		List<Emprunt> emprunts = new ArrayList<>();

		try{
			LivreServiceImpl livreService = LivreServiceImpl.getInstance();
			EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
			Livre livre=livreService.getById(id);
			emprunts=empruntService.getListCurrentByLivre(id);

			request.setAttribute("idDuLivre", livre.getId());
			request.setAttribute("Titre", livre.getTitre());
			request.setAttribute("Auteur", livre.getAuteur());
			request.setAttribute("Isbn", livre.getIsbn());
			request.setAttribute("emprunts", emprunts); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_details.jsp");
			dispatcher.forward(request, response);
		}
		catch (ServiceException e){System.out.println(e.getMessage());}
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		try{
			LivreServiceImpl livreService = LivreServiceImpl.getInstance();
			int idDuLivre = Integer.parseInt(request.getParameter(("idDuLivre")));
			String titre =  request.getParameter("Titre");
			String auteur = request.getParameter("Auteur");
			String isbn =  request.getParameter("Isbn");
			Livre livre = new Livre();
			livre.setId(idDuLivre);
			livre.setTitre(titre);
			livre.setAuteur(auteur);
			livre.setIsbn(isbn);
			livreService.update(livre);
		}
		catch (ServiceException e){System.out.println(e.getMessage());}
    }
}
