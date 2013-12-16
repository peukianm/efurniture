package com.furniture.entities;

import com.furniture.dao.ProductDAO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Category entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CATEGORY", schema = "FURNITURE")
public class Category implements java.io.Serializable {

    // Fields
    private BigDecimal categoryid;
    private Category category;
    private String name;
    private String description;
    private BigDecimal depth;
    private BigDecimal active;
    private BigDecimal ordered;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private List<Category> categories = new ArrayList<Category>(0);
    private List<Company> companies = new ArrayList<Company>(0);
    private List<Product> products = new ArrayList<Product>(0);
    private String categoryPath;
    
    // Constructors
    /**
     * default constructor
     */
    public Category() {
    }

    /**
     * minimal constructor
     */
    public Category(BigDecimal categoryid, String name, BigDecimal active) {
        this.categoryid = categoryid;
        this.name = name;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Category(BigDecimal categoryid, Category category, String name, String description, BigDecimal depth, BigDecimal active, BigDecimal ordered,
            Timestamp createdTimestamp, Timestamp modifiedTimestamp, List<Category> categories, List<Company> companies, List<Product> products) {
        this.categoryid = categoryid;
        this.category = category;
        this.name = name;
        this.description = description;
        this.depth = depth;
        this.active = active;
        this.ordered = ordered;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        this.categories = categories;
        this.products = products;
        this.companies = companies;
    }

    // Property accessors
    @Id
    @Column(name = "CATEGORYID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getCategoryid() {
        return this.categoryid;
    }

    public void setCategoryid(BigDecimal categoryid) {
        this.categoryid = categoryid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTCATEGORYID")
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "NAME", nullable = false, length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", length = 400)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "DEPTH", precision = 22, scale = 0)
    public BigDecimal getDepth() {
        return this.depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    @Column(name = "ACTIVE", nullable = false, precision = 22, scale = 0)
    public BigDecimal getActive() {
        return this.active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }

    @Column(name = "ORDERED", precision = 22, scale = 0)
    public BigDecimal getOrdered() {
        return this.ordered;
    }

    public void setOrdered(BigDecimal ordered) {
        this.ordered = ordered;
    }

    @Column(name = "CREATED_TIMESTAMP", length = 11)
    public Timestamp getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Column(name = "MODIFIED_TIMESTAMP", length = 11)
    public Timestamp getModifiedTimestamp() {
        return this.modifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    
    
    
    @Transient
    public String getCategoryPath() {
        try {
            ProductDAO dao = new ProductDAO();
            return dao.getCategoryPath(category, null);
        } catch (RuntimeException re) {            
            re.printStackTrace();
            throw re;
        }
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }
    

   
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CATPROD",
    joinColumns = {
        @JoinColumn(name = "CATEGORYID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "PRODUCTID")
    })
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the books to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
     /**
     * @return the companies
     */
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "COMPCAT",
    joinColumns = {
        @JoinColumn(name = "CATEGORYID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "COMPANYID")
    })
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * @param companies the books to set
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
    
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Category)) {
            return false;
        }

        Category compare = (Category) obj;

        return compare.categoryid.equals(this.categoryid);
    }

    @Override
    public int hashCode() {
        return categoryid != null ? this.getClass().hashCode() + categoryid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Category={" + "id=" + categoryid + ", name=" + getName() + "}";
    }
    
}