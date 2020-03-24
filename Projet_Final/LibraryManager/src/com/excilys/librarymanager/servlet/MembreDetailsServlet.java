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
import com.excilys.librarymanager.modele.*;
import com.excilys.librarymanager.service.impl.*;

public class MembreDetailsServlet extends HttpServlet {	
	
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
		try{
			int id =(int) request.getAttribute("id");
			MembreServiceImpl membreService = MembreServiceImpl.getInstance();
			EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
			Membre membre = membreService.getById(id);

			List<Emprunt> emprunts = new ArrayList<>();
			emprunts=empruntService.getListCurrentByMembre(id);

			request.setAttribute("idDuMembre", membre.getId());
			request.setAttribute("prenom", membre.getPrenom());
			request.setAttribute("nom", membre.getNom());
			request.setAttribute("adresse", membre.getAdresse());
			request.setAttribute("email", membre.getEmail());
			request.setAttribute("telephone", membre.getTelephone());
			request.setAttribute("abonnement", membre.getAbonnement());
			request.setAttribute("emprunts", emprunts); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_details.jsp");
			dispatcher.forward(request, response);
		}
		catch (ServiceException e){System.out.println(e.getMessage());}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		int idDuMembre = Integer.parseInt( request.getParameter("id") );
		String prenom =  request.getParameter("prenom");
		String nom =  request.getParameter("nom");
        String adresse =  request.getParameter("adresse");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        Abonnement abonnement = Abonnement.valueOf(request.getParameter("abonnement"));
		Membre membre = new Membre();
		membre.setId(idDuMembre);
		membre.setPrenom(prenom);
		membre.setNom(nom);
        membre.setAdresse(adresse);
        membre.setEmail(email);
        membre.setTelephone(telephone);
		membre.setAbonnement(abonnement);
		try{
			MembreServiceImpl membreService = MembreServiceImpl.getInstance();
			membreService.update(membre);
		}
		catch (ServiceException e){System.out.println(e.getMessage());}
	}
	
}