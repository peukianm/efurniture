package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Price entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRICE", schema = "FURNITURE")
public class Price implements java.io.Serializable {

    // Fields
    private BigDecimal priceid;
    private Currency currency;
    private Double amount;
    private Product product;
    private Company company;
    private Catalogue catalogue;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private Date pricedate;
    private Double discount;
    private Double initialprice;
    
    
    // Constructors
    /**
     * default constructor
     */
    public Price() {
    }

    /**
     * minimal constructor
     */
    public Price(BigDecimal priceid, Currency currency, Double amount, Product product, Company company, Catalogue catalogue, BigDecimal active) {
        this.priceid = priceid;
        this.currency = currency;
        this.amount = amount;
        this.product = product;
        this.company = company;
        this.catalogue = catalogue;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Price(BigDecimal priceid, Currency currency, Double amount, Product product, Company company, Catalogue catalogue, BigDecimal active,
            Timestamp createdTimestamp, Timestamp modifiedTimestamp, Date pricedate,Double discount, Double initialprice) {
        this.priceid = priceid;
        this.currency = currency;
        this.amount = amount;
        this.product = product;
        this.company = company;
        this.catalogue = catalogue;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        this.pricedate = pricedate;
        this.discount = discount;
        this.initialprice = initialprice;
    }

    // Property accessors
    @Id
    @Column(name = "PRICEID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getPriceid() {
        return this.priceid;
    }

    public void setPriceid(BigDecimal priceid) {
        this.priceid = priceid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENCYID", nullable = false)
    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "AMOUNT", nullable = false, precision = 22, scale=2)
    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
    @Column(name = "INITIALPRICE", nullable = false, precision = 22, scale=2)
    public Double getInitialprice() {
        return this.initialprice;
    }

    public void setInitialprice(Double initialprice) {
        this.initialprice = initialprice;
    }
    
    @Column(name = "DISCOUNT", nullable = false, precision = 22, scale=2)
    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "PRICEDATE", length = 7)
    public Date getPricedate() {
        return this.pricedate;
    }

    public void setPricedate(Date pricedate) {
        this.pricedate = pricedate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTID", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Price)) {
            return false;
        }

        Price compare = (Price) obj;

        return compare.priceid.equals(this.priceid);
    }

    @Override
    public int hashCode() {
        return priceid != null ? this.getClass().hashCode() + priceid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + priceid + ", product=" + getProduct() + " catalogue=" + getCatalogue() + " Company=" + getCompany() + " Price=" + getAmount() + "}";
    }
}