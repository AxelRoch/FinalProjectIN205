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

public class LivreAddServlet extends HttpServlet {	
	
/// A faire : doGet -> ??, renvoyer vers dÃ©tails (attention rÃ©cupÃ©ration de l'identifiant)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_add.jsp");
		dispatcher.forward(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		doGet(request, response);
		LivreService livreService = livreService.getInstance();
		String titre = (String) response.getAttribute("titre");
		String auteur = (String) response.getAttribute("auteur");
		String isbn = (String) response.getAttribute("isbn");
		int i=livreService.create(titre,auteur,isbn);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_details.jsp");
	}
	
}
