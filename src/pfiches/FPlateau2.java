/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pfiches;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import ptraitement.Joueur;
import ptraitement.Ordinateur;
import ptraitement.Photo;
import ptraitement.Pion;
import ptraitement.Quarto;

/**
 *
 * @author quent
 */
public class FPlateau2 extends javax.swing.JDialog {

    private  String DOSS_IMAGES = "src/pimages/" ;
    private Photo plateau = new Photo("plateau.jpg");
    private Quarto quarto;
    private ArrayList<JButton> JCases;
    private ArrayList<JButton> JPions;
    private int nbrPionAfficher=0;
    private int nbrPioche = 16;
    private Integer choixPion=null;
    private JButton button;
    private JButton button2;
    private boolean premsPioche = true;
    private int nbrCases=2;
    private Joueur[] joueurs;
    private String niveau;
    private boolean choixOrdi=false;
    private boolean musique=true;
    private String gagnant="";
    /**
     * Creates new form FPlateau2
     */
    public FPlateau2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //Initialisation de la partie
        //______________________________________________________________________________________________
        //Initialisation de la classe Quarto
        quarto = new Quarto();
        
        //Initialisation des tailles, des images et de l'affichage des différents composants de l'interface graphique
        this.setLocation(-10,0);
        this.setSize(1600,830);
        lBackground.setSize(1550,800);
        lChoix.setSize(300,45);
        bFlecheD.setSize(57,44);
        bFlecheG.setSize(57,44);
        lVolume.setSize(93,36);
        
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage(DOSS_IMAGES+"Plateau_Galaxio.png");
        quarto.afficher(img,lBackground);
        
        Image img2 = t.getImage(DOSS_IMAGES+"setting1.png");
        quarto.afficher(img2,bSetting);
        
        Image img3 = t.getImage(DOSS_IMAGES+"Suivant.png");
        quarto.afficher(img3,jSuivant);
        
        
        Image img4 = t.getImage(DOSS_IMAGES+"Retour.png");
        quarto.afficher(img4,jRetour);
        
        Image img6 = t.getImage(DOSS_IMAGES+"Volume_moins.png");
        quarto.afficher(img6,bFlecheG);
        
        Image img7 = t.getImage(DOSS_IMAGES+"Volume_plus.png");
        quarto.afficher(img7,bFlecheD);
        
        pSetting.setVisible(false);
        jRetour.setVisible(false);
        jSuivant.setVisible(false);
        
