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
 * Specificationcategory entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SPECIFICATIONCATEGORY", schema = "FURNITURE")
public class Specificationcategory implements java.io.Serializable {

    // Fields
    private BigDecimal specificationcategoryid;
    private String name;
    private BigDecimal active;
    private Set<Specification> specifications = new HashSet<Specification>(0);
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Specificationcategory() {
    }

    /**
     * minimal constructor
     */
    public Specificationcategory(BigDecimal specificationcategoryid, String name, BigDecimal active) {
        this.specificationcategoryid = specificationcategoryid;
        this.name = name;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Specificationcategory(BigDecimal specificationcategoryid, String name, BigDecimal active, Set<Specification> specifications, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.specificationcategoryid = specificationcategoryid;
        this.name = name;
        this.active = active;
        this.specifications = specifications;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "SPECIFICATIONCATEGORYID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getSpecificationcategoryid() {
        return this.specificationcategoryid;
    }

    public void setSpecificationcategoryid(BigDecimal specificationcategoryid) {
        this.specificationcategoryid = specificationcategoryid;
    }

    @Column(name = "NAME", nullable = false, length = 80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ACTIVE", nullable = false, precision = 22, scale = 0)
    public BigDecimal getActive() {
        return this.active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specificationcategory")
    public Set<Specification> getSpecifications() {
        return this.specifications;
    }

    public void setSpecifications(Set<Specification> specifications) {
        this.specifications = specifications;
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

        if (!(obj instanceof Specificationcategory)) {
            return false;
        }

        Specificationcategory compare = (Specificationcategory) obj;

        return compare.specificationcategoryid.equals(this.specificationcategoryid);
    }

    @Override
    public int hashCode() {
        return specificationcategoryid != null ? this.getClass().hashCode() + specificationcategoryid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Specificationcategory{" + "d=" + specificationcategoryid + ", name=" + getName() + "}";
    }
}