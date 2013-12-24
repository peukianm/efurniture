package com.furniture.dao;

import com.furniture.entities.Catalogue;
import com.furniture.entities.Company;
import com.furniture.entities.Productline;
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
 * A data access object (DAO) providing persistence and search support for Catalogue entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see com.furniture.entities.Catalogue
 * @author MyEclipse Persistence Tools
 */
public class CatalogueDAO {
    // property constants

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    private static final Logger logger = Logger.getLogger(CatalogueDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Catalogue entity. All subsequent persist actions of this entity should use the #update() method. This
     * operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e.,
     * database. This method uses the      {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * CatalogueDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Catalogue entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Catalogue entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Catalogue entity. This operation must be performed within the a database transaction context for the entity's data to be permanently
     * deleted from the persistence store, i.e., database. This method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * CatalogueDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Catalogue entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Catalogue entity) {
        try {
            entity = getEntityManager().getReference(Catalogue.class, entity.getCatalogueid());
            getEntityManager().remove(entity);
        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Catalogue entity and return it or a copy of it to the sender. A copy of the Catalogue entity parameter is returned when the
     * JPA persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a database transaction context
     * for the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = CatalogueDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Catalogue entity to update
     * @return Catalogue the persisted Catalogue entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Catalogue update(Catalogue entity) {
        try {
            Catalogue result = getEntityManager().merge(entity);
            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Catalogue findById(BigDecimal id) {
        try {
            Catalogue instance = getEntityManager().find(Catalogue.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Catalogue entities with a specific property value.
     *
     * @param propertyName the name of the Catalogue property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Catalogue> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Catalogue> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Catalogue model where model." + propertyName + "= :propertyValue";
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

    public List<Catalogue> findByName(Object name, int... rowStartIdxAndCount) {
        return findByProperty(NAME, name, rowStartIdxAndCount);
    }

    public List<Catalogue> findByDescription(Object description, int... rowStartIdxAndCount) {
        return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
    }

    /**
     * Find all Catalogue entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Catalogue> all Catalogue entities
     */
    @SuppressWarnings("unchecked")
    public List<Catalogue> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Catalogue model";
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
    
    public List<Catalogue> getCompanyCatalogues(Company company) {
        try {            
            String queryString = "Select DISTINCT model from Catalogue model JOIN model.companies comp  "
                   // + " join model.productlines pl  "                    
                    + " where model.active = 1 "
                    //+ " and pl.active = 1 "
                    + (company!=null? " and comp = :company " :" ")
                    + " order by model.name";
            
            
            Query query = getEntityManager().createQuery(queryString);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            if (company != null) {
                query.setParameter("company", company);
            }
            
            
            return query.getResultList();
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }
    
     public List<Productline> getCatalogueProductlines(Catalogue catalogue) {
        try {            
            String queryString = "Select pl from Catalogue model "   
                    + " JOIN  model.productlines pl   "    
                    + " where model.active = 1 "                    
                    + (catalogue!=null? " and model = :catalogue " :" ")
                    + " order by pl.name";
            
            
            Query query = getEntityManager().createQuery(queryString);
            if (catalogue != null) {
                query.setParameter("catalogue", catalogue);
            }
            return query.getResultList();
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }
    
    
}