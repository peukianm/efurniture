package com.furniture.entities;

import java.math.BigDecimal;
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
 * Measurment entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MEASURMENT", schema = "FURNITURE")
public class Measurment implements java.io.Serializable {

    // Fields
    private BigDecimal measurmentid;
    private String name;
    private String symbol;
    private BigDecimal type;
    private BigDecimal active;
    private BigDecimal ordered;
    private Set<Productvalue> productvalues = new HashSet<Productvalue>(0);

    // Constructors
    /**
     * default constructor
     */
    public Measurment() {
    }

    /**
     * minimal constructor
     */
    public Measurment(BigDecimal measurmentid, String name, String symbol, BigDecimal active) {
        this.measurmentid = measurmentid;
        this.name = name;
        this.symbol = symbol;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Measurment(BigDecimal measurmentid, String name, String symbol, BigDecimal type, BigDecimal active, BigDecimal ordered,
            Set<Productvalue> productvalues) {
        this.measurmentid = measurmentid;
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.active = active;
        this.ordered = ordered;
        this.productvalues = productvalues;
    }

    // Property accessors
    @Id
    @Column(name = "MEASURMENTID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getMeasurmentid() {
        return this.measurmentid;
    }

    public void setMeasurmentid(BigDecimal measurmentid) {
        this.measurmentid = measurmentid;
    }

    @Column(name = "NAME", nullable = false, length = 80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SYMBOL", nullable = false, length = 80)
    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Column(name = "TYPE", precision = 22, scale = 0)
    public BigDecimal getType() {
        return this.type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "measurment")
    public Set<Productvalue> getProductvalues() {
        return this.productvalues;
    }

    public void setProductvalues(Set<Productvalue> productvalues) {
        this.productvalues = productvalues;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Measurment)) {
            return false;
        }

        Measurment compare = (Measurment) obj;

        return compare.measurmentid.equals(this.measurmentid);
    }

    @Override
    public int hashCode() {
        return measurmentid != null ? this.getClass().hashCode() + measurmentid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Masurment{" + "Measurmentidd=" + measurmentid + ", name=" + getName() + "}";
    }
}