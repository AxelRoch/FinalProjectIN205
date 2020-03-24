package com.excilys.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.service.impl.*;
import com.excilys.librarymanager.exception.*;

public class LivreAddServlet extends HttpServlet {	
	
/// A faire : doGet -> ??, renvoyer vers dÃ©tails (attention rÃ©cupÃ©ration de l'identifiant)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_add.jsp");
		dispatcher.forward(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		try{
			LivreServiceImpl livreService = LivreServiceImpl.getInstance();
			String titre = request.getParameter("titre");
			String auteur = request.getParameter("auteur");
			String isbn = request.getParameter("isbn");
			int i=livreService.create(titre,auteur,isbn);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_details.jsp");
		}
		catch(ServiceException e){System.out.println(e.getMessage());}
	}
	
}