        //Récupération des données issus de la fiche Choix
        boolean nouveau = ((FAccueil)this.getParent()).getFichChoix().getNouveau();
        int modeJeu = ((FAccueil)this.getParent()).getFichChoix().getModeJeu();
        nbrCases = ((FAccueil)this.getParent()).getFichChoix().getNbrCases();
        String[] joueur = ((FAccueil)this.getParent()).getFichChoix().getNom();
        
        
        if(nouveau){    //Intialisation de la partie en cas d'une nouvelle partie
            quarto.creationPions(nbrCases);
            quarto.creationPlateau(nbrCases);
            quarto.creationCases();
            if(joueur[1].length()>0){
                quarto.ajoutJoueur(joueur[0]);
                quarto.ajoutJoueur(joueur[1]);
            }else{
                niveau = ((FAccueil)this.getParent()).getFichChoix().getNiveauOrdi();
                quarto.ajoutJoueur(joueur[0]);
                quarto.ajoutJoueur("Ordi",niveau);
            }
            joueurs=quarto.getJoueur(0).TirageSort(quarto.getJoueur(1));    //Initialise le tableau de Joueur avec j1 et j2
            if(joueurs[0]!=quarto.getJoueur(0)){
                switchJoueurs();
            }
            
            
        }else{  //Intialisation de la partie en cas d'un chargement de partie
            try {
                quarto.charger();
                nbrCases=quarto.getPlateau().getNbrCases();
                nbrPioche=quarto.getPions().size();
                for(int i=0;i<quarto.getJoueurs().size();i++){
                    if(quarto.getJoueurs().get(i).getNom().equals("Ordi")){
                        niveau=quarto.getJoueurs().get(i).getNiveau();
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la partie", "Error 404", JOptionPane.INFORMATION_MESSAGE);
            }
        }
            
        //Initialisation du plateau 
        JCases = new ArrayList<>();
        jPlateau.setLayout(new GridLayout(nbrCases,nbrCases));
        for(int i=0;i<nbrCases*nbrCases;i++){
            button2 = new JButton();
            if(quarto.getPlateau().getNbrCases()==3){    
                button2.setSize(230, 230);
            }else if(quarto.getPlateau().getNbrCases()==4){
                button2.setSize(175, 175);
            }else{
                button2.setSize(140, 140);
            }
            button2.setBorderPainted(false);
            button2.setFocusPainted(false);
            button2.setLocation(0,0);
            JCases.add(button2);
            jPlateau.add(button2);
        }
        if(!nouveau){   //Condition pour setup le plateau si on charge une partie
            setupPlateau();
        }
        
        //Initialisation de la pioche
        if(quarto.getPions().size()>16){
            jSuivant.setVisible(true);
        }
        if(nbrCases==3){
            nbrPioche=8;
        }
        JPions = new ArrayList<>();
        pPioche.setLayout(new GridLayout(4,4));
        for(int i=0;i<16;i++){
            button = new JButton();
            button.setSize(120,120);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            if(i<nbrPioche){
            JPions.add(button);
            pPioche.add(JPions.get(i));
            }else{
                button.setVisible(false);
                pPioche.add(button);
            }
        }
        refreshPioche();
        
        //______________________________________________________________________________________________
        
        
        
        // Gère les événements des boutons de la pioche
        for(int o=0;o<JPions.size();o++){
            final int cpt = o;
            JPions.get(o).addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // Votre code de traitement ici
                    pourPioche(cpt);
                }  
            });
        }
        
        // Gère les évènements des plateaux
        for(int o=0;o<JCases.size();o++){
            final int cpt = o;
            JCases.get(o).addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // Votre code de traitement ici
                    pourPlateau(cpt);
                }  
            });
        }
            
        //Initialisation des textes pour savoir qui va choisir une pièce selon les joueurs et l'ordinateur
        if(quarto.getJoueurs().get(0).getNom().equals("Ordi")){
            Image img5 = t.getImage(DOSS_IMAGES+"Choix.png");
            quarto.afficher(img5,lChoix);
            lNom.setText(quarto.getJoueurs().get(1).getNom());
        }else if(quarto.getJoueurs().get(1).getNom().equals("Ordi")){
            Image img5 = t.getImage(DOSS_IMAGES+"Placer.png");
            quarto.afficher(img5,lChoix);
            lNom.setText(quarto.getJoueurs().get(0).getNom());
            pourPiocheOrdi();
        }else{
            Image img5 = t.getImage(DOSS_IMAGES+"Choix.png");
            quarto.afficher(img5,lChoix);
            lNom.setText(quarto.getJoueurs().get(1).getNom());
        }
        
       
        // Ferme le programme lorsqu'on appuie sur la croix
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pPioche = new javax.swing.JPanel();
        jSuivant = new javax.swing.JButton();
        jRetour = new javax.swing.JButton();
        bSetting = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPlateau = new javax.swing.JPanel();
        pSetting = new javax.swing.JPanel();
        lSetting = new javax.swing.JLabel();
        bSauvegarde = new javax.swing.JButton();
        bRetourSetting = new javax.swing.JButton();
        bAccueil = new javax.swing.JButton();
        bFlecheD = new javax.swing.JButton();
        bFlecheG = new javax.swing.JButton();
        lVolume = new javax.swing.JLabel();
        lNom = new javax.swing.JLabel();
        lChoix = new javax.swing.JLabel();
        lBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1680, 1050));
        getContentPane().setLayout(null);

        pPioche.setOpaque(false);

        javax.swing.GroupLayout pPiocheLayout = new javax.swing.GroupLayout(pPioche);
        pPioche.setLayout(pPiocheLayout);
        pPiocheLayout.setHorizontalGroup(
            pPiocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pPiocheLayout.setVerticalGroup(
            pPiocheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(pPioche);
        pPioche.setBounds(870, 200, 500, 500);

        jSuivant.setContentAreaFilled(false);
        jSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSuivantActionPerformed(evt);
            }
        });
        getContentPane().add(jSuivant);
        jSuivant.setBounds(1250, 710, 160, 45);

        jRetour.setContentAreaFilled(false);
        jRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRetourActionPerformed(evt);
            }
        });
        getContentPane().add(jRetour);
        jRetour.setBounds(860, 710, 160, 48);

        bSetting.setBorderPainted(false);
        bSetting.setContentAreaFilled(false);
        bSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSettingActionPerformed(evt);
            }
        });
        getContentPane().add(bSetting);
        bSetting.setBounds(1440, 0, 100, 90);

        jPanel1.setOpaque(false);

        jPlateau.setBackground(new java.awt.Color(141, 117, 86));
        jPlateau.setOpaque(false);
        jPlateau.setPreferredSize(new java.awt.Dimension(800, 800));

        javax.swing.GroupLayout jPlateauLayout = new javax.swing.GroupLayout(jPlateau);
        jPlateau.setLayout(jPlateauLayout);
        jPlateauLayout.setHorizontalGroup(
            jPlateauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        jPlateauLayout.setVerticalGroup(
            jPlateauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        pSetting.setBackground(new java.awt.Color(153, 0, 153));

        lSetting.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        lSetting.setForeground(new java.awt.Color(255, 255, 255));
        lSetting.setText("Paramètres");

        bSauvegarde.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        bSauvegarde.setText("Sauvegarder");
        bSauvegarde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSauvegardeActionPerformed(evt);
            }
        });

        bRetourSetting.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        bRetourSetting.setText("Retour");
        bRetourSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRetourSettingActionPerformed(evt);
            }
        });

        bAccueil.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 12)); // NOI18N
        bAccueil.setText("Ecran d'Accueil");
        bAccueil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAccueilActionPerformed(evt);
            }
        });

        bFlecheD.setContentAreaFilled(false);
        bFlecheD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFlecheDActionPerformed(evt);
            }
        });

        bFlecheG.setContentAreaFilled(false);
        bFlecheG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFlecheGActionPerformed(evt);
            }
        });

        lVolume.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        lVolume.setForeground(new java.awt.Color(255, 255, 255));
        lVolume.setText("Volume ON");

        javax.swing.GroupLayout pSettingLayout = new javax.swing.GroupLayout(pSetting);
        pSetting.setLayout(pSettingLayout);
        pSettingLayout.setHorizontalGroup(
            pSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bSauvegarde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bRetourSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bAccueil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSettingLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bFlecheG, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bFlecheD, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pSettingLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lSetting)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pSettingLayout.setVerticalGroup(
            pSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSettingLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lSetting)
                .addGap(47, 47, 47)
                .addComponent(bSauvegarde, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(bAccueil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSettingLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(pSettingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bFlecheD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bFlecheG, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pSettingLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64)
                .addComponent(bRetourSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(680, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pSetting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pSetting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPlateau, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 970, 1080);

        lNom.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 48)); // NOI18N
        lNom.setForeground(new java.awt.Color(242, 3, 255));
        lNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lNom);
        lNom.setBounds(930, 30, 400, 80);

        lChoix.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 27)); // NOI18N
        getContentPane().add(lChoix);
        lChoix.setBounds(980, 130, 350, 70);
        getContentPane().add(lBackground);
        lBackground.setBounds(0, 0, 2050, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSauvegardeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSauvegardeActionPerformed
        // Bouton permettant de sauegarder une partie
        quarto.sauvegarder();
        JOptionPane.showMessageDialog(null, "Votre partie a bien été sauvegardée", "Sauvegarde réussie", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_bSauvegardeActionPerformed

    private void bRetourSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRetourSettingActionPerformed
        // Bouton permettant de sortir des paramètres et de réafficher tous les composants de l'interface graphique
        pSetting.setVisible(false);
        jPlateau.setVisible(true);
        bSetting.setVisible(true);
        for(int i=0;i<JPions.size();i++){
            JPions.get(i).setEnabled(true);
        }
        affichePremsPhotos();
    }//GEN-LAST:event_bRetourSettingActionPerformed

    private void jSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSuivantActionPerformed
        // Bouton permettant d'afficher les 16 dernières pièces pour un plateau 5x5
        afficheDernieresPhotos();
        premsPioche=false;
    }//GEN-LAST:event_jSuivantActionPerformed

    private void bSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSettingActionPerformed
        // Bouton permettant d'accéder aux paramètres du jeu en activant et désactivant les composants de l'interface graphique souhaités
        jPlateau.setVisible(false);
        bSetting.setVisible(false);
        jSuivant.setVisible(false);
        jSuivant.setVisible(false);
        pSetting.setVisible(true);
        lVolume.setVisible(true);
        bFlecheD.setVisible(true);
        for(int i=0;i<JPions.size();i++){
            JPions.get(i).setEnabled(false);
        }
    }//GEN-LAST:event_bSettingActionPerformed

    private void jRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRetourActionPerformed
        // Bouton permettant d'accéder aux premières pièces de la pioche
        affichePremsPhotos();
        premsPioche=true;
    }//GEN-LAST:event_jRetourActionPerformed

    private void bAccueilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAccueilActionPerformed
        // Bouton permettant d'accéder à l'écran d'accueil 
        ((FAccueil) this.getParent()).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_bAccueilActionPerformed

    private void bFlecheGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFlecheGActionPerformed
        // Bouton permettant de baisser le volume
        float volume = ((FAccueil) this.getParent()).baisseVolume();
        if(volume<=-60.0){
            lVolume.setText("Volume OFF");
        }
    }//GEN-LAST:event_bFlecheGActionPerformed

    private void bFlecheDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFlecheDActionPerformed
        // // Bouton permettant d'augmenter le volume
        float volume = ((FAccueil) this.getParent()).augmenteVolume();
        if(volume>-60.0){
            lVolume.setText("Volume ON");
            
        }
    }//GEN-LAST:event_bFlecheDActionPerformed

    //----------------------------------------------------------------------------------------------------------------------
    public void afficheDernieresPhotos(){
        // Méthode permettant d'afficher les dernières pièces de la pioche pour un plateau 5x5
        if(JPions.size()>15){
            nbrPionAfficher=16;
        }else{
            nbrPionAfficher=0;
        }
        for(int i=0;i<JPions.size();i++){
            JPions.get(i).setVisible(true);
            JPions.get(i).setSize(120, 120);
        }
        for(int i=0;i<JPions.size();i++){   //Affiche toutes les pièces de la pioche sans prendre en compte celle qui ont déjà été poséés
            if(nbrPionAfficher>=quarto.getPions().size()){
                JPions.get(i).setVisible(false);
                this.nbrPionAfficher+=1;
                continue;
            }
            Image image = quarto.getPions().get(nbrPionAfficher).getImage();
            JPions.get(i).setSize(120, 120);
            quarto.afficher(image,JPions.get(i));
            this.nbrPionAfficher+=1;
        }   
        premsPioche=false;
        jSuivant.setVisible(false);
        jRetour.setVisible(true);
    }
   
    public void affichePremsPhotos(){
        // Méthode permettant d'afficher les premières pièces de la pioche
        this.nbrPionAfficher=0;
        for(int i=0;i<JPions.size();i++){
            JPions.get(i).setVisible(true);
            JPions.get(i).setSize(120, 120);
        }
        for(int i=0;i<JPions.size();i++){   //Affiche tous les pions qui n'ont pas encore été posés
            if(i<quarto.getPions().size()){
                Image image = quarto.getPions().get(nbrPionAfficher).getImage();
                quarto.afficher(image,JPions.get(i));
            }
            this.nbrPionAfficher+=1;
        }
        premsPioche=true;
        if(quarto.getPions().size()>=16){   //Active ou non les boutons selon le nombre de pions restants
            jRetour.setVisible(false);
            jSuivant.setVisible(true);
        }else{
            jRetour.setVisible(false);
            jSuivant.setVisible(false);
        }
    }
    
    public void refreshPioche(){
        //Permet de mettre a jour la pioche en la réaffichant
        this.nbrPionAfficher=0;
        for(int i=0;i<JPions.size();i++){
            if(i<quarto.getPions().size()){
                Image image = quarto.getPions().get(nbrPionAfficher).getImage();
                quarto.afficher(image,JPions.get(i));
            }
            this.nbrPionAfficher+=1;
        }
    }
    
    public int[] convertPlateau(int nbr){
        //Méthode convertissant un indice d'un tableau à une dimension en des coordonnées de tableau à deux dimensions (méthode inventée)
        int coords[]= new int[2];
        int taille = quarto.getPlateau().getNbrCases();
        int cptX=0;
        for(int i=0;i<taille;i++){
            if(nbr<taille){
                coords[0]=cptX;
                coords[1]=nbr;
                break;
            }
            nbr = nbr-taille;
            cptX+=1;
        }
        return coords;
    }
    
    public int convertJPlateau(int x, int y){
        //Méthode convertissant des coordonnées d'un tableau à deux dimensions en un indice d'un tableau à une dimension (méthode inventée)
        int taille = quarto.getPlateau().getNbrCases();
        int cpt=x*taille+y;
        
        return cpt;
    }
    
    public void setupPlateau(){
        // Méthode permettant de set le plateau en cas de chargement d'une partie en affichant les pions déjà posés
        for(int i=0;i<nbrCases;i++){    // Parcours les x du tableau
            for(int j=0;j<nbrCases;j++){    // Parcours les y du tableau
                if(!quarto.getPlateau().get(i,j).getJContenant().equals("[]")){  // Condition qui vérifie que la case n'est pas vide
                    int cptCase = convertJPlateau(i,j);
                    Toolkit t = Toolkit.getDefaultToolkit();
                    Image img = t.getImage(quarto.getPlateau().get(i, j).getJContenant());
                    quarto.afficher(img,JCases.get(cptCase));
                }
            }
        }
    }
    
    public void pourPioche(int cpt){
        //Méthode permettant de gérer la pioche lorsqu'on appuie sur un pièce
        if(choixPion==null){    //Verification que aucun pion n'a été sélectionné
            for(int i = 0; i < JPions.size(); i++){
                if(i!=cpt){ //On cache toutes les pièces sauf celle sélectionné
                    JPions.get(i).setVisible(false);
                }
             } 
            jSuivant.setVisible(false);
            if(!premsPioche){   //Si le pion esst dans la deuxième partie de la pioche alors il se situe à l'indice (cpt+15) du tableau JPions, sinon à l'indice cpt
                choixPion=cpt+15;
            }else{
                choixPion=cpt;
            }

            if(quarto.getJoueurs().get(0).getNom().equals("Ordi")){ //Si c'est un ordi le j1, alors il doit poser
                pourPlateau(choixPion);
            }
            
            //Modification du nom et de l'action dans l'interface graphique que les joueurs doivent effectuer
            Toolkit t = Toolkit.getDefaultToolkit();
            Image img5 = t.getImage(DOSS_IMAGES+"Placer.png");
            quarto.afficher(img5,lChoix);
            lNom.setText(quarto.getJoueurs().get(0).getNom());
            

         }else if(choixPion!=null && !choixOrdi && choixOrdi){   //Vérification qu'un pion à déjà été sélectionné et que ce n'est pas l'ordi qui l'a sélectionné
             if(!premsPioche){  //Si le pion appartient à la première pioche on la réaffiche sinon on réaffiche la deuxième pioche
                 afficheDernieresPhotos();
             }else{
                 affichePremsPhotos();
                 if(quarto.getPions().size()>=16){
                     jSuivant.setVisible(true);
                 }
             }
             choixPion=null;
             
            Toolkit t = Toolkit.getDefaultToolkit();
            Image img5 = t.getImage(DOSS_IMAGES+"Choix.png");
            quarto.afficher(img5,lChoix);
            lNom.setText(quarto.getJoueurs().get(1).getNom());
         }
    }
    
    public int[] pourPlateauOrdi(){
        //Méthode qui permet à l'ordinateur de placer un Pion sur le plateau interface graphique
        if(choixPion!=null){
            Image i = quarto.getPions().get(choixPion).getImage();
            int[] coords = quarto.placerPionOrdi2(quarto.getPions().get(choixPion), niveau);    //Place le pion dans le plateau console
            int cptCase = convertJPlateau(coords[0],coords[1]); //Conversion des coordonnées
            quarto.afficher(i,JCases.get(cptCase)); //Affiche le pion dans l'interface graphique
            if(quarto.getPions().size()<16){
                JPions.get(JPions.size()-1).setVisible(false);
                JPions.remove(JPions.get(JPions.size()-1));
            }else if(quarto.getPions().size()==16){
                jSuivant.setVisible(false);
            }
            affichePremsPhotos();
            return coords;
        }
        return null;
    }
    
    public void pourPiocheOrdi(){
        //Permet à l'ordinateur de choisir une pièce
        if(choixPion==null){
            int indice = quarto.choixPionOrdi(niveau);  //Récupération de l'indice ou se situe le pion choisi
            if(indice>15){  //Si l'indice est supérieur à 15 alors le pion se situe dans la deuxième partie de la pioche
                afficheDernieresPhotos();
                nbrPionAfficher=16;
                for(int i=0;i<16;i++){
                    if(nbrPionAfficher!=indice){    //Cache tous les pions sauf celui chosi par l'ordi
                        JPions.get(i).setVisible(false);
                    }
                    nbrPionAfficher+=1;
                }
                choixPion=indice;
                choixOrdi=true;
                jSuivant.setVisible(false);
                jRetour.setVisible(false);
            }else{  //Si l'indice est inférieure strict à 16 alors le pion se situe dans la première partie de la pioche
                affichePremsPhotos();
                nbrPionAfficher=0;
                for(int i=0;i<16;i++){
                    if(nbrPionAfficher!=indice && i<JPions.size()){ //Cache tous les pions sauf celui chosi par l'ordi
                        JPions.get(i).setVisible(false);
                    }
                    nbrPionAfficher+=1;
                }
                choixPion=indice;
                choixOrdi=true;
                jSuivant.setVisible(false);
                jRetour.setVisible(false);
            }
                
        }
    }
    
    public void pourPlateau(int cpt){
        //Méthode regroupant le positionnement d'une pièce sur le plateau graphique pour un joueur et l'ordinateur
        if(choixPion!=null){
            final int cpt2 = choixPion; //Obligation d'utiliser une variable final car la méthode est appelé dans un ActionListener
                if(!quarto.getJoueurs().get(0).getNom().equals("Ordi")){    //pourPlateau pour un Joueur
                    if(JCases.get(cpt).getIcon()==null){
                        Image i = quarto.getPions().get(cpt2).getImage();
                        int coords[]=convertPlateau(cpt);   //Convertis l'indice de la case du plateau du tableau JCases en coordonées
                        quarto.placerPion(quarto.getPions().get(cpt2), coords[0], coords[1]);   //Place le pion sur le plateau console
                        quarto.afficher(i,JCases.get(cpt)); //Affiche le pion sur le plateau graphique
                        if(quarto.getPions().size()<16){    //Affiche ou nom le bouton suivant et supprime les boutons de la pioche s'il ne reste plus que 16 pions
                            JPions.get(JPions.size()-1).setVisible(false);
                            JPions.remove(JPions.get(JPions.size()-1));
                        }else if(quarto.getPions().size()<=16){
                            jSuivant.setVisible(false);
                        }
                        affichePremsPhotos();
                        boolean gagne = quarto.verifGagnant3(quarto.getPlateau().get(coords[0], coords[1]));
                        if(gagne){  //Vérifie que le joueur a gagné et assigne à la variable gagnant de la fiche mère le nom du vainqueur, puis on affiche la fiche gagnant
                            ((FAccueil) this.getParent()).setGagnant(quarto.getJoueurs().get(0).getNom());
                            VerifGagnant();
                        }else{  //Vérifie que le joueur n'a pas gagné
                            choixPion=null;
                            choixOrdi=false;
                            switchJoueurs();    //Change l'ordre des joueurs
                            Toolkit t = Toolkit.getDefaultToolkit();
                            Image img5 = t.getImage(DOSS_IMAGES+"Choix.png");
                            quarto.afficher(img5,lChoix);
                            lNom.setText(quarto.getJoueurs().get(1).getNom());
                        }
                            

                        
                    }
                }else{  //pourPlateau pour un ordi
                    int[] coords = pourPlateauOrdi();   //Place le pion sur le plateau graphique
                    boolean gagne = quarto.verifGagnant3(quarto.getPlateau().get(coords[0], coords[1]));
                    if(gagne){  //Vérifie que le gagnant est l'ordinateur
                        ((FAccueil) this.getParent()).setGagnant("Ordi");
                        VerifGagnant();
                    }else{
                        switchJoueurs();    //Change l'ordre des joueurs
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {    //Ajout d'un timer pour simuler la reflexion de l'ordinateur
                            @Override
                            public void run() {
                                choixPion=null;
                                pourPiocheOrdi();   //Ordinateur choisi la pièce que le joueur devra jouer

                            }
                        }, 100);

                        Toolkit t = Toolkit.getDefaultToolkit();
                        Image img5 = t.getImage(DOSS_IMAGES+"Placer.png");
                        quarto.afficher(img5,lChoix);
                        lNom.setText(quarto.getJoueurs().get(0).getNom()); 
                    } 
                }
        }
    }
    
    public void VerifGagnant(){
        //Méthode permettant d'afficher la fiche du vainqueur
        FVainqueur f = ((FAccueil) this.getParent()).getFichVainqueur();
        f.setVisible(true);
        this.setVisible(false);
    }
    
    public void switchJoueurs(){
        //Méthode permettant de changer l'ordre des joueurs
        Joueur j = quarto.getJoueurs().get(0);
        quarto.getJoueurs().set(0,quarto.getJoueurs().get(1));
        quarto.getJoueurs().set(1,j);
    }
    
    
//----------------------------------------------------------------------------------------------------------------------
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FPlateau2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FPlateau2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FPlateau2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FPlateau2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FPlateau2 dialog = new FPlateau2(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAccueil;
    private javax.swing.JButton bFlecheD;
    private javax.swing.JButton bFlecheG;
    private javax.swing.JButton bRetourSetting;
    private javax.swing.JButton bSauvegarde;
    private javax.swing.JButton bSetting;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPlateau;
    private javax.swing.JButton jRetour;
    private javax.swing.JButton jSuivant;
    private javax.swing.JLabel lBackground;
    private javax.swing.JLabel lChoix;
    private javax.swing.JLabel lNom;
    private javax.swing.JLabel lSetting;
    private javax.swing.JLabel lVolume;
    private javax.swing.JPanel pPioche;
    private javax.swing.JPanel pSetting;
    // End of variables declaration//GEN-END:variables
}
