/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.random;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Julien Anglade
 */
public class CalculPanel extends JPanel {

    private final JPanel calculArea, operation, buttons;
    private final JLabel operLab, var1Lab, var2Lab, equal;
    private final JTextField answer;
    private final JButton verifyButton, solutionButton, nextButton;
    private final PopUp popUp;
    private static final String[] operators = {"+", "-"}; //static ?
    private String operator;
    private int var1, var2, result, resultInput;

    //constructeur
    public CalculPanel() {
        //Instanciations
        calculArea = new JPanel();
        operation = new JPanel();
        operLab = new JLabel();
        var1Lab = new JLabel();
        var2Lab = new JLabel();
        equal = new JLabel("=");
        answer = new JTextField();
        buttons = new JPanel();
        verifyButton = new JButton("Vérifier");
        solutionButton = new JButton("Solution");
        nextButton = new JButton("Suivant");
        popUp = new PopUp();
        //lancement d'un calcul aléatoire dès le chargement de la page:
        randomCalcul();
        //Initiation du contenu graphique
        initGui();
        //Initialisation des évenements
        initEvents();

    }
    //Getter et Setter

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public int getVar2() {
        return var2;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    // Affichage de la fenêtre 
    private void initGui() {
        //Visuel du Panel calculArea
        calculArea.setLayout(new FlowLayout(FlowLayout.CENTER)); // Utiliser FlowLayout pour aligner les boutons
        calculArea.setBackground(new Color(173, 216, 230));
        // Visuel du Panel opération 
        operation.setLayout(new FlowLayout(FlowLayout.CENTER));
        //operation.setPreferredSize(new Dimension(250, 100));
        //operation.setMinimumSize(??)
        operation.setBackground(Color.WHITE);
        operation.setBorder(BorderFactory.createTitledBorder("Le calcul à résoudre"));
        // Appliquer une police en gras et redimensionnée
        Font font = new Font("SansSerif", Font.BOLD, 24); // Police SansSerif, style Gras, taille 24
        //Visuels des labels
        operLab.setPreferredSize(new Dimension(100, 100));
        operLab.setFont(font);
        var1Lab.setPreferredSize(new Dimension(100, 100));
        var1Lab.setFont(font);
        var2Lab.setPreferredSize(new Dimension(100, 100));
        var2Lab.setFont(font);
        equal.setPreferredSize(new Dimension(100, 100));
        equal.setFont(font);
        //Visuel du champs de réponse
        answer.setPreferredSize(new Dimension(200, 100));
        answer.setBackground(Color.WHITE);
        equal.setFont(font);
        answer.setBorder(BorderFactory.createTitledBorder("Ta réponse !"));
        answer.setFont(font);
        //Visuel des bouttons
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER)); // Utiliser FlowLayout pour aligner les boutons

        //Ajout des élements
        this.add(calculArea);
        calculArea.add(operation);
        operation.add(var1Lab);
        operation.add(operLab);
        operation.add(var2Lab);
        calculArea.add(equal);
        calculArea.add(answer);
        this.add(buttons);
        buttons.add(verifyButton);
        buttons.add(solutionButton);
        buttons.add(nextButton);
        this.setVisible(true); //Affichage de la fenêtre
        answer.requestFocus(); //focus d'entrée pour answer   //autre choix meilleur ?
    }

    //Gestion des évenements
    public void initEvents() {
        //pour le boutton Vérifier
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        //pour le boutton Solution
        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult();
            }
        });
        //pour le boutton Suivant
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCalcul();
            }
        });
    }

    //Méthodes
    //
    //Génération d'un calcul random
    private void randomCalcul() {
        //Génération aléatoire de l'opérateur entre + et -
        //en utilisant String[] operators = {"+", "-"};
        operator = operators[new Random().nextInt(2)];
        //faire apparaître l'opérateur random dans le Label opérator (operlab)
        operLab.setText(operator);
        //Génération aléatoire entre 0 et 9
        var1 = new Random().nextInt(10);
        var2 = new Random().nextInt(10);
        //Génération aléatoire de var2 entre 0 et var1 (cas où résultat est négatif)
        if (operator.equals("-") && (var2 > var1)) {
            var2 = new Random().nextInt(var1 + 1);
        }
        //faire apparaître les var random dans leurs labels
        var1Lab.setText(String.valueOf(var1)); //convertir en String
        var2Lab.setText(String.valueOf(var2)); //convertir en String
    }
    //Calculer le résultat

    public int resultCalcul() {
        if (operator.equals("+")) {
            result = var1 + var2;
        } else {
            result = var1 - var2;
        }
        return result;
    }

//Vérifier la réponse
    public void checkAnswer() {
        //convertir le texte du champs de réponse en int pour comparaison avec result
        resultInput = Integer.valueOf(answer.getText());
        result = resultCalcul();
        if (resultInput == result) {
            popUp.showPopUpRight(); //réponse = résultat => réponse juste (popup)
        } else {
            popUp.showPopUpWrong(); //réponse != résultat => réponse fausse (popup)
        }
    }

    //Afficher la solution
    public void showResult() {
        result = resultCalcul();
        //afficher la réponse en popup
        popUp.showPopUpSolution(result);
        //montrer la réponse dans le champs de saisie
        answer.setText("" + result);
    }

    //Passer au calcul suivant
    public void nextCalcul() {
        //supprimer le champs de saisie et lancer un nouveau calcul
        answer.setText("");
        randomCalcul();
        answer.requestFocus();
    }

}
