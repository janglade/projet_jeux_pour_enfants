package graphics;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author laurie gasc
 */
public class Game extends JFrame {

    private final DrawingPanel drawPanel;
    private final CalculPanel calcPanel;
    private final QuizzPanel quizzPanel;
    private final PopUp popup;
    private final HomePanel home;
    private final JTabbedPane content;

    private static final long serialVersionUID = 1L;

    public Game() {
        drawPanel = new DrawingPanel();
        calcPanel = new CalculPanel();
        quizzPanel = new QuizzPanel();
        popup = new PopUp();
        home = new HomePanel();
        content = new JTabbedPane();
        setupGui();

        // Configuration de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture
        this.setResizable(false); // Fenêtre non-redimensionnable
        setSize(700, 700); // Ajustement de la taille au contenu
        this.setLocationRelativeTo(null); // On positionne la fenêtre au milieu de l'écran
        this.setVisible(true);

    }

    private void setupGui() {
        content.addTab("Acceuil", home);
        content.addTab("Dessin", drawPanel);
        content.addTab("Calcul", calcPanel);
        content.addTab("Quiz", quizzPanel);
        setContentPane(content);

    }

}
