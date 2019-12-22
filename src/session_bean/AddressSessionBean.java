package session_bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Address;
import entity.Customer;
import entity.CustomerOrder;
@Stateless
public class AddressSessionBean extends AbstractSessionBean<Address> {
	public AddressSessionBean() {
		super(Address.class);
	}

	@PersistenceContext(unitName = "eMarketPU")
	private EntityManager em ;
	@EJB 
	private CustomerSessionBean customerSB;
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		Query q = getEntityManager().createNamedQuery("Address.findAll");
		return q.getResultList();
	}
	@Override
	public void remove(Address p) {
		p = em.merge(p);
		Customer c = p.getCustomer();
		super.remove(p);
//		customerSB.getEntityManager().refresh(c);
		
	}
	@Override 
	public void create(Address p) {
		super.create(p);
		Customer c = customerSB.find(p.getCustomer().getCustomerId());
//		customerSB.getEntityManager().refresh(c);
	}
	
	public List<Address> findByCustomer(Customer customer) {
		return (List<Address>) em.createNamedQuery("Address.findByCustomer").setParameter("customer", customer)
				.getResultList();
	}

}
