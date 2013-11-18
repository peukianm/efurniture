package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Productcategory entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCTCATEGORY", schema = "FURNITURE")
public class Productcategory implements java.io.Serializable {

    // Fields
    private BigDecimal productcategoryid;
    private String name;
    private BigDecimal active;
    private String description;
    private BigDecimal ordered;
    private Set<Product> products = new HashSet<Product>(0);
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Productcategory() {
    }

    /**
     * minimal constructor
     */
    public Productcategory(BigDecimal productcategoryid, String name) {
        this.productcategoryid = productcategoryid;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Productcategory(BigDecimal productcategoryid, String name, BigDecimal active, String description, BigDecimal ordered, Set<Product> products,
              Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.productcategoryid = productcategoryid;
        this.name = name;
        this.active = active;
        this.description = description;
        this.ordered = ordered;
        this.products = products;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "PRODUCTCATEGORYID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getProductcategoryid() {
        return this.productcategoryid;
    }

    public void setProductcategoryid(BigDecimal productcategoryid) {
        this.productcategoryid = productcategoryid;
    }

    @Column(name = "NAME", nullable = false, length = 60)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ACTIVE", precision = 22, scale = 0)
    public BigDecimal getActive() {
        return this.active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }

    @Column(name = "DESCRIPTION", length = 300)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ORDERED", precision = 22, scale = 0)
    public BigDecimal getOrdered() {
        return this.ordered;
    }

    public void setOrdered(BigDecimal ordered) {
        this.ordered = ordered;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productcategory")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Column(name = "CREATED_TIMESTAMP", length = 11, insertable = false, updatable = true)
    public Timestamp getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Column(name = "MODIFIED_TIMESTAMP", length = 11, insertable = false, updatable = true)
    public Timestamp getModifiedTimestamp() {
        return this.modifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Productcategory)) {
            return false;
        }

        Productcategory compare = (Productcategory) obj;

        return compare.productcategoryid.equals(this.productcategoryid);
    }

    @Override
    public int hashCode() {
        return productcategoryid != null ? this.getClass().hashCode() + productcategoryid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "id=" + productcategoryid + ", name=" + getName() + "}";
    }
}