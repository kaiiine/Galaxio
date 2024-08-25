package ptraitement;


public class Case implements Comparable<Case>{
    private int centreX;
    private int centreY;
    private int rayon=1;
    private Integer id_case; //Doit être de type Integer pour que ça fonctionne dans le compareTo (prend en charge les classe type String, Integer et non les types primitifs tel que float, int...
    private String contenant="[]";
    private String Jcontenant="[]";
    
    public Case(int valID,int ptX, int ptY, int R){
        /*
        Constructeur permttant de créer un objet Case
            Entrée : Identifiant de la Case (chaine de caractère) / Coordonée en X(int) / Coordonnée en Y(int) / Rayon(int)
        */
        this.centreX=ptX;
        this.centreY=ptY;
        this.rayon=R;
        this.id_case=valID;
    }
    
    public Case(int valID,String valContenant,String valJContenant, int ptX, int ptY, int R){
        /*
        Constructeur permttant de créer un objet Joueur
            Entrée : Identifiant de la Case (chaine de caractère) / Contenant de la case (chaine de caractère) / Coordonée en X(int) / Coordonnée en Y(int) / Rayon(int)
        */
        this.id_case=valID;
        this.contenant=valContenant;
        this.Jcontenant=valJContenant;
        this.centreX=ptX;
        this.centreY=ptY;
        this.rayon=R;
    }
    

    
    public Integer getId(){
        /*
        Méthode permettant de récupérer l'identifiant de l'objet Case
            Entrée : Aucune
            Sortie : Identifiant de Case
        */
        return this.id_case;
    }
    
    public int getX(){
        /*
        Méthode permettant de récupérer la coordonnée X 
            Entrée : Aucune
            Sortie : Coordonnée X
        */
        return this.centreX;
    }
    
    public int getY(){
        /*
        Méthode permettant de récupérer la coordonnée Y 
            Entrée : Aucune
            Sortie : Coordonnée Y
        */
        return this.centreY;
    }
    
    public int getRayon(){
        /*
        Méthode permettant de récupérer le rayon
            Entrée : Aucune
            Sortie : Rayon
        */
        return this.rayon;
    }
    
    
    public String getContenant(){
        /*
        Méthode permettant de récupérer le contenant de la Case (soit "[]" si'il n'y a pas de pion ou l'identifiant du pion)
            Entrée : Aucune
            Sortie : Contenant (chaine de caractère)
        */
        return this.contenant;
    }
    public String getJContenant(){
        /*
        Méthode permettant de récupérer le contenant de la Case (soit "[]" si'il n'y a pas de pion ou l'identifiant du pion)
            Entrée : Aucune
            Sortie : Contenant (chaine de caractère)
        */
        return this.Jcontenant;
    }
    
    public void setContenant(String valContenant){
        /*
        Méthode permettant de modifier la valeur initiale du contenant de Case 
            Entrée : Contenant (chaine de caractère correspondant à l'identifiant du pion)
            Sortie : Aucune
        */
        this.contenant=valContenant;
    }
    
    public void setJContenant(String valContenant){
        /*
        Méthode permettant de modifier la valeur initiale du contenant de Case 
            Entrée : Contenant (chaine de caractère correspondant à l'identifiant du pion)
            Sortie : Aucune
        */
        this.Jcontenant=valContenant;
    }
    
    @Override
    public int compareTo(Case o) {
        /*
        Méthode permettant de créer un comparateur à ma classe Case afin de les trier dans un tableau 
            Entrée : Objet Case
            Sortie : Entier permettant de comparer les objets Case entre eux
        */
        return this.id_case.compareTo(o.id_case);
    }

    
}
