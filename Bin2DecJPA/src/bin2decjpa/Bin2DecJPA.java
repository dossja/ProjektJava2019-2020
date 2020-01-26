/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bin2decjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Project to create the table for Web Project part 
 * 
 * @author Dominika Matyja
 * @version 1.0
 */
public class Bin2DecJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }
    
    /**
     * Default method for JPA
     * @param object 
     */
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bin2DecJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
