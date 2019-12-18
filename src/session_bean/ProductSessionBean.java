package session_bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}