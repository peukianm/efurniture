package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;

/**
 * Companyproduct entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMPANYPRODUCT", schema = "FURNITURE")
public class Companyproduct implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Company company;
    private Product product;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Companyproduct() {
    }

    /**
     * minimal constructor
     */
    public Companyproduct(BigDecimal id, Company company, Product product) {
        this.id = id;
        this.company = company;
        this.product = product;
    }

    /**
     * full constructor
     */
    public Companyproduct(BigDecimal id, Company company, Product product, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.id = id;
        this.company = company;
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
    @JoinColumn(name = "COMPANYID", nullable = false)
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

        if (!(obj instanceof Companyproduct)) {
            return false;
        }

        Companyproduct compare = (Companyproduct) obj;
        
        if (compare.id!=null)
            return compare.id.equals(this.id);
        else
            return compare.company.equals(this.company);
    }

    @Override
    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "CompanyProduct{id=" + id + ", company=" + getCompany() + ", product=" + getProduct() + "}";
    }
}