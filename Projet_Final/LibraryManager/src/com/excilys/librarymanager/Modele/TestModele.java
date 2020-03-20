package com.excilys.librarymanager.Modele;

public class TestModele{
    public static void main( String[] args ){
        Date dateEmprunt = new Date(2019,11,01);
        Date dateRetour = new Date(2020,03,24);
        Livre livre1 =new Livre(0001, Java Pour Les Nuls, Inconnu, 0001);
        Livre livre2 =new Livre(0002, Les Servlets Pour Les Nuls, Inconnu, 0001);
        Membre alizee = new Membre(0001, basset, alizee, le deves, alizee.basset@ensta-paris.fr, 0123456789, BASIC);
        Membre axel = new Membre(0002, rochel, axel, palaiseau, axxel.rochel@ensta-paris.fr, 9876543210, VIP);
        Emprunt emprunt1 = new Emprunt(0001, axel, livre2, dateEmprunt, dateRetour);
        Emprunt emprunt2 = new Emprunt (0002, alizee, livre1, dateEmprunt, dateRetour);
        livre1.toString();
        livre2.toString();
        alizee.toString();
        axel.toString();
        emprunt1.toString();
        emprunt2.toString();
    }
}
