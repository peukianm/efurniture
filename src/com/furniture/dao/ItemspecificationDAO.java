package com.furniture.dao;

import com.furniture.dao.ProductDAO;
import com.furniture.entities.Item;
import com.furniture.entities.Itemspecification;
import com.furniture.entities.Specification;
import com.furniture.util.EJBUtil;
import com.furniture.util.PersistenceHelper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * A data access object (DAO) providing persistence and search support for Itemspecification entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see com.furniture.entities.Itemspecification
 * @author MyEclipse Persistence Tools
 */
public class ItemspecificationDAO {
    // property constants

    private static final Logger logger = Logger.getLogger(ProductDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Itemspecification entity. All subsequent persist actions of this entity should use the #update() method.
     * This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e.,
     * database. This method uses the      {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ItemspecificationDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Itemspecification entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Itemspecification entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Itemspecification entity. This operation must be performed within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This method uses the      {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ItemspecificationDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Itemspecification entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Itemspecification entity) {
        try {
            entity = getEntityManager().getReference(Itemspecification.class, entity.getId());
            getEntityManager().remove(entity);
        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Itemspecification entity and return it or a copy of it to the sender. A copy of the Itemspecification entity parameter is
     * returned when the JPA persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object)
     * EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ItemspecificationDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Itemspecification entity to update
     * @return Itemspecification the persisted Itemspecification entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Itemspecification update(Itemspecification entity) {
        try {
            Itemspecification result = getEntityManager().merge(entity);
            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Itemspecification findById(BigDecimal id) {
        try {
            Itemspecification instance = getEntityManager().find(Itemspecification.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on find entity", re);
            throw re;
        }
    }

    /**
     * Find all Itemspecification entities with a specific property value.
     *
     * @param propertyName the name of the Itemspecification property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Itemspecification> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Itemspecification> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Itemspecification model where model." + propertyName + "= :propertyValue";
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
            logger.error("Error on find entity", re);

            throw re;
        }
    }

    /**
     * Find all Itemspecification entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Itemspecification> all Itemspecification entities
     */
    @SuppressWarnings("unchecked")
    public List<Itemspecification> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Itemspecification model";
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
    
    
    @SuppressWarnings("unchecked")    
    public List<Specification> fetchItemSpecifications(Item item, Boolean showOnlyEnabled) {
        try {
            Query query = getEntityManager().createQuery("Select model.specification from Itemspecification model where "
                    + " model.item = :item "
                    + (showOnlyEnabled ? " and model.active = 1 " : " "));
              
            query.setParameter("item", item);
            return query.getResultList();
        } catch (RuntimeException re){
            logger.error("Error on finding entity", re);
            throw re;
        }
     }
    
    
     @SuppressWarnings("unchecked")    
    public List<Specification> fetchItemDimensionSpecifications(Item item, Boolean showOnlyEnabled) {
        try {
            Query query = getEntityManager().createQuery("Select model.specification from Itemspecification model where "
                    + " model.item = :item "
                    + " and model.specification.dimension = 1  "
                    + (showOnlyEnabled ? " and model.active = 1 " : " "));
              
            query.setParameter("item", item);
            return query.getResultList();
        } catch (RuntimeException re){
            logger.error("Error on finding entity", re);
            throw re;
        }
     }
    
    
    
}