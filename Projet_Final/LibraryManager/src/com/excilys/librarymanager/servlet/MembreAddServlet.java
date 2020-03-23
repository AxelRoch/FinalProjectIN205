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

public class MembreAddServlet extends HttpServlet {	
	
/// A faire : doGet -> ??, renvoyer vers détails (attention récupération de l'identifiant)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/membre_add.jsp");
		dispatcher.forward(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServiceException, IOException {
		doGet(request, response);
		MembreService membreService = membreService.getInstance();
		String prenom = (String) response.getAttribute("prenom");
		String nom = (String) response.getAttribute("nom");
        String adresse = (String) response.getAttribute("adresse");
        String email = (String) response.getAttribute("email");
        String telephone = (String) response.getAttribute("telephone");
        //Abonnement abonnement = (Abonnement) response.getAttribute("abonnement");
		int i=membreService.create(nom,prenom,adresse,email,telephone);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/membre_details.jsp");
	}
	
}