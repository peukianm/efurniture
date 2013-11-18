package com.furniture.dao;

import com.furniture.entities.Company;
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Product;
import com.furniture.util.EJBUtil;
import com.furniture.util.PersistenceHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * A data access object (DAO) providing persistence and search support for Companyproduct entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 *
 * @see com.furniture.entities.Companyproduct
 * @author MyEclipse Persistence Tools
 */
public class CompanyproductDAO {
    // property constants

    private static final Logger logger = Logger.getLogger(CompanyproductDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Companyproduct entity. All subsequent persist actions of this entity should use the #update() method.
     * This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e.,
     * database. This method uses the null null     {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * CompanyproductDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Companyproduct entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Companyproduct entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Companyproduct entity. This operation must be performed within the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This method uses the null null     {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * CompanyproductDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Companyproduct entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Companyproduct entity) {
        try {
            entity = getEntityManager().getReference(Companyproduct.class, entity.getId());
            getEntityManager().remove(entity);

        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Companyproduct entity and return it or a copy of it to the sender. A copy of the Companyproduct entity parameter is returned
     * when the JPA persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = CompanyproductDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Companyproduct entity to update
     * @return Companyproduct the persisted Companyproduct entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Companyproduct update(Companyproduct entity) {
        try {
            Companyproduct result = getEntityManager().merge(entity);
            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Companyproduct findById(BigDecimal id) {
        try {
            Companyproduct instance = getEntityManager().find(Companyproduct.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Companyproduct entities with a specific property value.
     *
     * @param propertyName the name of the Companyproduct property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Companyproduct> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Companyproduct> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Companyproduct model where model." + propertyName + "= :propertyValue";
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
     * Find all Companyproduct entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Companyproduct> all Companyproduct entities
     */
    @SuppressWarnings("unchecked")
    public List<Companyproduct> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Companyproduct model";
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

    public List<Product> getCompanyProducts(Company company, Boolean showOnlyActive) {
        try {
            Query query = getEntityManager().createQuery("Select cp.product from Companyproduct cp where "
                    + " cp.company = :company "
                    + (showOnlyActive == true ? " and cp.active=1 " : " "));
            query.setParameter("company", company);
            return query.getResultList();
        } catch (RuntimeException re) {
            logger.error("Error fetching products from company ", re);
            throw re;
        }

    }
    
     public List<Product> getCompanyProducts(List<Company> companies, Boolean showOnlyActive) {
        try {            
            Set<Product> retValues = new HashSet<Product>(0);
            Query query = getEntityManager().createQuery("Select DISTINCT(cp.product) from Companyproduct cp where "
                    + " cp.company = :company "
                    + (showOnlyActive == true ? " and cp.active=1 " : " "));
            
            for (int i = 0; i < companies.size(); i++) {
                Company company = companies.get(i);
                query.setParameter("company", company);
                retValues.addAll(new HashSet<Product>(query.getResultList()));
            }
            
            
            return new ArrayList<Product>(retValues);
        } catch (RuntimeException re) {
            logger.error("Error fetching products from companyproduct ", re);
            throw re;
        }

    }
    
}