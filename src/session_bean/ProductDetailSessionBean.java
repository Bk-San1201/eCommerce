package session_bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.ProductDetail;

@Stateless
public class ProductDetailSessionBean extends AbstractSessionBean<ProductDetail> {
	@PersistenceContext(unitName = "eMarketPU")
	public EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public ProductDetailSessionBean() {
		super(ProductDetail.class);
	}
}

