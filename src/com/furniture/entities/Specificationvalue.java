package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Specificationvalue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SPECIFICATIONVALUE", schema = "FURNITURE")
@SequenceGenerator(name = "SEQ_SPECIFICATIONVALUE", sequenceName = "SPECIFICATIONVALUE_SEQ", allocationSize = 1)
public class Specificationvalue implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Specification specification;
    private Svalue svalue;
    private BigDecimal active;
    private BigDecimal ordered;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    
    // Constructors
    /**
     * default constructor
     */
    public Specificationvalue() {
    }

    /**
     * minimal constructor
     */
    public Specificationvalue(BigDecimal id, Specification specification, Svalue svalue, BigDecimal active) {
        this.id = id;
        this.specification = specification;
        this.svalue = svalue;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Specificationvalue(BigDecimal id, Specification specification, Svalue svalue, BigDecimal active, BigDecimal ordered, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.id = id;
        this.specification = specification;
        this.svalue = svalue;
        this.active = active;
        this.ordered = ordered;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SPECIFICATIONVALUE")
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECIFICATIONID", nullable = false)
    public Specification getSpecification() {
        return this.specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VALUEID", nullable = false)
    public Svalue getSvalue() {
        return this.svalue;
    }

    public void setSvalue(Svalue svalue) {
        this.svalue = svalue;
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

        if (!(obj instanceof Specificationvalue)) {
            return false;
        }

        Specificationvalue compare = (Specificationvalue) obj;
       
        if (compare.id==null)
            return compare.specification.equals(this.specification); 
        else {
            return compare.id.equals(this.id);
        }
        
//        return compare.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Specificationvalue{" + "d=" + id + ", specification=" + getSpecification() + " svalue=" + getSvalue() + "}";
    }
}