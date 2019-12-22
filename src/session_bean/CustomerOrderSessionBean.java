package session_bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Category;
import entity.Customer;
import entity.CustomerOrder;

/**
 *
 * @author ThanDieu
 */
@Stateless
public class CustomerOrderSessionBean extends AbstractSessionBean<CustomerOrder> {
	@PersistenceContext(unitName = "eMarketPU")
	private EntityManager em;
	@EJB
	private CustomerSessionBean customerSB;

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

	public List<CustomerOrder> findByCustomer(Object customer) {
		return (List<CustomerOrder>) em.createNamedQuery("CustomerOrder.findByUsername").setParameter("customer", customer)
				.getResultList();
	}
	
	@Override
	public void create(CustomerOrder customerOrder) {
		super.create(customerOrder);
//		Customer c = customerSB.find(customerOrder.getCustomer().getCustomerId());
//		customerSB.getEntityManager().refresh(c);
	}
	public List<CustomerOrder> findAll() {
    	Query q = getEntityManager().createNamedQuery("CustomerOrder.findAll");
		return q.getResultList();
    }
}