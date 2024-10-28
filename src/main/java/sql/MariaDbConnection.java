/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caroline Casteras
 */
public class MariaDbConnection {
    private static Connection instance;

    private MariaDbConnection() {
    }

    public static Connection getInstance() throws RuntimeException {
        if (instance == null) {
            //Création de la connexion
            try { //Charger le driver
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("Driver introuvable");
            }
            String server = "wp.ldnr.fr";
            String port = "3306";
            String dbname = "cda202401_jse_g1";
            String user = "cda202401_jse_g1";
            String pwd = "OMAMnSMNte6JXO2x";
            String url = "jdbc:mariadb://" + server + ":" + port
                + "/" + dbname;
            try {
                instance = DriverManager.getConnection(url, user, pwd);
            } catch (SQLException ex) {
                throw new RuntimeException("Connexion à la base de donnée impossible avec le message : "
                    + ex.getMessage());
            }
        }
        return instance;
    }

    public static void closeConnection() {
        if (instance != null) {
            try {
                instance.close();
            } catch (SQLException ex) {
                Logger.getLogger(MariaDbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
