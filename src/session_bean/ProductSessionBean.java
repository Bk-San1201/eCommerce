package session_bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Category;
import entity.Product;

/**
 *
 * @author ThanDieu
 */
@Stateless
public class ProductSessionBean extends AbstractSessionBean<Product> {
	@PersistenceContext(unitName = "eMarketPU")
	private EntityManager em;
	@EJB
	private CategorySessionBean categorySB;
	public EntityManager getEntityManager() {
		return em;
	}

	public ProductSessionBean() {
		super(Product.class);
	}

	@Override
	public void remove(Product p) {
		p = em.merge(p);
		Category c = p.getCategory();
//		Category c = p.getCategory();
//		c.removeProduct(p);
		super.remove(p);
		categorySB.getEntityManager().refresh(c);
		
	}
	@Override 
	public void create(Product p) {
		super.create(p);
		Category c = categorySB.find(p.getCategory().getCategoryId());
//		c.addProduct(p);
		categorySB.getEntityManager().refresh(c);
	}
	public Set<Product> findByKeyword(String keyword) {
		Set<Product> products = new HashSet<Product>();
		
		Query q = em.createNativeQuery("SELECT * FROM Product p WHERE p.name like '%" + keyword + "%'", Product.class);
		@SuppressWarnings("unchecked")
		List<Product> list = q.getResultList();
		for (Product p : list) {
			products.add(p);
		}
		
		q = em.createNativeQuery("SELECT * FROM Product p WHERE p.description like '%" + keyword + "%'", Product.class);
		
		list = q.getResultList();
		for (Product p : list) {
			products.add(p);
		}
		
		q = em.createNativeQuery("SELECT * FROM Product p WHERE p.description_detail like '%" + keyword + "%'", Product.class);

		list = q.getResultList();
		for (Product p : list) {
			products.add(p);
		}
		
		
		return products;
	}
}