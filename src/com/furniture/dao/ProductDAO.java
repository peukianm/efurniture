package com.furniture.dao;

import com.furniture.entities.Catalogue;
import com.furniture.entities.Category;
import com.furniture.entities.Company;
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Product;
import com.furniture.entities.Productcategory;
import com.furniture.entities.Productline;
import com.furniture.util.EJBUtil;
import com.furniture.util.PersistenceHelper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.enterprise.inject.spi.Producer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * A data access object (DAO) providing persistence and search support for Product entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually added to each of these methods for data to be persisted to the JPA datastore.
 *
 * @see com.furniture.entities.Product
 * @author MyEclipse Persistence Tools
 */
public class ProductDAO {
    // property constants

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    private static final Logger logger = Logger.getLogger(ProductDAO.class);

    private EntityManager getEntityManager() {
        PersistenceHelper persistenceHelper = EJBUtil.lookupPersistenceHelperBean();
        return persistenceHelper.getEntityManager();
    }

    /**
     * Perform an initial save of a previously unsaved Product entity. All subsequent persist actions of this entity should use the #update() method. This
     * operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e.,
     * database. This method uses the null null null null null null null null     {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ProductDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Product entity to persist
     * @throws RuntimeException when the operation fails
     */
    public void save(Product entity) {
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException re) {
            logger.error("Error on saving entity", re);
            throw re;
        }
    }

    /**
     * Delete a persistent Product entity. This operation must be performed within the a database transaction context for the entity's data to be permanently
     * deleted from the persistence store, i.e., database. This method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * ProductDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity Product entity to delete
     * @throws RuntimeException when the operation fails
     */
    public void delete(Product entity) {
        try {
            entity = getEntityManager().getReference(Product.class, entity.getProductid());
            getEntityManager().remove(entity);
        } catch (RuntimeException re) {
            logger.error("Error on deleting entity", re);
            throw re;
        }
    }

    /**
     * Persist a previously saved Product entity and return it or a copy of it to the sender. A copy of the Product entity parameter is returned when the JPA
     * persistence mechanism has not previously been tracking the updated entity. This operation must be performed within the a database transaction context for
     * the entity's data to be permanently saved to the persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = ProductDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity Product entity to update
     * @return Product the persisted Product entity instance, may not be the same
     * @throws RuntimeException if the operation fails
     */
    public Product update(Product entity) {
        try {
            Product result = getEntityManager().merge(entity);

            return result;
        } catch (RuntimeException re) {
            logger.error("Error on updating entity", re);
            throw re;
        }
    }

    public Product findById(BigDecimal id) {
        try {
            Product instance = getEntityManager().find(Product.class, id);
            return instance;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    /**
     * Find all Product entities with a specific property value.
     *
     * @param propertyName the name of the Product property to query
     * @param value the property value to match
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum number of results to return.
     * @return List<Product> found by query
     */
    @SuppressWarnings("unchecked")
    public List<Product> findByProperty(String propertyName, final Object value, final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Product model where model." + propertyName + "= :propertyValue";
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

    public List<Product> findByName(Object name, int... rowStartIdxAndCount) {
        return findByProperty(NAME, name, rowStartIdxAndCount);
    }

    public List<Product> findByDescription(Object description, int... rowStartIdxAndCount) {
        return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
    }

    /**
     * Find all Product entities.
     *
     * @param rowStartIdxAndCount Optional int varargs. rowStartIdxAndCount[0] specifies the the row index in the query result-set to begin collecting the
     * results. rowStartIdxAndCount[1] specifies the the maximum count of results to return.
     * @return List<Product> all Product entities
     */
    @SuppressWarnings("unchecked")
    public List<Product> findAll(final int... rowStartIdxAndCount) {
        try {
            final String queryString = "select model from Product model order by model.name";
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

    public List<Product> searchProducts(String name, Company company, Productline productline, Catalogue catalogue, Productcategory productcategory) {
        try {
            final String queryString = "select DISTINCT p "
                    + " from Product p "
                    + (company != null ? " JOIN p.companyproducts cp " : " ")
                    + (productline != null ? " JOIN p.productlineproducts ppl " : " ")
                    + (catalogue != null ? "  JOIN p.productlineproducts ppl JOIN ppl.productline.catalogueproductlines cpl" : " ")
                    + " where p.active= 1 "
                    + (company != null ? " and cp.company = :company " : " ")
                    + (productline != null ? " and ppl.productline = :productline " : " ")
                    + (productcategory != null ? " and p.productcategory = :productcategory " : " ")
                    + (catalogue != null ? " and cpl.catalogue= :catalogue" : " ")
                    + (name != null ? " AND (LOWER(d.name) like '%" + name.toLowerCase() + "%'" + " OR UPPER(d.name)  like '%" + name.toUpperCase() + "%') " : " ")
                    + " order by p.name ";

            Query query = getEntityManager().createQuery(queryString);

            if (company != null) {
                query.setParameter("company", company);
            }
            if (productline != null) {
                query.setParameter("productline", productline);
            }
            if (productcategory != null) {
                query.setParameter("productcategory", productcategory);
            }
            if (catalogue != null) {
                query.setParameter("catalogue", catalogue);
            }


            List<Product> products = query.getResultList();
            return products;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    public List<Product> fetchProductAutoCompleteName(String name, Company company) {
        try {
            name = name.trim();

            String queryString = "Select c from Product c  where (LOWER(c.name) like '" + ((String) name).toLowerCase() + "%'"
                    + " OR UPPER(c.name)  like '" + ((String) name).toUpperCase() + "%') "
                    + " and c.active = 1 "
                    + " order by c.name";

            queryString = "Select DISTINCT cp.product  from Companyproduct cp where   "
                    + " cp.active = 1  "
                    + (company != null ? " and cp.company = :company " : " ")
                    + " and (LOWER(cp.product.name) like '" + ((String) name).toLowerCase() + "%'"
                    + " OR UPPER(cp.product.name)  like '" + ((String) name).toUpperCase() + "%') "
                    + " order by cp.product.name";



            Query query = getEntityManager().createQuery(queryString);
            if (company != null) {
                query.setParameter("company", company);
            }

            query.setMaxResults(20);
            return query.getResultList();
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    public Boolean checkProductInCompany(Company company, Product product) {
        try {

            String queryString = "Select cp.product from Companyproduct cp "
                    + " where cp.active = 1 "
                    + " and cp.company = :company "
                    + " and cp.company.active = 1 "
                    + " and cp.product = :product "
                    + " and cp.product.active = 1 ";

            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("company", company);
            query.setParameter("product", product);

            List<Product> products = query.getResultList();
            if (products.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    public String getCategoryPath(Category category, String path) {
        try {
            String retPath = null;
            if (path != null) {
                retPath = category.getName() + "/" + path;
            } else {
                retPath = category.getName();
            }

            String queryString = "Select model.category from Category model "
                    + " where model.categoryid = :id ";
            //+ " and model.category!=null ";

            Query query = getEntityManager().createQuery(queryString);
            query.setParameter("id", category.getCategoryid());
            List<Category> categories = query.getResultList();
            if (categories.size() > 0 && categories.get(0) != null) {
                Category cat = categories.get(0);
                return getCategoryPath(cat, retPath);
            } else {
                return retPath;
            }


        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    public List<Product> getCategoryProduct(List<Category> categories, List<Company> companies, String productName) {

        Set<Product> products = new HashSet<Product>(0);
        try {
            if (companies != null && companies.size() > 0 && (categories == null || categories.size() == 0)) {
                for (int i = 0; i < companies.size(); i++) {
                    Company company = companies.get(i);
                    List<Companyproduct> companyProducts = new ArrayList(company.getCompanyproducts());
                    for (int j = 0; j < companyProducts.size(); j++) {
                        Companyproduct companyproduct = companyProducts.get(j);                        
                        if (companyproduct.getProduct().getActive().equals(BigDecimal.ONE)) {
                            products.add(companyproduct.getProduct());
                        }
                    }
                }
            } else if ((companies == null || companies.size() == 0) && categories != null && categories.size() > 0) {
                for (int i = 0; i < categories.size(); i++) {
                    Category category = categories.get(i);
                    for (Iterator<Product> it = getCategoryProduct(category, null).iterator(); it.hasNext();) {
                        Product product = it.next();
                        if (product.getActive().equals(BigDecimal.ONE)) {
                            products.add(product);
                        }
                    }
//                    for (int j = 0; j < category.getProducts().size(); j++) {
//                        Product product = category.getProducts().get(j);
//                        if (product.getActive().equals(BigDecimal.ONE)) {
//                            products.add(product);
//                        }
//                    }
                }
            } else if (companies != null && companies.size() > 0 && categories != null && categories.size() > 0) {
                for (int i = 0; i < companies.size(); i++) {
                    Company company = companies.get(i);
                    for (int j = 0; j < categories.size(); j++) {
                        Category category = categories.get(j);
                        for (Iterator<Product> it = getCategoryProduct(category, company).iterator(); it.hasNext();) {
                            Product product = it.next();
                            if (product.getActive().equals(BigDecimal.ONE)) {
                                products.add(product);
                            }
                        }
                    }
                }
            }


            List<Product> productList = new ArrayList<Product>(0);
            for (Iterator<Product> it = products.iterator(); it.hasNext();) {
                Product product = it.next();
                if (productName != null && !product.getName().toLowerCase().contains(productName.toLowerCase())) {
                    // DO NOTHING !!!!!!!!!
                } else {
                    productList.add(product);
                }
            }

            Collections.sort(productList, new Comparator<Product>() {
                public int compare(Product one, Product other) {
                    return one.getName().compareTo(other.getName());
                }
            });
            return productList;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }

    public Set<Product> getCategoryProduct(Category category, Company company) {
        Set<Product> products = new HashSet<Product>(0);
        try {
            //products.addAll(category.getProducts());            
            for (int i = 0; i < category.getProducts().size(); i++) {
                Product product = category.getProducts().get(i);

                if (company != null) {
                    List<Company> companies = product.getOrderedCompanies();
                    for (int j = 0; j < companies.size(); j++) {
                        Company company1 = companies.get(j);
                        if (company1.equals(company)) {
                            products.add(product);
                        }
                    }
                } else {
                    products.add(product);
                }
            }


            if (category.getCategories() != null && category.getCategories().size() > 0) {
                for (int i = 0; i < category.getCategories().size(); i++) {
                    Category cat = category.getCategories().get(i);
                    products.addAll(getCategoryProduct(cat, company));
                }
            }
            return products;
        } catch (RuntimeException re) {
            logger.error("Error on finding entity", re);
            throw re;
        }
    }
}