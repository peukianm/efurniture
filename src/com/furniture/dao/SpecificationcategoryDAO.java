package com.furniture.dao;

import com.furniture.entities.Specificationcategory;
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
 * A data access object (DAO) providing persistence and search support for Specificationcategory entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the
 * JPA datastore.
 *
 * @see com.furniture.entities.Specificationcategory
 * @author MyEclipse Persistence Tools
 */
public class SpecificationcategoryDAO {
    // property constants

    public static final String NAME = "name";
    private static final Logger logger = Logger.getLogger(SpecificationcategoryDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Specificationcategory entity. All subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
     * store, i.e., database. This method uses the      {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SpecificationcategoryDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Specificationcategory entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Specificationcategory entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Specificationcategory entity. This operation must be performed within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This method uses the      {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * SpecificationcategoryDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Specificationcategory entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Specificationcategory entity) {
        try {
            entity = getEntityManager().getReference(Specificationcategory.class, entity.getSpecificationcategoryid());
            getEntityManager().remove(entity);
        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Specificationcategory entity and return it or a copy of it to the sender. A copy of the Specificationcategory entity parameter
     * is returned when the JPA persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a
     * database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object)
     * EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = SpecificationcategoryDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Specificationcategory entity to update
     * @return Specificationcategory the persisted Specificationcategory entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Specificationcategory update(Specificationcategory entity) {
        try {
            Specificationcategory result = getEntityManager().merge(entity);
            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Specificationcategory findById(BigDecimal id) {
        try {
            Specificationcategory instance = getEntityManager().find(Specificationcategory.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Specificationcategory entities with a specific property value.
     *
     * @param propertyName the name of the Specificationcategory property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Specificationcategory> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Specificationcategory> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Specificationcategory model where model." + propertyName + "= :propertyValue";
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

    public List<Specificationcategory> findByName(Object name, int... rowStartIdxAndCount) {
        return findByProperty(NAME, name, rowStartIdxAndCount);
    }

    /**
     * Find all Specificationcategory entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Specificationcategory> all Specificationcategory entities
     */
    @SuppressWarnings("unchecked")
    public List<Specificationcategory> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Specificationcategory model";
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