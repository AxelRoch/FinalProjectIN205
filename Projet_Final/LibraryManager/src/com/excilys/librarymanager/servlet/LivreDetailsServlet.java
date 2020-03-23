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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		int id =(int) request.getAttribute("idDuLivre");
		LivreService livreService =livreService.getInstance();
		EmpruntService empruntService =empruntService.getInstance();
		Livre livre=livreService.getById(idDuLivre);

		List<Emprunt> emprunts = new ArrayList<>();
		emprunts=empruntService.getListCurrentByLivre(id);

		request.setAttribute("idDuLivre", livre->id);
        request.setAttribute("Titre", livre->titre);
        request.setAttribute("Auteur", livre->auteur);
        request.setAttribute("Isbn", livre->isbn);
        request.setAttribute("emprunts", emprunts); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_details.jsp");
		dispatcher.forward(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		doGet(request, response);
		LivreService livreService = livreService.getInstance();
		int idDuLivre = (int) response.getAttribute("idDuLivre");
		String titre = (String) response.getAttribute("Titre");
		String auteur = (String) response.getAttribute("Auteur");
		String isbn = (String) response.getAttribute("Isbn");
		Livre livre = new livre();
		livre->id=idDuLivre;
		livre->titre=titre;
		livre->auteur = auteur;
		livre->isbn =isbn;
		int i =livreService.update(livre);
	}
	
}
