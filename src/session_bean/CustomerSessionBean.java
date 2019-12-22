package session_bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Category;
import entity.Customer;

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
	public Customer findByUsername(String username) {
		return (Customer) em.createNamedQuery("Customer.findByUsername").setParameter("username",
                username).getSingleResult();
	}
	public List<Customer> findAll() {
    	Query q = getEntityManager().createNamedQuery("Customer.findAll");
		return q.getResultList();
    }
}
