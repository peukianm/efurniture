package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Specification entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SPECIFICATION", schema = "FURNITURE")
@SequenceGenerator(name = "SEQ_SPECIFICATION", sequenceName = "SPECIFICATION_SEQ", allocationSize = 1)
public class Specification implements java.io.Serializable {

    // Fields
    private BigDecimal specificationid;
    private Specificationcategory specificationcategory;
    private String name;
    private String description;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private BigDecimal freetext;
    private BigDecimal multiplevalues;
    private BigDecimal multipleinsert;
    private BigDecimal color;
    private BigDecimal dimension;
    private List<Specificationvalue> specificationvalues = new ArrayList<Specificationvalue>(0);
    private Set<Productspecification> productspecifications = new HashSet<Productspecification>(0);
    private Set<Auditing> auditings = new HashSet<Auditing>(0);
    private Set<Itemspecification> itemspecifications = new HashSet<Itemspecification>(0);
    private BigDecimal ordered;

    // Constructors
    /**
     * default constructor
     */
    public Specification() {
    }

    /**
     * minimal constructor
     */
    public Specification(BigDecimal specificationid, Specificationcategory specificationcategory, String name, BigDecimal active, BigDecimal freetext, BigDecimal multiplevalues) {
        this.specificationid = specificationid;
        this.specificationcategory = specificationcategory;
        this.name = name;
        this.active = active;
        this.freetext = freetext;
        this.multiplevalues = multiplevalues;
    }

    /**
     * full constructor
     */
    public Specification(BigDecimal specificationid, Specificationcategory specificationcategory, String name, String description, BigDecimal active,
            Timestamp createdTimestamp, Timestamp modifiedTimestamp, BigDecimal freetext, List<Specificationvalue> specificationvalues,
            Set<Productspecification> productspecifications, Set<Auditing> auditings, Set<Itemspecification> itemspecifications) {
        this.specificationid = specificationid;
        this.specificationcategory = specificationcategory;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        this.freetext = freetext;
        this.specificationvalues = specificationvalues;
        this.productspecifications = productspecifications;
        this.auditings = auditings;
        this.itemspecifications = itemspecifications;
    }

    // Property accessors
    @Id
    @Column(name = "SPECIFICATIONID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SPECIFICATION")
    public BigDecimal getSpecificationid() {
        return this.specificationid;
    }

    public void setSpecificationid(BigDecimal specificationid) {
        this.specificationid = specificationid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPECIFICATIONCATEGORYID", nullable = false)
    public Specificationcategory getSpecificationcategory() {
        return this.specificationcategory;
    }

    public void setSpecificationcategory(Specificationcategory specificationcategory) {
        this.specificationcategory = specificationcategory;
    }

    @Column(name = "NAME", nullable = false, length = 300)
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

    @Column(name = "FREETEXT", nullable = false, precision = 22, scale = 0)
    public BigDecimal getFreetext() {
        return this.freetext;
    }

    public void setFreetext(BigDecimal freetext) {
        this.freetext = freetext;
    }

    @Column(name = "MULTIPLEVALUES", nullable = false, precision = 22, scale = 0)
    public BigDecimal getMultiplevalues() {
        return this.multiplevalues;
    }

    public void setMultiplevalues(BigDecimal multiplevalues) {
        this.multiplevalues = multiplevalues;
    }
    
    
    
     @Column(name = "MULTIPLEINSERT", nullable = false, precision = 22, scale = 0)
    public BigDecimal getMultipleinsert() {
        return this.multipleinsert;
    }

    public void setMultipleinsert(BigDecimal multipleinsert) {
        this.multipleinsert = multipleinsert;
    }
    
    

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specification")
    public List<Specificationvalue> getSpecificationvalues() {
        return this.specificationvalues;
    }

    public void setSpecificationvalues(List<Specificationvalue> specificationvalues) {
        this.specificationvalues = specificationvalues;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specification")
    public Set<Productspecification> getProductspecifications() {
        return this.productspecifications;
    }

    public void setProductspecifications(Set<Productspecification> productspecifications) {
        this.productspecifications = productspecifications;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specification")
    public Set<Auditing> getAuditings() {
        return this.auditings;
    }

    public void setAuditings(Set<Auditing> auditings) {
        this.auditings = auditings;
    }

    @Column(name = "COLOR", precision = 22, scale = 0)
    public BigDecimal getColor() {
        return this.color;
    }

    public void setColor(BigDecimal color) {
        this.color = color;
    }

    @Column(name = "DIMENSION", precision = 22, scale = 0)
    public BigDecimal getDimension() {
        return this.dimension;
    }

    public void setDimension(BigDecimal dimension) {
        this.dimension = dimension;
    }

   
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "specification")
    public Set<Itemspecification> getItemspecifications() {
        return this.itemspecifications;
    }

    public void setItemspecifications(Set<Itemspecification> itemspecifications) {
        this.itemspecifications = itemspecifications;
    }

    @Transient
    public BigDecimal getOrdered() {
        return ordered;
    }

    public void setOrdered(BigDecimal ordered) {
        this.ordered = ordered;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Specification)) {
            return false;
        }

        Specification compare = (Specification) obj;

        return compare.specificationid.equals(this.specificationid);
    }

    @Override
    public int hashCode() {
        return specificationid != null ? this.getClass().hashCode() + specificationid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Specification{" + "Specificationid=" + specificationid + ", name=" + getName() + " ordered="+ordered+"}";
    }
}