package com.furniture.dao;

import com.furniture.dao.ProductlineDAO;
import com.furniture.entities.Productcategory;
import com.furniture.util.EJBUtil;
import com.furniture.util.PersistenceHelper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * A data access object (DAO) providing persistence and search support for
 * Productcategory entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.furniture.entities.Productcategory
 * @author MyEclipse Persistence Tools
 */

public class ProductcategoryDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
        private static final Logger logger = Logger.getLogger(ProductcategoryDAO.class);

	private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

	/**
	 * Perform an initial save of a previously unsaved Productcategory entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProductcategoryDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Productcategory entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Productcategory entity) {		
		try {
			getEntityManager().persist(entity);			
		} catch (RuntimeException re) {
			logger.error("Error on saving entity", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Productcategory entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProductcategoryDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Productcategory entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Productcategory entity) {
		try {
			entity = getEntityManager().getReference(Productcategory.class, entity.getProductcategoryid());
			getEntityManager().remove(entity);			
		} catch (RuntimeException re) {			
		logger.error("Error on saving deleting ", re);	
                    throw re;
		}
	}

	/**
	 * Persist a previously saved Productcategory entity and return it or a copy
	 * of it to the sender. A copy of the Productcategory entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ProductcategoryDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Productcategory entity to update
	 * @return Productcategory the persisted Productcategory entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Productcategory update(Productcategory entity) {
		try {
			Productcategory result = getEntityManager().merge(entity);			
			return result;
		} catch (RuntimeException re) {			
		logger.error("Error on updating entity", re);	
                    throw re;
		}
	}

	public Productcategory findById(BigDecimal id) {		
		try {
			Productcategory instance = getEntityManager().find(Productcategory.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("Error on findby", re);
			throw re;
		}
	}

	/**
	 * Find all Productcategory entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Productcategory property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Productcategory> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Productcategory> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {		
		try {
			final String queryString = "select model from Productcategory model where model." + propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			logger.error("Error on findProperty ", re);
			throw re;
		}
	}

	public List<Productcategory> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<Productcategory> findByDescription(Object description, int... rowStartIdxAndCount) {
		return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
	}

	/**
	 * Find all Productcategory entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Productcategory> all Productcategory entities
	 */
	@SuppressWarnings("unchecked")
	public List<Productcategory> findAll(final int... rowStartIdxAndCount) {		
		try {
			final String queryString = "select model from Productcategory model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			logger.error("Error on find all ", re);
			throw re;
		}
	}

}