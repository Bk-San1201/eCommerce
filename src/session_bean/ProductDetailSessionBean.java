package session_bean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.ProductDetail;

@Stateless
public class ProductDetailSessionBean extends AbstractSessionBean<ProductDetail> {
	@PersistenceContext(unitName = "eMarketPU")
	public EntityManager em;
	@EJB
	private ProductSessionBean productSB;
	public EntityManager getEntityManager() {
		return em;
	}
	public List<ProductDetail> findAll() {
    	Query q = getEntityManager().createNamedQuery("ProductDetail.findAll");
		return q.getResultList();
    }

	public ProductDetailSessionBean() {
		super(ProductDetail.class);
	}
	public List<ProductDetail> findTop5Sale() {
		List<ProductDetail> products = this.findAll();
		Collections.sort(products, new SaleComparator());
		return products;
	}
	public void create(ProductDetail productDetail) {
		super.create(productDetail);
//		productSB.getEntityManager().refresh(productSB.find(productDetail.getProductId()));
	}
}

class SaleComparator implements Comparator<ProductDetail> {
	public int compare(ProductDetail p1, ProductDetail p2) {
		if (((entity.ProductDetail) p1).getSale() == ((entity.ProductDetail) p2).getSale()) {
			return 0;
		} else if (((entity.ProductDetail) p1).getSale() < ((entity.ProductDetail) p2).getSale()) {
			return 1;
		} else {
			return -1;
		}
	}
}