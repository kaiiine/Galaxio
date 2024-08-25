
package ptraitement;

import java.io.IOException;
import java.util.Scanner;


public class Jeu {

    public static void main(String[] args) {
        
        Quarto quarto = new Quarto();
        
        
        
        //------------------------------------------          DEBUT DE LA RECUPERATION DES DONNEES AUPRES DE L'UTILISATEUR            ---------------------------------------------//
        
        Scanner sc = new Scanner(System.in);
        String modeJoueur="";
        int modeJeu=0;
        boolean nouvellePartie=true;
        String reponse="";
        String reponse2="";
        Joueur[] joueurs = new Joueur[2];
        
        
        //----- Début de la demande nouvelle/vharger partie -----
        
        
        System.out.println("Bonjour, que souhaitez vous faire :\n");
        System.out.println("1 --> Créer une nouvelle partie");
        System.out.println("2 --> Continuer une ancienne partie\n");
        while(!reponse.equals("1") && !reponse.equals("2")){
            sc=new Scanner(System.in);  //Renouvelle le Scanner pour éviter les bugs
            reponse = sc.nextLine();
            switch(reponse){
                case "1":
                    nouvellePartie = true;
                    break;
                case "2":
                    nouvellePartie = false;
                    break;
                default:
                    System.out.println("Erreur, veuillez resaisir entre 1 et 2");
            }
        }
       
        
        //----- Début de la demande 1V1/1VOrdi -----
        
        
        if(nouvellePartie==true){
            sc = new Scanner(System.in);
            reponse="";
            
            System.out.println("Quelle mode souhaitez vous jouer :\n");
            System.out.println("1 --> 1 VS 1");
            System.out.println("2 --> 1 VS Ordi\n");
            
            while(!reponse.equals("1") && !reponse.equals("2")){
                reponse=sc.nextLine();
                if(reponse.equals("1")){
                    modeJoueur="1v1";
                    System.out.print("Joueur 1, veuillez saisir votre pseudo : ");
                    quarto.ajoutJoueur(sc.nextLine());   //Création du joueur 1
                    System.out.print("Joueur 2, veuillez saisir votre pseudo : ");
                    quarto.ajoutJoueur(sc.nextLine());   //Création du joueur 2
                    
                    
                }else if(reponse.equals("2")){
                    modeJoueur="1vOrdi";
                    System.out.print("Veuillez saisir votre pseudo : ");
                    quarto.ajoutJoueur(sc.nextLine());   //Création du joueur 1
                    System.out.println("\nParfait "+quarto.getJoueur(0).getNom()+"\nQuel niveau souhaitez vous choisir pour l'ordinateur :\n");
                    System.out.println("1 --> Facile");
                    System.out.println("2 --> Moyen");
                    System.out.println("3 --> Extreme");
                    while(!reponse2.equals("1") && !reponse2.equals("2") && !reponse2.equals("3")){
                        sc = new Scanner(System.in);
                        reponse2=sc.nextLine();
                        if(reponse2.equals("1") || reponse2.equals("2") || reponse2.equals("3")){
                            quarto.ajoutJoueur("Ordi",reponse2);    //Création de l'Ordinateur
                        }else{
                            System.out.println("Erreur. Veuillez choisir entre 1,2 ou 3");
                        }
                    }
                    
                }else{
                    System.out.println("Erreur. Veuillez saisir 1 ou 2\n");
                }   
            }
            
            
            //----- Début de la demande de la taille du plateau -----
        
        
            reponse="";
            System.out.println("Parfait !! Veuillez choisir votre mode de jeu : \n");
            System.out.println("1 --> 3x3");
            System.out.println("2 --> 4x4");
            System.out.println("3 --> 5x5");

            while(modeJeu!=3 && modeJeu!=4 && modeJeu!=5){
                sc=new Scanner(System.in);
                reponse=sc.nextLine();
                switch(reponse){
                    case "1":
                        modeJeu=3;
                        break;
                    case "2":
                        modeJeu=4;
                        break;
                    case "3":
                        modeJeu=5;
                        break;
                    default:
                        System.out.println("Erreur. Veuillez saisir 1, 2 ou 3\n");
                        break;
                }
            }
            
            
            System.out.println("Parfait !! Commençons !");
        
            quarto.creationPlateau(modeJeu);
            quarto.creationCases();
            quarto.creationPions(modeJeu);

            quarto.afficherPlateau();
            quarto.afficherPions();

            quarto.sauvegarder();
            
            
        }
        else{
            System.out.println("\nContinuons votre partie!!\n");
            try{
                quarto.charger();
            }catch(IOException ex){
                System.out.println("Problème lors du chargement du fichier");
            }


            quarto.afficherNoms();
            quarto.afficherPlateau();
            quarto.afficherPions();
            }
        
        
        
        
        //------------------------------------------          FIN DE LA RECUPERATION DES DONNEES AUPRES DE L'UTILISATEUR            ---------------------------------------------//
        
        
        /*
        Joueur j1 = quarto.getJoueur(0);
        Joueur j2 = quarto.getJoueur(1);
        Joueur[] tabJoueur = {j2,j1};
        sc=new Scanner(System.in);
        Pion pion = null;
        int cpt=0;
        Case cse =null;
        
        System.out.println(quarto.choixPionOrdi("Difficile").getId());
        */
        /*
        while(true){
            
            try{
                System.out.println("\n\n"+tabJoueur[1].getNom()+ " veuillez choisir un pion : ");
                if(tabJoueur[1].getClass().getSimpleName().equals("Ordinateur")){
                    pion = tabJoueur[1].choixPion(0, quarto.getPions());
                    System.out.println("Voici le pion Choisi : "+pion.getId());
                }else{
                    quarto.afficherPions();
                    System.out.print("\npion : ");
                    int choix = sc.nextInt();
                    pion = tabJoueur[1].choixPion(choix, quarto.getPions());
                }
                if(tabJoueur[0].getClass().getSimpleName().equals("Ordinateur")){
                    cse=quarto.placerPionOrdi2(pion,"Difficile");
                }else{
                    System.out.print("Choisir des coordonnées en X : ");
                    int x = sc.nextInt();
                    System.out.print("Choisir des coordonnées en Y : ");
                    int y = sc.nextInt();
                    cse=quarto.placerPion(pion, x, y);
                }
                
                sc=new Scanner(System.in);
                quarto.afficherPlateau();
                if(quarto.verifGagnant3(cse)){
                    System.out.println("Bravo, "+tabJoueur[0].getNom()+" a gagné");
                    break;
                }
                quarto.sauvegarder();
                Joueur j3=tabJoueur[0];
                tabJoueur[0]=tabJoueur[1];
                tabJoueur[1]=j3;
                cpt+=1;
                if(cpt==2){
                    System.out.println("Souhaitez -vous arrêter la partie ? (O/N)");
                    String answer = sc.nextLine();
                    if(answer.equals("O")){
                        break;
                    }
                    cpt=0;
                }
                    
            }catch(NullPointerException  ex){
                System.out.println("Erreur");
            }
        }
        
            
        */
        
        
        
    }
    
    
}
