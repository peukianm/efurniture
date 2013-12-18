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
 * Productline entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCTLINE", schema = "FURNITURE")
public class Productline implements java.io.Serializable {

    // Fields
    private BigDecimal productlineid;
    private String name;
    private String description;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private Date createddate;
    //private Set<Catalogueproductline> catalogueproductlines = new HashSet<Catalogueproductline>(0);
    private Set<Imageproductline> imageproductlines = new HashSet<Imageproductline>(0);
    //private Set<Productlineproduct> productlineproducts = new HashSet<Productlineproduct>(0);
    private Set<Auditing> auditings = new HashSet<Auditing>(0);
    private List<Catalogue> catalogues = new ArrayList<Catalogue>(0);
    private List<Product> products = new ArrayList<Product>(0);
    
    
    // Constructors
    /**
     * default constructor
     */
    public Productline() {
    }

    /**
     * minimal constructor
     */
    public Productline(BigDecimal productlineid, String name) {
        this.productlineid = productlineid;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Productline(BigDecimal productlineid, String name, String description, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp,
            Set<Catalogueproductline> catalogueproductlines, Set<Imageproductline> imageproductlines, Set<Productlineproduct> productlineproducts, Date createddate,
            Set<Auditing> auditings, List<Catalogue> catalogues) {
        this.productlineid = productlineid;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        //this.catalogueproductlines = catalogueproductlines;
        this.imageproductlines = imageproductlines;
        //this.productlineproducts = productlineproducts;
        this.auditings = auditings;
        this.createddate = createddate;
        this.catalogues = catalogues;
    }

    // Property accessors
    @Id
    @Column(name = "PRODUCTLINEID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getProductlineid() {
        return this.productlineid;
    }

    public void setProductlineid(BigDecimal productlineid) {
        this.productlineid = productlineid;
    }

    @Column(name = "NAME", nullable = false, length = 80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATEDDATE", length = 7)
    public Date getCreateddate() {
        return this.createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
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

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productline")
//    public Set<Catalogueproductline> getCatalogueproductlines() {
//        return this.catalogueproductlines;
//    }
//
//    public void setCatalogueproductlines(Set<Catalogueproductline> catalogueproductlines) {
//        this.catalogueproductlines = catalogueproductlines;
//    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productline")
    public Set<Imageproductline> getImageproductlines() {
        return this.imageproductlines;
    }

    public void setImageproductlines(Set<Imageproductline> imageproductlines) {
        this.imageproductlines = imageproductlines;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productline")
//    public Set<Productlineproduct> getProductlineproducts() {
//        return this.productlineproducts;
//    }
//
//    public void setProductlineproducts(Set<Productlineproduct> productlineproducts) {
//        this.productlineproducts = productlineproducts;
//    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productline")
    public Set<Auditing> getAuditings() {
        return this.auditings;
    }

    public void setAuditings(Set<Auditing> auditings) {
        this.auditings = auditings;
    }
    
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CATALOGUEPRODUCTLINE",
    joinColumns = {
        @JoinColumn(name = "PRODUCTLINEID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "CATALOGUEID")
    })
    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    /**
     * @param catalogues the books to set
     */
    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }
    

    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCTLINEPRODUCT",
    joinColumns = {
        @JoinColumn(name = "PRODUCTLINEID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "PRODUCTID")
    })
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param catalogues the books to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Productline)) {
            return false;
        }

        Productline compare = (Productline) obj;

        return compare.productlineid.equals(this.productlineid);
    }

    @Override
    public int hashCode() {
        return productlineid != null ? this.getClass().hashCode() + productlineid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "ProductLine{" + "id=" + productlineid + ", name=" + getName() + " user=" + "}";
    }
}