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
 * Companycatalogue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMPANYCATALOGUE", schema = "FURNITURE")
public class Companycatalogue implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Company company;
    private Catalogue catalogue;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Companycatalogue() {
    }

    /**
     * minimal constructor
     */
    public Companycatalogue(BigDecimal id, Company company, Catalogue catalogue) {
        this.id = id;
        this.company = company;
        this.catalogue = catalogue;
    }

    /**
     * full constructor
     */
    public Companycatalogue(BigDecimal id, Company company, Catalogue catalogue, BigDecimal active) {
        this.id = id;
        this.company = company;
        this.catalogue = catalogue;
        this.active = active;
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

        if (!(obj instanceof Companycatalogue)) {
            return false;
        }

        Companycatalogue compare = (Companycatalogue) obj;
        return compare.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "CompanyCatalogue{id=" + id + ", company=" + getCompany() + ", catalogue=" + getCatalogue() + "}";
    }
}