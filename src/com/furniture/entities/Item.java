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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Item entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ITEM", schema = "FURNITURE")
@SequenceGenerator(name = "SEQ_ITEM", sequenceName = "ITEM_SEQ", allocationSize = 1)
public class Item implements java.io.Serializable {

    // Fields
    private BigDecimal itemid;
    private String name;
    private String description;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private BigDecimal active;
    private Set<Auditing> auditings = new HashSet<Auditing>(0);
    private Set<Product> products = new HashSet<Product>(0);
    private List<Itemspecification> itemspecifications = new ArrayList<Itemspecification>(0);

    // Constructors
    /**
     * default constructor
     */
    public Item() {
    }

    /**
     * minimal constructor
     */
    public Item(BigDecimal itemid, String name) {
        this.itemid = itemid;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Item(BigDecimal itemid, String name, String description, Timestamp createdTimestamp, Timestamp modifiedTimestamp, BigDecimal active,
            Set<Auditing> auditings, Set<Product> products, List<Itemspecification> itemspecifications) {
        this.itemid = itemid;
        this.name = name;
        this.description = description;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        this.active = active;
        this.auditings = auditings;
        this.products = products;
        this.itemspecifications = itemspecifications;
    }

    // Property accessors
    @Id
    @Column(name = "ITEMID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM")
    public BigDecimal getItemid() {
        return this.itemid;
    }

    public void setItemid(BigDecimal itemid) {
        this.itemid = itemid;
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

    @Column(name = "ACTIVE", precision = 22, scale = 0)
    public BigDecimal getActive() {
        return this.active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    public Set<Auditing> getAuditings() {
        return this.auditings;
    }

    public void setAuditings(Set<Auditing> auditings) {
        this.auditings = auditings;
    }

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "item")
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    public List<Itemspecification> getItemspecifications() {
        return this.itemspecifications;
    }

    public void setItemspecifications(List<Itemspecification> itemspecifications) {
        this.itemspecifications = itemspecifications;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item compare = (Item) obj;

        return compare.itemid.equals(this.itemid);
    }

    @Override
    public int hashCode() {
        return itemid != null ? this.getClass().hashCode() + itemid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "item{" + "id=" + itemid + ", name=" + getName() + "}";
    }
}