package com.furniture.entities;

import com.furniture.entities.Product;
import com.furniture.entities.Productvalue;
import com.furniture.entities.Specification;
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

/**   
 * Productspecification entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCTSPECIFICATION", schema = "FURNITURE")
@SequenceGenerator(name = "SEQ_PRODUCTSPECIFICATION", sequenceName = "PRODUCTSPECIFICATION_SEQ", allocationSize = 1)
public class Productspecification implements java.io.Serializable {

    // Fields
    private BigDecimal id;
    private Specification specification;
    private Product product;
    private BigDecimal freetext;
    private BigDecimal ordered;
    private BigDecimal active;
    private List<Productvalue> productvalues = new ArrayList<Productvalue>(0);
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    
    // Constructors
    /**
     * default constructor
     */
    public Productspecification() {
    }

    /**
     * minimal constructor
     */
    public Productspecification(BigDecimal id, Specification specification, Product product, BigDecimal ordered, BigDecimal active) {
        this.id = id;
        this.specification = specification;
        this.product = product;
        this.ordered = ordered;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Productspecification(BigDecimal id, Specification specification, Product product, BigDecimal freetext, BigDecimal ordered, BigDecimal active,
            List<Productvalue> productvalues, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.id = id;
        this.specification = specification;
        this.product = product;
        this.freetext = freetext;
        this.ordered = ordered;
        this.active = active;
        this.productvalues = productvalues;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCTSPECIFICATION")
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
    @JoinColumn(name = "PRODUCTID", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "FREETEXT", precision = 22, scale = 0)
    public BigDecimal getFreetext() {
        return this.freetext;
    }

    public void setFreetext(BigDecimal freetext) {
        this.freetext = freetext;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productspecification")
    public List<Productvalue> getProductvalues() {
        return this.productvalues;
    }

    public void setProductvalues(List<Productvalue> productvalues) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Productspecification)) {
            return false;
        }

        Productspecification compare = (Productspecification) obj;
        
        if (id==null)
            return compare.specification.getSpecificationid().equals(this.specification.getSpecificationid()); 
        else {
            return compare.id.equals(this.id);
        }

        //return compare.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "ProductSpecification{" + "id=" + id + ", product=" + getProduct() + " specification=" + getSpecification() + "}";
    }
}