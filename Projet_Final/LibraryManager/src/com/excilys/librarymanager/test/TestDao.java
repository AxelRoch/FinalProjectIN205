package com.excilys.librarymanager.test;

import com.excilys.librarymanager.dao.impl.*;
import com.excilys.librarymanager.dao.*;
import com.excilys.librarymanager.modele.*;
import com.excilys.librarymanager.exception.*;

import java.sql.Date;
import java.time.LocalDate;


public class TestDao
{
    public static void main( String[] args )
    {

        EmpruntDaoImpl daoEmprunts = EmpruntDaoImpl.getInstance();
        MembreDaoImpl daoMembres = MembreDaoImpl.getInstance();
        LivreDaoImpl daoLivres = LivreDaoImpl.getInstance();

        Membre alizee = new Membre(0001, "basset", "alizee", "le deves", "alizee.basset@ensta-paris.fr", "0123456789", Abonnement.BASIC);
        Membre axel = new Membre(0002, "rochel", "axel", "palaiseau", "axel.rochel@ensta-paris.fr", "9876543210", Abonnement.VIP);

        Livre livre =new Livre(0001, "Java Pour Les Nuls", "Inconnu","0001");

        Emprunt emprunt = new Emprunt(0001, alizee, livre, LocalDate.of(2019,11,1), LocalDate.of(2020,03,24));

        System.out.println("ok");

        try {
            daoMembres.create(alizee.getNom(), alizee.getPrenom(), alizee.getAdresse(), alizee.getEmail(),alizee.getTelephone());
            daoLivres.create(livre.getTitre(), livre.getAuteur(), livre.getIsbn());
            daoEmprunts.create(emprunt.getmembre().getId(), emprunt.getlivre().getId(), emprunt.getDateEmprunt());
            daoEmprunts.getListCurrent();
            daoEmprunts.getList();
            daoLivres.getList();
            daoMembres.getList();
        } catch (DaoException error) {
        }
    }
}