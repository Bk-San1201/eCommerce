/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import entity.Category;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zayt
 */
@Stateless
public class CategorySessionBean extends AbstractSessionBean<Category>{
    @PersistenceContext(unitName = "eMarketPU")
//    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("eMarketPU");
    private EntityManager em ;

    public EntityManager getEntityManager() {
        return em;
    }

    public CategorySessionBean() {
        super(Category.class);
    }
}
