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
 * Catalogueproductline entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CATALOGUEPRODUCTLINE", schema = "FURNITURE")
public class Catalogueproductline implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Productline productline;
    private Catalogue catalogue;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Catalogueproductline() {
    }

    /**
     * minimal constructor
     */
    public Catalogueproductline(BigDecimal id, Productline productline, Catalogue catalogue) {
        this.id = id;
        this.productline = productline;
        this.catalogue = catalogue;
    }

    /**
     * full constructor
     */
    public Catalogueproductline(BigDecimal id, Productline productline, Catalogue catalogue, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.id = id;
        this.productline = productline;
        this.catalogue = catalogue;
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
    @JoinColumn(name = "CATALOGUEID", nullable = false)
    public Catalogue getCatalogue() {
        return this.catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
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

        if (!(obj instanceof Catalogueproductline)) {
            return false;
        }

        Catalogueproductline compare = (Catalogueproductline) obj;
        return compare.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "CatalogueLone{id=" + id + ", catalogue=" + getCatalogue() + ", Line=" + getProductline() + "}";
    }
}