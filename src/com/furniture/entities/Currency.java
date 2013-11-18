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
 * Currency entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CURRENCY", schema = "FURNITURE")
public class Currency implements java.io.Serializable {

    // Fields
    private BigDecimal currencyid;
    private String name;
    private String symbol;
    private Set<Price> prices = new HashSet<Price>(0);

    // Constructors
    /**
     * default constructor
     */
    public Currency() {
    }

    /**
     * minimal constructor
     */
    public Currency(BigDecimal currencyid, String name, String symbol) {
        this.currencyid = currencyid;
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * full constructor
     */
    public Currency(BigDecimal currencyid, String name, String symbol, Set<Price> prices) {
        this.currencyid = currencyid;
        this.name = name;
        this.symbol = symbol;
        this.prices = prices;
    }

    // Property accessors
    @Id
    @Column(name = "CURRENCYID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getCurrencyid() {
        return this.currencyid;
    }

    public void setCurrencyid(BigDecimal currencyid) {
        this.currencyid = currencyid;
    }

    @Column(name = "NAME", nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SYMBOL", nullable = false, length = 8)
    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "currency")
    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Currency)) {
            return false;
        }

        Currency compare = (Currency) obj;
        return compare.currencyid.equals(this.currencyid);
    }

    @Override
    public int hashCode() {
        return currencyid != null ? this.getClass().hashCode() + currencyid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Currency{id=" + currencyid + ", name=" + getName() + ", symbol==" + getSymbol() + "}";
    }
}