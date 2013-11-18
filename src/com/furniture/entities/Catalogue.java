package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Catalogue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CATALOGUE", schema = "FURNITURE")
public class Catalogue implements java.io.Serializable {

    // Fields
    private BigDecimal catalogueid;
    private String name;
    private String description;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private Date createddate;
    private Set<Catalogueproductline> catalogueproductlines = new HashSet<Catalogueproductline>(0);
    private Set<Imagecatalogue> imagecatalogues = new HashSet<Imagecatalogue>(0);
    private Set<Companycatalogue> companycatalogues = new HashSet<Companycatalogue>(0);
    private Set<Auditing> auditings = new HashSet<Auditing>(0);
    private Set<Price> prices = new HashSet<Price>(0);
    private List<Company> companies = new ArrayList<Company>(0);
    private List<Productline> productlines = new ArrayList<Productline>(0);

    // Constructors
    /**
     * default constructor
     */
    public Catalogue() {
    }

    /**
     * minimal constructor
     */
    public Catalogue(BigDecimal catalogueid, String name) {
        this.catalogueid = catalogueid;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Catalogue(BigDecimal catalogueid, String name, String description, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp,
            Set<Catalogueproductline> catalogueproductlines, Set<Imagecatalogue> imagecatalogues, Set<Companycatalogue> companycatalogues,
            Set<Auditing> auditings, Set<Price> prices, Date createddate, List<Company> companies, List<Productline> productlines) {
        this.catalogueid = catalogueid;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        this.catalogueproductlines = catalogueproductlines;
        this.imagecatalogues = imagecatalogues;
        this.companycatalogues = companycatalogues;
        this.auditings = auditings;
        this.prices = prices;
        this.createddate = createddate;
        this.companies = companies;
        this.productlines = productlines;
    }

    // Property accessors
    @Id
    @Column(name = "CATALOGUEID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getCatalogueid() {
        return this.catalogueid;
    }

    public void setCatalogueid(BigDecimal catalogueid) {
        this.catalogueid = catalogueid;
    }

    @Column(name = "NAME", nullable = false, length = 80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATEDDATE", length = 7)
    public Date getCreateddate() {
        return this.createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Column(name = "DESCRIPTION", length = 400)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalogue")
    public Set<Catalogueproductline> getCatalogueproductlines() {
        return this.catalogueproductlines;
    }

    public void setCatalogueproductlines(Set<Catalogueproductline> catalogueproductlines) {
        this.catalogueproductlines = catalogueproductlines;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalogue")
    public Set<Imagecatalogue> getImagecatalogues() {
        return this.imagecatalogues;
    }

    public void setImagecatalogues(Set<Imagecatalogue> imagecatalogues) {
        this.imagecatalogues = imagecatalogues;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalogue")
    public Set<Companycatalogue> getCompanycatalogues() {
        return this.companycatalogues;
    }

    public void setCompanycatalogues(Set<Companycatalogue> companycatalogues) {
        this.companycatalogues = companycatalogues;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalogue")
    public Set<Auditing> getAuditings() {
        return this.auditings;
    }

    public void setAuditings(Set<Auditing> auditings) {
        this.auditings = auditings;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalogue")
    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }
    
    
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "COMPANYCATALOGUE",
    joinColumns = {
        @JoinColumn(name = "CATALOGUEID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "COM{ANYID")
    })
      
    /**
     * @return the catalogues
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * @param catalogues the books to set
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
    
        /**
     * @return the catalogues
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CATALOGUEPRODUCTLINE",
    joinColumns = {
        @JoinColumn(name = "CATALOGUEID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "PRODUCTLINEID")
    })
    public List<Productline> getProductlines() {
        return productlines;
    }

    /**
     * @param catalogues the books to set
     */
    public void setProductlines(List<Productline> productlines) {
        this.productlines = productlines;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Catalogue)) {
            return false;
        }

        Catalogue compare = (Catalogue) obj;
        return compare.catalogueid.equals(this.catalogueid);
    }

    @Override
    public int hashCode() {
        return catalogueid != null ? this.getClass().hashCode() + catalogueid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Catalogue{id=" + catalogueid + ", Name=" + getName() + "}";
    }
}