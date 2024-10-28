/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.Collection;

/**
 *
 * @author Caroline Casteras
 */
interface Dao<T> {
    
    /**
     * Retourne l'objet lu en DB grâce à son identifiant ou null.
     * @param id L'identifiant en DB de l'objet
     * @return L'objet ou null
     */
    T find(int id);
    
    /**
     * Hydrate la DB avec l'objet fourni. Cet objet doit avoir un id null.
     * @param obj 
     */
    void create(T obj);
    
    /**
     * Supprime la ligne d'identifiant id dans la DB.
     * @param id 
     */
    void delete(int id);
    
    /**
     * Met à jour l'objet dans la DB. L'objet doit avoir un id non null.
     * @param obj 
     */
    void update(T obj);
    
    /**
     * Retourne toutes les lignes de la DB dans une collection.
     * @return Collection contenant tous les objets hydratés par la DB.
     */
    Collection<T> all();
}
