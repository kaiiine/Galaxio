/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pfiches;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author quent
 */
public class FReglementation extends javax.swing.JDialog {

    private  String DOSS_IMAGES = "src/pimages/" ;
    /**
     * Creates new form FReglementation
     */
    public FReglementation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Initialisation des images des composants dans l'interface graphique 
        this.setLocation(-10,0);
        this.setSize(1600,830);
        lBackground.setSize(1540,790);
        Toolkit t = Toolkit.getDefaultToolkit();
        Image img = t.getImage(DOSS_IMAGES+"Regles.png");
        
        Image img2 = t.getImage(DOSS_IMAGES+"Retour.png");
        ((FAccueil)this.getParent()).afficher(img2,bRetour);
        
        ((FAccueil)this.getParent()).afficher(img,lBackground);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bRetour = new javax.swing.JButton();
        lBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        bRetour.setContentAreaFilled(false);
        bRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRetourActionPerformed(evt);
            }
        });
        getContentPane().add(bRetour);
        bRetour.setBounds(10, 720, 180, 60);
        getContentPane().add(lBackground);
        lBackground.setBounds(0, 0, 1590, 880);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRetourActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        ((FAccueil)this.getParent()).setVisible(true);
    }//GEN-LAST:event_bRetourActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FReglementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FReglementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FReglementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FReglementation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FReglementation dialog = new FReglementation(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bRetour;
    private javax.swing.JLabel lBackground;
    // End of variables declaration//GEN-END:variables
}
