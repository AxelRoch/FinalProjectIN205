package com.excilys.librarymanager.test;

import java.sql.Date;
import java.time.LocalDate;


import com.excilys.librarymanager.modele.*;


public class TestModele{
    public static void main( String[] args ){
        Date dateEmprunt = new Date(2019,11,01);
        Date dateRetour = new Date(2020,03,24);
        Livre livre1 =new Livre(0001, "Java Pour Les Nuls", "Inconnu","0001");
        Livre livre2 =new Livre(0002, "Les Servlets Pour Les Nuls", "Inconnu", "0001");
        Membre alizee = new Membre(0001, "basset", "alizee", "le deves", "alizee.basset@ensta-paris.fr", "0123456789", Abonnement.BASIC);
        Membre axel = new Membre(0002, "rochel", "axel", "palaiseau", "axel.rochel@ensta-paris.fr", "9876543210", Abonnement.VIP);
        Emprunt emprunt1 = new Emprunt(0001, axel, livre2, dateEmprunt, dateRetour);
        Emprunt emprunt2 = new Emprunt (0002, alizee, livre1, dateEmprunt, dateRetour);
        System.out.println(livre1);
        System.out.println(livre2);
        System.out.println(alizee);
        System.out.println(axel);
        System.out.println(emprunt1);
        System.out.println(emprunt2);
    }
}
