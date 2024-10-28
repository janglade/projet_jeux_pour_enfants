package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

    private final JPanel drawZone;
    private final JPanel toolPanel;
    private final JButton delete;
    private final JLabel colorLabel;
    private Color color;
    private final String[] colors = {"Noir", "Jaune", "Rouge", "Bleu", "Vert", "Gris"};
    private final JComboBox<String> colorBox;
    private int x, y = 0;

    public DrawingPanel() {

        drawZone = new JPanel();
        toolPanel = new JPanel();
        delete = new JButton("Effacer");
        colorLabel = new JLabel();
        colorBox = new JComboBox<>(colors);
        color = Color.BLACK;
        initGui();
        initEvents();
    }

    /**
     * Permet la création de JPanel Dessin.
     */
    private void initGui() {

        // Configuration du layout de DrawPanel
        this.setLayout(new BorderLayout());

        // COuleur de fond de la drawZone
        drawZone.setBackground(Color.WHITE);

        // Ajustement de la barre d'outils
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.LINE_AXIS));
        colorLabel.setText("Choisis ta couleur :");

        // Ajout du bouton effacer et du choix des couleurs dans la bare d'outils
        toolPanel.add(Box.createHorizontalStrut(20));//Box Horizontale qui crée des espaces
        toolPanel.add(delete);
        toolPanel.add(Box.createHorizontalStrut(200));
        toolPanel.add(colorLabel);
        toolPanel.add(Box.createHorizontalStrut(20));
        toolPanel.add(colorBox);
        toolPanel.add(Box.createHorizontalStrut(20));

        this.add(drawZone, BorderLayout.CENTER);
        this.add(toolPanel, BorderLayout.SOUTH);
    }
    //Utilisation d'un switch pour récuperer les couleur en fonction de la String colors
    private Color getColor(String choix) {
        switch (choix) {
            case "Jaune":
                color = Color.YELLOW;
                break;
            case "Rouge":
                color = Color.RED;
                break;
            case "Bleu":
                color = Color.BLUE;
                break;
            case "Vert":
                color = Color.GREEN;
                break;
            case "Gris":
                color = Color.GRAY;
                break;
            default:
                color = Color.BLACK;
                break;
        }
        return color;
    }
    /**
     * Création des fonctions associés au JPanel "drawZone"
     */
    private void initEvents() {

        //Ecouteur qui permet de dessiner avec la souris
        drawZone.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
            /**
             * Permet de tracer une ligne avcec le mouvement de la souris à
             * partir du moment ou on clique jusqu'à que l'on relâche
             *
             * @param e
             */

            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = drawZone.getGraphics();
                g.setColor(color);
                g.drawLine(x, y, e.getX(), e.getY());
                x = e.getX();
                y = e.getY();
            }
        });

        delete.addActionListener((ae) -> {
            eraseDrawPanel();
        });

        // Un ecouteur sur le colorBox qui permet de récuperer une couleur sur le getColor
        //color est de baseBlack
        colorBox.addActionListener(e -> {
            String selectedColor = (String) colorBox.getSelectedItem();
            color = getColor(selectedColor);
        });
    }

    /**
     * Utulisation d'une methode fillRect qui supprime tout le rectangle ( la
     * drawZone ) en le mettant à 0.
     */
    private void eraseDrawPanel() {
        Graphics g = drawZone.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());  // Effacer tout le drawPanel en u
    }
}
