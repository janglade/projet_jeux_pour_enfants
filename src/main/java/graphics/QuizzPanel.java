/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import beans.Questions;
import dao.QuestionsDao;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Caroline Casteras
 */
public class QuizzPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private QuestionsDao qDao;
    private JPanel quizArea;
    private JPanel buttons;
    private final JLabel questionL;
    private final JTextField attempt;
    private final JButton verifyButton;
    private final JButton solutionButton;
    private final JButton nextButton;
    private final PopUp popUp;
    ArrayList<Integer> idList;

    public QuizzPanel() {
        quizArea = new JPanel();
        buttons = new JPanel();
        questionL = new JLabel();
        attempt = new JTextField(20);
        verifyButton = new JButton("Vérifier");
        solutionButton = new JButton("Solution");
        nextButton = new JButton("Autre question");
        popUp = new PopUp();

        initGui();
        QuizManagement();
    }

    private void initGui() {
        //ajout des éléments au panel
        this.add(quizArea);
        this.add(buttons);
        quizArea.add(questionL);
        quizArea.add(attempt);
        buttons.add(verifyButton);
        buttons.add(solutionButton);
        buttons.add(nextButton);
        quizArea.setLayout(new FlowLayout(FlowLayout.CENTER));
        quizArea.setBackground(new Color(173, 216, 230));
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));

    }

    private void QuizManagement() {
//        idList = new ArrayList<>();//id qui n'ont pas encore été utilisés
//        qDao = new QuestionsDao();
//        int idRandom;
//
//        createIdList();
//        //tant que la liste des id n'est pas vide on pose des questions
//        while (!idList.isEmpty()) {
//            //génère id aléatoire pour tirer une question
//            idRandom = createRandomId();
//            //gestion des actions/événements relatives au quiz
//            initEvents(idRandom);
//        }
//        //affichage de fin des questions
//        JOptionPane.showMessageDialog(this, "Bravo, tu as fini le quiz !", "Quiz", JOptionPane.INFORMATION_MESSAGE);
    }

//REINITIALISE LE POOL DE QUESTION
    private void createIdList() {
        //remplit la liste des id qui permettront de récupérer une Q/R aléatoire
        for (int i = 1; i < 8; i++) {
            idList.add(i);
        }
    }

    //MELANGE LES QUESTIONS
    private int createRandomId() {
        int id;
        //mélange la liste des id restants
        Collections.shuffle(idList);
        //récupère le premier id dans la liste mélangée qui donnera la question
        id = idList.get(0);
        return id;
    }

    private void initEvents(int idRandom) {
        //récupère les questions et réponses par id dans la base de données
        Questions quizUnit = qDao.getQuestionsById(idRandom);
        String question = quizUnit.getQuestion();
        String answer = quizUnit.getAnswer();
        //affiche la question
        questionL.setText("Question : " + question);
        //action bouton vérifier
        verifyButton.addActionListener((ActionEvent e) -> {
            verifyAnswer(answer);
        });
        //action bouton solution
        solutionButton.addActionListener((ActionEvent e) -> {
            showAnswer(answer);
        });
        //action bouton autre question
        nextButton.addActionListener((ActionEvent e) -> {
            newQuestion(idRandom);
        });
    }

    //VERIFIE LA REPONSE EN FAISANT ABSTRACTION DE LA CASSE ET DES ACCENTS  
    private void verifyAnswer(String answer) {
        String userAnswer = attempt.getText();
        userAnswer = removeDiacritics(userAnswer);
        answer = removeDiacritics(answer);
        //equalsIgnoreCase permet d'ignorer la casse
        if (userAnswer.equalsIgnoreCase(answer)) {
            //si la réponse est juste
            popUp.showPopUpRight();
        } else {
            //si la réponse est fausse
            popUp.showPopUpWrong();
        }
    }

    //FAIT APPARAITRE LA REPONSE DANS LE CHAMP DE TEXTE OU ON ECRIT LA REPONSE
    private void showAnswer(String answer) {
        //affichage réponse en pop-up
        popUp.showPopUpSolution(answer);
        //affichage réponse en vert dans le champ de texte de la réponse
        attempt.setText(answer);
        attempt.setForeground(Color.green);
    }

    //RETIRE LA QUESTION ET DE PASSER A LA PROCHAINE
    private void newQuestion(int idRandom) {
        //retire l'identifiant de la liste
        idList.remove(idRandom);
        //vide le champ de texte de la réponse pour la prochaine question
        attempt.setText("");
        //mets le focus sur le champ de texte de la réponse
        attempt.requestFocus();
    }

    //ENLEVE LES DIACRITIQUES (ACCENTS)
    private String removeDiacritics(String word) {
        //isole les caractères avec des diacritiques et change en forme NFD
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        //les caractères isolés sont ensuite nettoyés (XD)
        word = word.replaceAll("\\p{M}", "");
        return word;
    }
}
