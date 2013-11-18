package com.furniture.dao;

import com.furniture.entities.Productspecification;
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
 * A data access object (DAO) providing persistence and search support for Productspecification entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the
 * JPA datastore.
 *
 * @see com.furniture.entities.Productspecification
 * @author MyEclipse Persistence Tools
 */
public class ProductspecificationDAO {
    // property constants

    private static final Logger logger = Logger.getLogger(ProductspecificationDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Productspecification entity. All subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
     * store, i.e., database. This method uses the      {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ProductspecificationDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Productspecification entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Productspecification entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Productspecification entity. This operation must be performed within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This method uses the      {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ProductspecificationDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Productspecification entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Productspecification entity) {
        try {
            entity = getEntityManager().getReference(Productspecification.class, entity.getId());
            getEntityManager().remove(entity);
        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Productspecification entity and return it or a copy of it to the sender. A copy of the Productspecification entity parameter
     * is returned when the JPA persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a
     * database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object)
     * EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ProductspecificationDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Productspecification entity to update
     * @return Productspecification the persisted Productspecification entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Productspecification update(Productspecification entity) {
        try {
            Productspecification result = getEntityManager().merge(entity);
            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Productspecification findById(BigDecimal id) {
        try {
            Productspecification instance = getEntityManager().find(Productspecification.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Productspecification entities with a specific property value.
     *
     * @param propertyName the name of the Productspecification property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Productspecification> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Productspecification> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Productspecification model where model." + propertyName + "= :propertyValue";
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
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Productspecification entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Productspecification> all Productspecification entities
     */
    @SuppressWarnings("unchecked")
    public List<Productspecification> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Productspecification model";
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
            logger.error("Error on finding entity", re);
            throw re;
        }
    }
}