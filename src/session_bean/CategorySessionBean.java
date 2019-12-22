/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Category;
import entity.Product;
import entity.ProductDetail;

/**
 *
 * @author Zayt
 */
@Stateless
public class CategorySessionBean extends AbstractSessionBean<Category>{
    @PersistenceContext(unitName = "eMarketPU")
    private EntityManager em ;
    @EJB
    private ProductDetailSessionBean productDetailSB;

    public EntityManager getEntityManager() {
        return em;
    }

    public CategorySessionBean() {
        super(Category.class);
    }
    public List<Category> findAll() {
    	Query q = getEntityManager().createNamedQuery("Category.findAll");
		return q.getResultList();
    }
    public int calQuantity(Category category) {
    	int res = 0;
    	for (Product p : category.getProducts()) {
    		int productId = p.getProductId();
    		ProductDetail productDetail = productDetailSB.find(productId);
    		res += productDetail.getQuantity();
    	}
    	return res;
    }
}
