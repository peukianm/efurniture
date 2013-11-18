package com.furniture.dao;

import com.furniture.entities.Specification;
import com.furniture.util.EJBUtil;
import com.furniture.util.PersistenceHelper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * A data access object (DAO) providing persistence and search support for Specification entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see com.furniture.entities.Specification
 * @author MyEclipse Persistence Tools
 */
public class SpecificationDAO {
    // property constants

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    private static final Logger logger = Logger.getLogger(SpecificationDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Specification entity. All subsequent persist actions of this entity should use the #update() method. This
     * operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e.,
     * database. This method uses the      {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SpecificationDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Specification entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Specification entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Specification entity. This operation must be performed within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This method uses the      {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SpecificationDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Specification entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Specification entity) {
        try {
            entity = getEntityManager().getReference(Specification.class, entity.getSpecificationid());
            getEntityManager().remove(entity);
        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Specification entity and return it or a copy of it to the sender. A copy of the Specification entity parameter is returned
     * when the JPA persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = SpecificationDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Specification entity to update
     * @return Specification the persisted Specification entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Specification update(Specification entity) {
        try {
            Specification result = getEntityManager().merge(entity);
            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Specification findById(BigDecimal id) {
        try {
            Specification instance = getEntityManager().find(Specification.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Specification entities with a specific property value.
     *
     * @param propertyName the name of the Specification property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Specification> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Specification> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Specification model where model." + propertyName + "= :propertyValue";
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

    public List<Specification> findByName(Object name, int... rowStartIdxAndCount) {
        return findByProperty(NAME, name, rowStartIdxAndCount);
    }

    public List<Specification> findByDescription(Object description, int... rowStartIdxAndCount) {
        return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
    }

    /**
     * Find all Specification entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Specification> all Specification entities
     */
    @SuppressWarnings("unchecked")
    public List<Specification> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Specification model";
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