package session_bean;

import java.util.ArrayList;
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
import entity.ProductDetail;

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
	@EJB 
	private ProductDetailSessionBean productDetailSB;
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
	public List<Product> findAll() {
    	Query q = getEntityManager().createNamedQuery("Product.findAll");
		return q.getResultList();
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
	public List<Product> findTop5Sale() {
		List<ProductDetail> productDetails = productDetailSB.findTop5Sale();
		List<Product> products = new ArrayList<Product>();
		int i = 0;
		for (ProductDetail pd: productDetails) {
			products.add(this.find(pd.getProductId()));
			i += 1;
			if (i == 5) {
				break;
			}
		}
		return products;
	}
	
	public List<Product> findProductWaitingDelivery() {
		Query q = em.createNativeQuery("select product.* from customer_order, product, ordered_product where (product.product_id = ordered_product.product_id) and (customer_order.order_id = ordered_product.order_id);", Product.class);		
		return q.getResultList();
	}
	
}
