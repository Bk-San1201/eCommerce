package session_bean;

import entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ThanDieu
 */
@Stateless
public class CustomerSessionBean extends AbstractSessionBean<Customer> {
	@PersistenceContext(unitName = "eMarketPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public CustomerSessionBean() {
		super(Customer.class);
	}
}
