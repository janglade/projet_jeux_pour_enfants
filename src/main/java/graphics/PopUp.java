/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Julien Anglade
 */
public class PopUp {

    private final ImageIcon iconRight = new ImageIcon("assets/petitheros_bonne_reponse_redim.jpeg");
    private final ImageIcon iconWrong = new ImageIcon("assets/petitheros_mauvaise_reponse_redim.jpeg");
    private final ImageIcon iconSolu = new ImageIcon("assets/petitheros_solution_redim.jpeg");

    public void showPopUpRight() {
        JLabel right = new JLabel("Bravo ! Bien joué !");
        right.setForeground(new Color(0, 255, 0));
        right.setFont(new Font("SansSerif", Font.BOLD, 24)); // Police SansSerif, style Gras, taille 24
        JOptionPane.showMessageDialog(null, right, "Bonne Réponse !", JOptionPane.INFORMATION_MESSAGE, iconRight);
    }

    public void showPopUpWrong() {
        JLabel wrong = new JLabel("Réfléchis encore et essaie de nouveau !");
        wrong.setForeground(new Color(255, 0, 0));
        wrong.setFont(new Font("SansSerif", Font.BOLD, 24)); // Police SansSerif, style Gras, taille 24
        JOptionPane.showMessageDialog(null, wrong, "Mausaise Réponse !", JOptionPane.INFORMATION_MESSAGE, iconWrong);
    }

    public void showPopUpSolution(int solu) {
        JLabel solution = new JLabel("La solution est " + solu);
        solution.setFont(new Font("SansSerif", Font.BOLD, 24));
        JOptionPane.showMessageDialog(null, solution, "Voila la solution !", JOptionPane.INFORMATION_MESSAGE, iconSolu);
    }
    
    public void showPopUpSolution(String solu) {
        JLabel solution = new JLabel("La solution est " + solu);
        solution.setFont(new Font("SansSerif", Font.BOLD, 24));
        JOptionPane.showMessageDialog(null, solution, "Voila la solution !", JOptionPane.INFORMATION_MESSAGE, iconSolu);
    }
}
