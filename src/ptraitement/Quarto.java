
package ptraitement;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Quarto {
    
    private String NOMFICHIER = "Sauvegarde";
    private ArrayList<Joueur> joueurs = new ArrayList<>(2);
    private Plateau plateau;
    private ArrayList<Pion> pions = new ArrayList<>();  
    private int cpt_case=0;
    private int nbrPions;
    
    public Quarto(){
        /*
        Constructeur permttant de créer un objet Quarto, réunissant toutes les fonctions pour pouvoir Lancer une partie
            Entrée : Aucune
        */
    }
    
    
    
    public void ajoutJoueur(String name){   
        /*
        Méthode permettant de créer d'ajouter un Joueur dans la liste des joueurs
            Entrée : nom du joueur
            Sortie : Aucune
        */
        Joueur joueur = new Joueur(name);
        this.joueurs.add(joueur);
    }
    
    public void ajoutJoueur(String name, String level){
        /*
        Méthode permettant de créer d'ajouter un Ordinateur dans la liste des joueurs
            Entrée : Nom "Ordi" / Niveau de l'ordinateur
            Sortie : Aucune
        */
        Ordinateur joueur = new Ordinateur(name, level);
        this.joueurs.add(joueur);
    }
    
    public void ajoutPion(String id, String valLabel){
        /*
        Méthode permettant de créer d'ajouter un Pion dans la liste des pions
            Entrée : ID du Pion
            Sortie : Aucune
        */
        Pion pion = new Pion(id,valLabel);
        this.pions.add(pion);
    }
    
    public void ajoutCases(int valID, int ptX, int ptY, int R){
        /*
        Méthode permettant de créer d'ajouter un objet Case dans la liste des cases
            Entrée : ID de la case / Position X / Position Y / Rayon
            Sortie : Aucune
        */
        Case cse = new Case(valID,ptX,ptY,R);
        this.plateau.add(cse);
    }
    
    public void ajoutCases(int valID, String valContenant, String valJContenant, int ptX, int ptY, int R){
        /*
        Méthode permettant de créer d'ajouter un objet Case dans la liste des cases
            Entrée : ID de la case / Contenant de la case / Position X / Position Y / Rayon
            Sortie : Aucune
        */
        Case cse = new Case(valID,valContenant,valJContenant,ptX,ptY,R);
        this.plateau.add(cse);
    }
    
    
    
    public void creationCases(){
        /*
        Méthode permettant de créer les cases du plateau
            Entrée : Aucune
            Sortie : Aucune
        */
        int centreX=100;
        int centreY=100;
        int rayon = 50;
        for(int i=0; i<this.plateau.getNbrCases(); i++){
            for(int j=0; j<this.plateau.getNbrCases(); j++){
                ajoutCases(this.cpt_case,centreX,centreY,rayon);
                centreX+=20;
                this.cpt_case+=1;
                
            }
            centreY+=20;
        }
    }
    
    public void creationPions(int nbr){
        /*
        Méthode permettant de créer les différents Pions du jeu
            Entrée : Taille du plateau (3, 4 ou 5)
            Sortie : Aucune
        */
        String[] forme={"R-","C-"};
        String[] couleur={"B-","N-"};
        String[] taille={"G","S"};
        String[] allure={"P","H"};
        String[] pointCouleur={"Y","M"};
        String label="src/pimages/Pieces";
        int cptLabel=1;
;        String id="";
        int cpt=0;
        if(nbr==3){
            label+="3x3/";
        }else if(nbr==3){
            label+="4x4/";
        }else{
            label+="5x5/";
        }
        
        
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                for(int h=0;h<2;h++){
                    id=forme[i]+couleur[j]+taille[h];
                    if(nbr>=4){
                        for(int o=0;o<2;o++){
                            if(nbr==5){
                                for(int p=0;p<2;p++){
                                    id=forme[i]+couleur[j]+taille[h]+"-"+allure[o]+"-"+pointCouleur[p];
                                    ajoutPion(id,label+cptLabel+".png");
                                    cptLabel+=1;
                                }
                            }else{
                                id=forme[i]+couleur[j]+taille[h]+"-"+allure[o];
                                ajoutPion(id,label+cptLabel+".png");
                                cptLabel+=1;
                            }
                            
                        }
                    }else{
                        ajoutPion(id,label+cptLabel+".png");
                        cptLabel+=1;
                    }
                        
                    
                }
            }
        }
    }
    
    public void creationPlateau(int nbr){
        /*
        Méthode permettant de créer le plateau
            Entrée : Taille du plateau (3, 4 ou 5)
            Sortie : Aucune
        */
        this.plateau = new Plateau(nbr);
    }
    
    
    
    public void afficherPlateau(){
        /*
        Méthode permettant d'afficher le plateau
            Entrée : Aucune
            Sortie : Aucune
        */
        this.plateau.afficherPlateau();
    }
    
    public void afficherPions(){
        /*
        Méthode permettant d'afficher les pions restant non placés dans le plateau
            Entrée : Aucune
            Sortie : Aucune
        */
        int cpt=0;
        for(Pion pion : this.pions){
            System.out.println(cpt+" : "+pion.getId());
            cpt+=1;
        }
    }
    
    public void afficherNoms(){
        /*
        Méthode permettant d'afficher les noms des joueurs
            Entrée : Aucune
            Sortie : Aucune
        */
        for(Joueur j : this.joueurs){
            System.out.println(j.nom);
        }
    }
    
    
    
    public ArrayList<Pion> getPions(){
        /*
        Méthode permettant de retourner la liste des pions restant, non placés dans le plateau
            Entrée : Aucune
            Sortie : liste des pions
        */
        return this.pions;
    }
    
    public Joueur getJoueur(int cpt){
        /*
        Méthode permettant de retourner un joueur 
            Entrée : compteur
            Sortie : Objet Joueur
        */
        return this.joueurs.get(cpt);
    }
    
    public ArrayList<Joueur> getJoueurs(){
        return joueurs;
    }
    
    public Plateau getPlateau(){
        return this.plateau;
    }
    
    
    public boolean verifGagnant3(Case cse){
        /*
        Méthode permettant de savoir si un Joueur a gagné
            Entrée : l'objet Case ou a été placé le pion
            Sortie : boolean true ou false
        */
        
        for(int k=0;k<(this.plateau.getNbrCases()*2-1);k+=2){
            ArrayList<Case> compare = new ArrayList<>();
            compare.add(cse);
            int cpt=1;
            for(int o=0;o<compare.size();o++){
                regardeAutour(compare.get(o),compare,k);
                if(compare.size()>=this.plateau.getNbrCases()){
                    return true;
                }
                cpt+=1;
            }
        }
        
        return false;
    }
    
    public void regardeAutour(Case cse,ArrayList<Case> compare, int k){
        /*
        Méthode permettant de connaître les différentes pièces autour d'une case
            Entrée : L'objet Case ou a été placé le pion / une liste de Case où 
                        sont stockés des cases contenant des pions avec au moins 
                        un attribut similaire à l'objet Case / compteur k permettant 
                        de comparer toutes les attributs d'un pion
            Sortie : liste des cases 
        */
        int cpt=0;
        
        int x=-1;   //Initialisation de x avec -1
        int y=-1;   //Initialisation de y avec -1
        for(int i=0;i<this.plateau.getNbrCases();i++){
            for(int j=0;j<this.plateau.getNbrCases();j++){
                if(this.plateau.get(i,j)==cse && !cse.getContenant().equals("[]")){ //Si la case est la même que celle en argument, alors on récupère les coordonnées du tableau dans laquelle est la case
                    x=i;
                    y=j;
                }
            }
            if(x!=(-1) && y!=(-1)){
                break;
            }
        }
        
        int coordX=x;
        int coordY=y;
        boolean ajout=true;
        if(x!=-1 && y!=-1){
            if(coordX>0){
            if(!this.plateau.get(coordX-1, coordY).getContenant().equals("[]") && this.plateau.get(coordX, coordY).getContenant().charAt(k)==this.plateau.get(coordX-1, coordY).getContenant().charAt(k)){ //Si le contenant de la case à coté est le même que celle de la case en argument, alors on l'ajoute dans le tableau
               for(int i=0;i<compare.size();i++){
                    if(this.plateau.get(coordX-1, coordY)==compare.get(i)){
                        ajout=false;
                    }
                }   
                if(ajout==true){
                    compare.add(this.plateau.get(coordX-1, coordY));  //Ajout dans le tableau les coordonnées de la case similaire
                    cpt+=1;
                }
                
            }
            }if(coordX<this.plateau.getNbrCases()-1){
                ajout=true;
                if(!this.plateau.get(coordX+1, coordY).getContenant().equals("[]") && this.plateau.get(coordX, coordY).getContenant().charAt(k)==this.plateau.get(coordX+1, coordY).getContenant().charAt(k)){ //Si le contenant de la case à coté est le même que celle de la case en argument, alors on l'ajoute dans le tableau
                    for(int i=0;i<compare.size();i++){
                        if(this.plateau.get(coordX+1, coordY)==compare.get(i)){
                            ajout=false;
                        }
                    }       
                    if(ajout==true){
                            compare.add(this.plateau.get(coordX+1, coordY));    //Ajout dans le tableau les coordonnées de la case similaire
                            cpt+=1;
                        }
                }
            }if(coordY>0){
                ajout=true;
                if(!this.plateau.get(coordX, coordY-1).getContenant().equals("[]") && this.plateau.get(coordX, coordY).getContenant().charAt(k)==this.plateau.get(coordX, coordY-1).getContenant().charAt(k)){ //Si le contenant de la case à coté est le même que celle de la case en argument, alors on l'ajoute dans le tableau
                    for(int i=0;i<compare.size();i++){
                        if(this.plateau.get(coordX, coordY-1)==compare.get(i)){
                            ajout=false;
                        }
                    }   
                    if(ajout==true){
                        compare.add(this.plateau.get(coordX, coordY-1));    //Ajout dans le tableau les coordonnées de la case similaire
                        cpt+=1;  
                    }

                }
            }if(coordY<this.plateau.getNbrCases()-1){
                ajout=true;
                if(!this.plateau.get(coordX, coordY+1).getContenant().equals("[]") && this.plateau.get(coordX, coordY).getContenant().charAt(k)==this.plateau.get(coordX, coordY+1).getContenant().charAt(k)){ //Si le contenant de la case à coté est le même que celle de la case en argument, alors on l'ajoute dans le tableau
                    for(int i=0;i<compare.size();i++){
                        if(this.plateau.get(coordX, coordY+1)==compare.get(i)){
                            ajout=false;
                        }
                    }   
                    if(ajout==true){
                        compare.add(this.plateau.get(coordX, coordY+1));//Ajout dans le tableau les coordonnées de la case similaire
                        cpt+=1;
                        }

                }
            }
        }
    }
    
    
    public boolean verifOrdi2(ArrayList<Case> total,Case cse,String niveau){
        int niv;
        switch(niveau){
            case "Moyen":
                niv=this.plateau.getNbrCases()-3; 
                break;
            default:
                niv=this.plateau.getNbrCases()-2;
        }
        
        for(int k=0;k<(this.plateau.getNbrCases()*2-1);k+=2){
            ArrayList<Case> compare = new ArrayList<>();
            compare.add(cse);
            for(int o=0;o<compare.size();o++){
                regardeAutour(compare.get(o),compare,k);
            }
            
            if(compare.size()>=niv){
                int x=0;
                int y=0;
                for(int b=0;b<compare.size();b++){  //Parcours compare
                    for(int o=0; o<this.plateau.getNbrCases();o++){ //Parcours plateau
                        for(int a=0;a<this.plateau.getNbrCases();a++){  //Parcours plateau
                            if(this.plateau.get(o,a).getContenant().equals(compare.get(b).getContenant())){ 
                                x=o;    //Si la case est la même que celle en argument, alors on récupère les coordonnées du tableau dans laquelle est la case
                                y=a;
                            }
                        }
                    }
                    if(x>0){
                        if(this.plateau.get(x-1,y).getContenant().equals("[]") && compare.size()==niv){
                            total.remove(this.plateau.get(x-1,y));
                        }else if(this.plateau.get(x-1,y).getContenant().equals("[]") && compare.size()>niv){
                            return true;
                        }
                    }
                    if(x<this.plateau.getNbrCases()-1){
                        if(this.plateau.get(x+1,y).getContenant().equals("[]") && compare.size()==niv){
                            total.remove(this.plateau.get(x+1,y));
                        }else if(this.plateau.get(x+1,y).getContenant().equals("[]") && compare.size()>niv){
                            return true;
                        }
                    }
                    if(y>0){
                        if(this.plateau.get(x,y-1).getContenant().equals("[]") && compare.size()==niv){
                            total.remove(this.plateau.get(x,y-1));
                        }else if(this.plateau.get(x,y-1).getContenant().equals("[]") && compare.size()>niv){
                            return true;
                        }
                    }
                    if(y<this.plateau.getNbrCases()-1){
                        if(this.plateau.get(x,y+1).getContenant().equals("[]") && compare.size()==niv){
                            total.remove(this.plateau.get(x,y+1));
                        }else if(this.plateau.get(x,y+1).getContenant().equals("[]") && compare.size()>niv){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
    public void afficher (Image img, JButton monLabel){
        monLabel.setVisible(true);
        if (img !=null){
        //mise à l'échelle de l'image
        img = img.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(),
        Image.SCALE_DEFAULT);
        //création d'une icône et assignation au label
        monLabel.setIcon(new ImageIcon(img));
        }
    }
    
    public void afficher (Image img, JLabel monLabel){
        monLabel.setVisible(true);
        if (img !=null){
        //mise à l'échelle de l'image
        img = img.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(),
        Image.SCALE_DEFAULT);
        //création d'une icône et assignation au label
        monLabel.setIcon(new ImageIcon(img));
        }
    }

    
    
    public int choixPionOrdi(String niveau){
        
        ArrayList<Pion> total = new ArrayList<>();
        for(int i=0;i<this.pions.size();i++){
            total.add(pions.get(i));
        }
        
        ArrayList<String> lettre = new ArrayList<>();
        int niv=this.plateau.getNbrCases()-1;
        Random rand = new Random();
        int moyen = rand.nextInt(0,2);
        
        if(niveau.equals("Difficile") || niveau.equals("Moyen") && moyen==1){
            for(int k=0;k<(this.plateau.getNbrCases()*2-1);k+=2){
                for(int i=0;i<plateau.getNbrCases();i++){
                    for(int j=0;j<plateau.getNbrCases();j++){
                        ArrayList<Case> compare = new ArrayList<>();
                        if(!plateau.get(i, j).getContenant().equals("[]")){
                            compare.add(plateau.get(i, j));
                            for(int o=0;o<compare.size();o++){
                                regardeAutour(compare.get(o),compare,k);
                            }
                        }
                        if(compare.size()==(this.plateau.getNbrCases()-1)){
                            lettre.add(""+compare.get(0).getContenant().charAt(k));
                        }

                    }
                }

            }
            for(int i=0;i<this.pions.size();i++){
                int cpt=0;
                String[] piece = this.pions.get(i).getId().split("-");
                for(int j=0;j<piece.length;j++){
                    for(int o=0;o<lettre.size();o++){
                        if(piece[j].equals(lettre.get(o))){
                            total.remove(pions.get(i));
                        }
                    }
                }
            }
            System.out.println("Le Total de total c'est --> "+total.size());
            if(total.size()>0){
                int nombre = rand.nextInt(0,total.size());
                for(int i=0;i<pions.size();i++){
                    if(pions.get(i)==total.get(nombre)){
                        return i;
                    }
                }
            }
        }
            
        
        for(int i=0;i<pions.size();i++){
            return i;
        }
        
            
        return 0;
        
    }
    
    public Case placerPion(Pion pion, int coordX, int coordY){
        /*
        Méthode permettant à un Joueur de placer un pion
            Entrée : L'objet Pion, Les coordonnées en x du pion dans le plateau, Les coordonnées en y du pion dans le plateau
            Sortie : Aucune
        */
        for(int i=0;i<this.pions.size();i++){
            if(this.pions.get(i)==pion && this.plateau.get(coordX, coordY).getContenant().equals("[]")){
                this.plateau.get(coordX, coordY).setContenant(pion.getId());
                this.plateau.get(coordX, coordY).setJContenant(pion.getDOSS_IMAGES());
                this.pions.remove(pion);
                return this.plateau.get(coordX, coordY);
            }
        }
        return null;
    }
    
    
    public int[] placerPionOrdi2(Pion pion,String niveau){
        /*
        Méthode permettant à un Ordinateur de placer un pion
            Entrée : L'objet Pion
            Sortie : Aucune
        */
        Random rand = new Random();
        int[] coords = new int[2];
        ArrayList<Case> total = new ArrayList<>();
        for(int o=0; o<this.plateau.getNbrCases(); o++){
            for(int k=0; k<this.plateau.getNbrCases(); k++){
                total.add(plateau.get(o, k));
            }
        }
        
        if(niveau.equals("Moyen") || niveau.equals("Difficile")){
            for(int o=0; o<this.plateau.getNbrCases(); o++){
                for(int k=0; k<this.plateau.getNbrCases(); k++){
                    if(!this.plateau.get(o, k).equals("[]")){
                        boolean gagne = verifOrdi2(total,this.plateau.get(o, k),niveau);
                        if(gagne){  //Il manque plus que une piece a placer et c'est la bonne, donc on regarde ou c'est vide pour que l'ordi place et gagne
                            if(o>0){
                                if(this.plateau.get(o-1,k).getContenant().equals("[]")){
                                    this.plateau.get(o-1,k).setContenant(pion.getId());
                                    this.plateau.get(o-1,k).setJContenant(pion.getDOSS_IMAGES());
                                    this.pions.remove(pion);
                                    coords[0]=o-1;
                                    coords[1]=k;
                                    return coords;
                                }
                            }
                            if(o<this.plateau.getNbrCases()-1){
                                if(this.plateau.get(o+1,k).getContenant().equals("[]")){
                                    this.plateau.get(o+1,k).setContenant(pion.getId());
                                    this.plateau.get(o+1,k).setJContenant(pion.getDOSS_IMAGES());
                                    this.pions.remove(pion);
                                    coords[0]=o+1;
                                    coords[1]=k;
                                    return coords;
                                }
                            }
                            if(k>0){
                                if(this.plateau.get(o,k-1).getContenant().equals("[]")){
                                    this.plateau.get(o,k-1).setContenant(pion.getId());
                                    this.plateau.get(o,k-1).setJContenant(pion.getDOSS_IMAGES());
                                    this.pions.remove(pion);
                                    coords[0]=o;
                                    coords[1]=k-1;
                                    return coords;
                                }
                            }
                            if(k<this.plateau.getNbrCases()-1){
                                if(this.plateau.get(o,k+1).getContenant().equals("[]")){
                                    this.plateau.get(o,k+1).setContenant(pion.getId());
                                    this.plateau.get(o,k+1).setJContenant(pion.getDOSS_IMAGES());
                                    this.pions.remove(pion);
                                    coords[0]=o;
                                    coords[1]=k+1;
                                    return coords;
                                }
                            }
                        }
                    }

                }
            }
            if(total.size()>0){
                int nbr = rand.nextInt(0,total.size());
                for(int o=0; o<this.plateau.getNbrCases(); o++){
                    for(int k=0; k<this.plateau.getNbrCases(); k++){
                        if(this.plateau.get(o, k)==total.get(nbr) && this.plateau.get(o, k).getContenant().equals("[]")){
                            this.plateau.get(o, k).setContenant(pion.getId());
                            this.plateau.get(o, k).setJContenant(pion.getDOSS_IMAGES());
                            this.pions.remove(pion);
                            System.out.println("Coucou, le indices sont : "+o+" et "+k);
                            coords[0]=o;
                            coords[1]=k;
                            return coords;
                        }
                    }
                }
            }
        }
        
        
        for(int o=0; o<this.plateau.getNbrCases(); o++){
            for(int k=0; k<this.plateau.getNbrCases(); k++){
                if(this.plateau.get(o, k).getContenant().equals("[]")){
                    this.plateau.get(o, k).setContenant(pion.getId());
                    this.plateau.get(o, k).setJContenant(pion.getDOSS_IMAGES());
                    this.pions.remove(pion);
                    coords[0]=o;
                    coords[1]=k;
                    return coords;
                }
            }
        }
        
        return null;
            
    }
    
    
    public void sauvegarder(){
        /*
        Méthode permettant de sauvegarder une Partie
            Entrée : Aucune
            Sortie : Aucune
        */
        try{
            FileWriter fich = new FileWriter(NOMFICHIER);
            
            fich.write("Joueurs:"+System.lineSeparator());
            for(Joueur x : this.joueurs){
                //System.out.println(x.getClass().getName());
                if(x.getClass().getSimpleName().equals("Joueur")){
                    fich.write(x.getNom()+System.lineSeparator());
                }else{
                    fich.write(x.getNom()+"-"+x.getNiveau()+System.lineSeparator());
                }
                
            }
            
            fich.write("Pions:"+System.lineSeparator());
            System.out.println("quarto   : "+this.pions.size());
            for(Pion x : this.pions){
                fich.write(x.getId()+"_"+x.getDOSS_IMAGES()+System.lineSeparator());
            }
            
            fich.write("Cases:"+System.lineSeparator());
            fich.write(this.plateau.getNbrCases()+System.lineSeparator());
            for(int i=0;i<this.plateau.getNbrCases();i++){
                for(int j=0;j<this.plateau.getNbrCases();j++){
                    Case x = this.plateau.get(i, j);
                    fich.write(x.getId()+"_"+x.getContenant()+"_"+x.getJContenant()+"_"+x.getX()+"_"+x.getY()+"_"+x.getRayon()+System.lineSeparator());
                }
            }
            
            fich.close();
        }catch(IOException ex){
            System.out.println("La partie n'a pas pu être sauvegardé");
        }
    }
    
    public void charger() throws IOException{
        /*
        Méthode permettant à un Joueur de placer un pion
            Entrée : L'objet Pion, Les coordonnées en x du pion dans le plateau, Les coordonnées en y du pion dans le plateau
            Sortie : Aucune
        */
        FileReader fich = new FileReader(NOMFICHIER);
        BufferedReader br = new BufferedReader(fich);
        String ligne = br.readLine();
        ligne = br.readLine();  //Saute la ligne "Joueurs:"
        while(!ligne.equals("Pions:")){
            String[] tab = ligne.split("-");
            if(tab.length>1){                       //Si la condition est vraie, alors l'objet est un  ordinateur
                ajoutJoueur(tab[0],tab[1]);
                ligne=br.readLine();
            }else{                                  //Sinon c'est un joueur
                ajoutJoueur(ligne);
                ligne=br.readLine();
            }
        }
        
        
        ligne = br.readLine();  //Saute la ligne "Pions:"
        while(!ligne.equals("Cases:")){
            String[] tab2 = ligne.split("_");
            ajoutPion(tab2[0],tab2[1]);
            ligne=br.readLine();    
        }
        
        
        
        ligne = br.readLine();  //Saute la ligne "Cases:"
        creationPlateau(Integer.parseInt(ligne));
        ligne=br.readLine();    //Saute la ligne contenant la taille du plateau
        while(ligne!=null){
            this.cpt_case+=1;
            String[] x=ligne.split("_");
            ajoutCases(Integer.parseInt(x[0]),x[1],x[2],Integer.parseInt(x[3]),Integer.parseInt(x[4]),Integer.parseInt(x[5]));
            ligne=br.readLine();
        }

        fich.close();

    }
}
