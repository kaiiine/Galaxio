
package ptraitement;

import java.util.ArrayList;
import java.util.Random;

public class Ordinateur extends Joueur{
    private String niveau;
    public final boolean ordi = true;
    
    public Ordinateur(String valNom, String valNiveau){
        /*
        Constructeur permttant de créer un objet Ordinateur
            Entrée : Nom (chaine de caractère) / Niveau(chaine de caractère)
        */
        super(valNom);
        this.niveau=valNiveau;
    }
    
    public String getNiveau(){
        /*
        Méthode permettant de récupérer le niveau de l'objet Ordinateur
            Entrée : Aucune
            Sortie : Niveau de l'Ordinateur (chaine de caractère) 
        */
        return this.niveau;
    }
    

    public Pion choixPion(int choix,ArrayList<Pion> pion){
        /*
        Méthode permettant à l'Ordinateur de choisir une pièce au hasard
            Entrée : Liste de Pion
            Sortie : Objet Pion 
        */
        Random rand = new Random();
        int nombre = rand.nextInt(0, pion.size());
        return pion.get(nombre);
    } 
    
    
}
