/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Questions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import sql.MariaDbConnection;

/**
 *
 * @author laurie gasc
 */
public class QuestionsDao implements Dao<Questions>{
    private Connection conn = MariaDbConnection.getInstance();

    private Questions createBean(ResultSet rs) {
        Questions obj = new Questions();
        try {
            obj.setId(rs.getInt("id"));
            obj.setQuestion(rs.getString("question"));
            obj.setAnswer(rs.getString("answer"));
        } catch (SQLException ex) {
            throw new RuntimeException("Impossible de parcourir le resultset");
        }
        return obj;
    }
    
    @Override
    public Questions find(int id) {
        Questions ret = null;
        String sql = "SELECT * FROM questions WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ret = createBean(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Sélection impossible dans la table questions. Erreur :\n" + ex.getMessage());
        }
        return ret;
    }

    @Override
    public void create(Questions obj) {
        String sql = "INSERT INTO questions (question, answer) VALUES (?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, obj.getQuestion());
            pstmt.setString(2, obj.getAnswer());
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                obj.setId(keys.getInt(1));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Insertion impossible dans la table questions. Erreur :\n" + ex.getMessage());
        }
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM questions WHERE id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Suppression impossible dans la table questions. Erreur :\n" + ex.getMessage());
        }
    }

    @Override
    public void update(Questions obj) {
        String sql = "UPDATE questions SET question=?, answer=? WHERE id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, obj.getQuestion());
            pstmt.setString(2, obj.getAnswer());
            pstmt.setInt(3, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Update impossible dans la table questions. Erreur :\n" + ex.getMessage());
        }
    }
    
    //RECUPÈRES LES DONNÉES D'UNE UNITÉ QUIZALE PAR SON IDENTIFIANT
    public Questions getQuestionsById(int id){
        Questions quizUnit = new Questions();
        String sql = "SELECT * FROM questions WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                quizUnit.setId(rs.getInt("id"));
                quizUnit.setQuestion(rs.getString("question"));
                quizUnit.setAnswer(rs.getString("answer"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Sélection impossible dans la table questions. Erreur :\n" + ex.getMessage());
        }
        return quizUnit;
    }

    @Override
    public Collection<Questions> all() {
        Collection<Questions> list = new ArrayList<>();
        String sql = "SELECT * FROM questions";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(createBean(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Liste impossible dans la table questions. Erreur :\n" + ex.getMessage());
        }
        return list;
    }
}
