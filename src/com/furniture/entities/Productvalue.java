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
 * Productvalue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCTVALUE", schema = "FURNITURE")
@SequenceGenerator(name = "SEQ_PRODUCTVALUE", sequenceName = "PRODUCTVALUE_SEQ", allocationSize = 1)
public class Productvalue implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Productspecification productspecification;
    private Svalue svalue;
    private String value;
    private BigDecimal ordered;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private Measurment measurment;
    
    // Constructors
    /**
     * default constructor
     */
    public Productvalue() {
    }

    /**
     * minimal constructor
     */
    public Productvalue(BigDecimal id, Productspecification productspecification, BigDecimal active) {
        this.id = id;
        this.productspecification = productspecification;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Productvalue(BigDecimal id, Productspecification productspecification, Svalue svalue, String value, BigDecimal ordered, BigDecimal active, 
            Timestamp createdTimestamp, Timestamp modifiedTimestamp, Measurment measurment) {
        this.id = id;
        this.productspecification = productspecification;
        this.svalue = svalue;
        this.value = value;
        this.ordered = ordered;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        this.measurment = measurment;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCTVALUE")
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTSPECIFICATIONID", nullable = false)
    public Productspecification getProductspecification() {
        return this.productspecification;
    }

    public void setProductspecification(Productspecification productspecification) {
        this.productspecification = productspecification;
    }
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEASURMENTID", nullable = false)
    public Measurment getMeasurment() {
        return this.measurment;
    }

    public void setMeasurment(Measurment measurment) {
        this.measurment = measurment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VALUEID")
    public Svalue getSvalue() {
        return this.svalue;
    }

    public void setSvalue(Svalue svalue) {
        this.svalue = svalue;
    }

    @Column(name = "VALUE", length = 200)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "ORDERED", precision = 22, scale = 0)
    public BigDecimal getOrdered() {
        return this.ordered;
    }

    public void setOrdered(BigDecimal ordered) {
        this.ordered = ordered;
    }

    @Column(name = "ACTIVE", nullable = false, precision = 22, scale = 0)
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
    public String toString() {
        return "ProductValue{" + "id=" + id + ", SVALUE=" + getSvalue() + " ProductSpecification=" + getProductspecification() + "}";
    }
}