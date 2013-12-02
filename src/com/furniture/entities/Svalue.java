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
 * Svalue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SVALUE", schema = "FURNITURE")
public class Svalue implements java.io.Serializable {

    // Fields
    private BigDecimal valueid;
    private String name;
    private String comments;
    private BigDecimal hasimage;
    private Set<Specificationvalue> specificationvalues = new HashSet<Specificationvalue>(0);
    private Set<Imagevalue> imagevalues = new HashSet<Imagevalue>(0);
    private Set<Productvalue> productvalues = new HashSet<Productvalue>(0);
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    
    // Constructors
    /**
     * default constructor
     */
    public Svalue() {
    }

    /**
     * minimal constructor
     */
    public Svalue(BigDecimal valueid, String name, BigDecimal hasimage) {
        this.valueid = valueid;
        this.name = name;
        this.hasimage = hasimage;
    }  

    /**
     * full constructor
     */
    public Svalue(BigDecimal valueid, String name, String comments, BigDecimal hasimage, Set<Specificationvalue> specificationvalues,
            Set<Imagevalue> imagevalues, Set<Productvalue> productvalues, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.valueid = valueid;
        this.name = name;
        this.comments = comments;
        this.hasimage = hasimage;
        this.specificationvalues = specificationvalues;
        this.imagevalues = imagevalues;
        this.productvalues = productvalues;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "VALUEID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getValueid() {
        return this.valueid;
    }

    public void setValueid(BigDecimal valueid) {
        this.valueid = valueid;
    }

    @Column(name = "NAME", nullable = false, length = 300)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "COMMENTS", length = 400)
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Column(name = "HASIMAGE", nullable = false, precision = 22, scale = 0)
    public BigDecimal getHasimage() {
        return this.hasimage;
    }

    public void setHasimage(BigDecimal hasimage) {
        this.hasimage = hasimage;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "svalue")
    public Set<Specificationvalue> getSpecificationvalues() {
        return this.specificationvalues;
    }

    public void setSpecificationvalues(Set<Specificationvalue> specificationvalues) {
        this.specificationvalues = specificationvalues;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "svalue")
    public Set<Imagevalue> getImagevalues() {
        return this.imagevalues;
    }

    public void setImagevalues(Set<Imagevalue> imagevalues) {
        this.imagevalues = imagevalues;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "svalue")
    public Set<Productvalue> getProductvalues() {
        return this.productvalues;
    }

    public void setProductvalues(Set<Productvalue> productvalues) {
        this.productvalues = productvalues;
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
}