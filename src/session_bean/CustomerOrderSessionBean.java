package session_bean;

import entity.CustomerOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ThanDieu
 */
@Stateless
public class CustomerOrderSessionBean extends AbstractSessionBean<CustomerOrder> {
	@PersistenceContext(unitName = "eMarketPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CustomerOrderSessionBean() {
		super(CustomerOrder.class);
	}

	public CustomerOrder find(Object id) {
		CustomerOrder order = em.find(CustomerOrder.class, id);
		em.refresh(order);
		return order;
	}

	public CustomerOrder findByCustomer(Object customer) {
		return (CustomerOrder) em.createNamedQuery("CustomerOrder.findByCustomer").setParameter("customer", customer)
				.getSingleResult();
	}
}