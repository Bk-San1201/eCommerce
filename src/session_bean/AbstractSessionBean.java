package session_bean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author ThanDieu
 */
public abstract class AbstractSessionBean<T> {
	private Class<T> entityClass;

	public AbstractSessionBean(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public void create(T entity) {
//		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entity);
//		getEntityManager().getTransaction().commit();
	}

	public void edit(T entity) {
		getEntityManager().merge(entity);
//		getEntityManager().clear();
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
//		getEntityManager().clear();
//		getEntityManager().flush();
		
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public abstract List<T> findAll();

	public List<T> findRange(int[] range) {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}