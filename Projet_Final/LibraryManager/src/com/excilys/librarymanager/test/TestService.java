package com.excilys.librarymanager.test;

import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Abonnement;

import com.excilys.librarymanager.service.LivreServiceImpl;
import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.service.EmpruntServiceImpl;

import com.excilys.librarymanager.exception.ServiceException;
import java.time.LocalDate;


public class TestService{
    public static void main( String[] args ){

        Membre alizee = new Membre(0001, "basset", "alizee", "le deves", "alizee.basset@ensta-paris.fr", "0123456789", Abonnement.BASIC);

        Livre livre =new Livre(0001, "Java Pour Les Nuls", "Inconnu","0001");

        Emprunt emprunt = new Emprunt(0001, alizee, livre, LocalDate.of(2019,11,1), LocalDate.of(2020,03,24));

        try {
            serviceMembres.create(alizee.getNom(), alizee.getPrenom(), alizee.getAdresse(), alizee.getEmail(),alizee.getTelephone());
            serviceLivres.create(livre.getTitre(), livre.getAuteur(), livre.getIsbn());
            serviceEmprunts.create(emprunt.getMembre().getId(), emprunt.getLivre().getId(), emprunt.getDateEmprunt());
            serviceEmprunts.getListCurrent();
            serviceEmprunts.returnBook(emprunt.getId());
            serviceLivres.getList();
            serviceMembres.getList();
        } catch (ServiceException error) {
        }

    }
}