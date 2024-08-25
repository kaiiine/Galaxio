/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ptraitement;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Photo {
    private String nomFichier;
    private Image img;
    public static final String DOSS_IMAGES = "src/pimages/" ;
    
    public Photo( String valFichier){
        this.nomFichier=valFichier;
        Toolkit t = Toolkit.getDefaultToolkit();
        img = t.getImage(DOSS_IMAGES + nomFichier);
    }
    
    public void afficher (JButton monLabel){
        if (img !=null){
        //mise à l'échelle de l'image
        img = img.getScaledInstance(monLabel.getWidth(), monLabel.getHeight(),
        Image.SCALE_DEFAULT);
        //création d'une icône et assignation au label
        monLabel.setIcon(new ImageIcon(img));
        }
    }
    
}
