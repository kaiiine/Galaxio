
package ptraitement;

public class Plateau {
    
    private Case[][] plateau;
    private int nbrCases;
    private int ligne=0;
    private int colonne=0;
    
    public Plateau(int nbr){
        /*
        Constructeur permttant de créer un objet Plateau
            Entrée : Taille du plateau(int)
        */
        this.nbrCases=nbr;
        plateau=new Case[this.nbrCases][this.nbrCases];
    }
    
    
    public void add(Case cse){
        /*
        Méthode permettant d'ajouter un élément dans le plateau
            Entrée : Objet Case
            Sortie : Aucune
        */
        if(this.colonne>this.nbrCases-1){
            this.ligne+=1;
            this.colonne=0;
        }
        this.plateau[ligne][colonne]=cse;
        this.colonne+=1;
    }
    
    public Case get(int i, int j){
        /*
        Méthode permettant de récupérer une case du plateau
            Entrée : Entier i, Entier j
            Sortie : Objet Case
        */
        return this.plateau[i][j];
    }
    
    public void set(Case cse){
        for(int i=0;i<nbrCases;i++){
            for(int j=0;j<nbrCases;j++){
                
            }
        }
    }
    
    public void afficherPlateau(){
        /*
        Méthode permettant d'afficher le plateau
            Entrée : Aucune
            Sortie : Aucune
        */
        for(int i=0;i<this.nbrCases;i++){
            String ch="";
            for(int j=0;j<this.nbrCases;j++){
                ch+=this.plateau[i][j].getContenant()+" ";
            }
            System.out.println(ch);
        }
    }
    
    public int getNbrCases(){
        /*
        Méthode permettant de récupérer le nombre de case du plateau
            Entrée : Aucune
            Sortie : Taille du plateau
        */
        return this.nbrCases;
    }
    
}
