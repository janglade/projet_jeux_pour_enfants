/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author laurie gasc
 */
public class HomePanel extends JPanel {

    //private final Image backgroundImage;
    private final ImageIcon imageIcon;

    public HomePanel() {
        // Charger l'image de fond
       // backgroundImage = imageIcon.getImage();

        imageIcon = new ImageIcon("logo.jpeg");
        setupGui();

    }
    /**
     * Cette méthode permet de " dessiner" une image en arriere plan
     *
     * @param g
     */
    /*    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }*/

    private void setupGui() {
        setLayout(new BorderLayout());
        JLabel image = new JLabel(imageIcon);
        this.add(image);
    }
}
