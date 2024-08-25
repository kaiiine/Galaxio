
package ptraitement;

import java.util.ArrayList;
import java.util.Random;


public class Joueur {
    
    public String nom;
    public final boolean ordi = false;
    
    public Joueur(String valNom){
        /*
        Constructeur permttant de créer un objet Joueur
            Entrée : Nom (chaine de caractère)
        */
        this.nom=valNom;
    }
    
    
    public Joueur[] TirageSort(Joueur j2){  //Tir au sort le premier joueur qui commencera.
        /*
        Méthode permettant d'effectuer un tirage au sort entre les objets Joueur
            Entrée : Objet Joueur
            Sortie : Tableau de Joueur
        */
        Joueur tab[] = {this,j2};
        Random rand = new Random();
        int numero = rand.nextInt(0,2); //Choisi un nombre random entre 0 et 1
        switch(numero){
            case 0:
                break;
            case 1:
                tab[0]=j2;
                tab[1]=this;
                break;
        }
        return tab;
    }
    
    public String getNom(){
        /*
        Méthode permettant de récupérer le nom de l'objet Joueur
            Entrée : Aucune
            Sortie : Nom du Joueur (chaine de caractère) 
        */
        return this.nom;
    }
    
    public String getNiveau(){
        /*
        Méthode permettant d'initialiser un niveau pour le jour de null afin de ne créer aucun priblème avec la méthode getNiveau() dans la classe Ordinateur
            Entrée : Aucune
            Sortie : null
        */
        return null;
    }
    
    
    public Pion choixPion(int choix, ArrayList<Pion> pions){
        /*
        Méthode permettant au Joueur de choisir un pion
            Entrée : Entier correspondant à la pièce qu'il a choisis / Liste des pions afin de récupérer le pion souhaité
            Sortie : Objet Pion 
        */
        return pions.get(choix);
    }
    
}
