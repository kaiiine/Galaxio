
package ptraitement;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static ptraitement.Photo.DOSS_IMAGES;


public class Pion implements Comparable<Pion>{
    private String id_pion;
    private String forme;   //Couleur Arruère Plan
    private String couleur; // Astronaute ou alienne
    private String taille;  //Vaisseau ou OVNI
    private String allure;   // Les planètes
    private String couleurPoint;
    
    
    private String nomFichier;
    private Image img;
    public  String DOSS_IMAGES = "" ;
    
    
    public Pion(String valID, String valLabel){
        /*
        Constructeur permttant de créer un objet Pion
            Entrée : Identifiant du Pion (chaine de caractère)
        */
        this.id_pion=valID;
        createID();
        DOSS_IMAGES+=valLabel;
        Toolkit t = Toolkit.getDefaultToolkit();
        img = t.getImage(DOSS_IMAGES);
    }
    

    
    
    public void createID(){
        /*
        Méthode permettant de transformer l'identifiant du pion en attribut 
            Entrée : Aucune
            Sortie : Aucune
        */
        String tab[] = this.id_pion.split("-");
        int taille = tab.length;
        
        if(tab[0].equals("C")){
            this.forme="Carré";
        }else{
            this.forme="Rond";
        }
        
        if(tab[1].equals("B")){
            this.couleur="Blanc";
        }else{
            this.couleur="Noir";
        }
        
        if(tab[2].equals("G")){
            this.taille="Grand";
        }else{
            this.taille="Petit";
        }
        
        if(taille==4){
            if(tab[3].equals("C")){
                this.allure="Creux";
            }else{
                this.allure="Pleins";
            }
        }
     
        if(taille==5){
            if(tab[3].equals("C")){
                this.couleurPoint="Orange";
            }else{
                this.couleurPoint="Noir";
            }
        }
    }
    
    
    
    public String getId(){
        /*
        Méthode permettant de récupérer l'identifiant de l'objet Pion
            Entrée : Aucune
            Sortie : Identifiant du Pion (chaine de caractère) 
        */
        return this.id_pion;
    }
    
    public String getForme(){
        /*
        Méthode permettant de récupérer la forme de l'objet Pion
            Entrée : Aucune
            Sortie : Forme du Pion (chaine de caractère) 
        */
        return this.forme;
    }
    
    public String getCouleur(){
        /*
        Méthode permettant de récupérer la couleur de l'objet Pion
            Entrée : Aucune
            Sortie : Couleur du Pion (chaine de caractère) 
        */
        return this.couleur;
    }
    
    public String getTaille(){
        /*
        Méthode permettant de récupérer la taille de l'objet Pion
            Entrée : Aucune
            Sortie : Taille du Pion (chaine de caractère) 
        */
        return this.taille;
    }
    
    public String getAllure(){
        /*
        Méthode permettant de récupérer l'allure de l'objet Pion
            Entrée : Aucune
            Sortie : Allure du Pion (chaine de caractère) 
        */
        return this.allure;
    }
    
    public String getDOSS_IMAGES(){
        return this.DOSS_IMAGES;
    }
    
    public Image getImage(){
        return this.img;
    }
    
    
    
    @Override
    public int compareTo(Pion o) {
        /*
        Méthode permettant de créer un comparateur à ma classe Pion afin de les trier dans un tableau 
            Entrée : Objet Pion
            Sortie : Entier permettant de comparer les objets Pion entre eux
        */
        return this.id_pion.compareTo(o.id_pion);
    }
}
