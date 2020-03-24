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
import com.excilys.librarymanager.service.*;

public class MembreAddServlet extends HttpServlet {	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/membre_add.jsp");
		dispatcher.forward(request, response);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		try {
			MembreServiceImpl membreService = MembreServiceImpl.getInstance();
			String prenom = request.getParameter("prenom");
			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			//Abonnement abonnement = (Abonnement) response.getAttribute("abonnement");
			int i=membreService.create(nom,prenom,adresse,email,telephone);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/membre_details.jsp");
		}
		catch(ServiceException e){System.out.println(e.getMessage());}
	}
	
}