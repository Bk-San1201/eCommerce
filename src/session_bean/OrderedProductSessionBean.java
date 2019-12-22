package session_bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Category;
import entity.OrderedProduct;

/**
 *
 * @author ThanDieu
 */
@Stateless
public class OrderedProductSessionBean extends AbstractSessionBean<OrderedProduct> {
	@PersistenceContext(unitName = "eMarketPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OrderedProductSessionBean() {
		super(OrderedProduct.class);
	}

	public List<OrderedProduct> findByOrderId(Object id) {
        return em.createNamedQuery("OrderedProduct.findByOrderId").setParameter("orderId",
                id).getResultList();
    }
	public List<OrderedProduct> findAll() {
    	Query q = getEntityManager().createNamedQuery("OrderProduct.findAll");
		return q.getResultList();
    }
}