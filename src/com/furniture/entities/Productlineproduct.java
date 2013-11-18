package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Productlineproduct entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCTLINEPRODUCT", schema = "FURNITURE")
@Cacheable(false)
public class Productlineproduct implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Productline productline;
    private Product product;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Productlineproduct() {
    }

    /**
     * minimal constructor
     */
    public Productlineproduct(BigDecimal id, Productline productline, Product product) {
        this.id = id;
        this.productline = productline;
        this.product = product;
    }

    /**
     * full constructor
     */
    public Productlineproduct(BigDecimal id, Productline productline, Product product, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.id = id;
        this.productline = productline;
        this.product = product;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTLINEID", nullable = false)
    public Productline getProductline() {
        return this.productline;
    }

    public void setProductline(Productline productline) {
        this.productline = productline;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTID", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "ACTIVE", precision = 22, scale = 0)
    public BigDecimal getActive() {
        return this.active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
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

        if (!(obj instanceof Productlineproduct)) {
            return false;
        }

        Productlineproduct compare = (Productlineproduct) obj;

        return compare.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "ProductlienProduct{" + "id=" + id + ", product=" + getProduct().getName() + " line=" + getProductline().getName() + "}";
    }
}