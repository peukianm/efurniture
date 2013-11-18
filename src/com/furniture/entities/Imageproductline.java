package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Imageproductline entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IMAGEPRODUCTLINE", schema = "FURNITURE")
public class Imageproductline implements java.io.Serializable {

    // Fields
    private BigDecimal imageid;
    private Productline productline;
    private String description;
    private BigDecimal active;
    private String image;
    private String path;
    private BigDecimal primary;
    private String filename;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    
    // Constructors
    /**
     * default constructor
     */
    public Imageproductline() {
    }

    /**
     * minimal constructor
     */
    public Imageproductline(BigDecimal imageid, Productline productline, BigDecimal active) {
        this.imageid = imageid;
        this.productline = productline;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Imageproductline(BigDecimal imageid, Productline productline, String description, BigDecimal active, String image, String path, BigDecimal primary, String filename,
                            Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.imageid = imageid;
        this.productline = productline;
        this.description = description;
        this.active = active;
        this.image = image;
        this.path = path;
        this.primary = primary;
        this.filename = filename;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    @Column(name = "PRIMARY", precision = 22, scale = 0)
    public BigDecimal getPrimary() {
        return this.primary;
    }

    public void setPrimary(BigDecimal primary) {
        this.primary = primary;
    }

    // Property accessors
    @Id
    @Column(name = "IMAGEID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getImageid() {
        return this.imageid;
    }

    public void setImageid(BigDecimal imageid) {
        this.imageid = imageid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTLINEID", nullable = false)
    public Productline getProductline() {
        return this.productline;
    }

    public void setProductline(Productline productline) {
        this.productline = productline;
    }

    @Column(name = "DESCRIPTION", length = 400)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name = "FILENAME", nullable = false)
    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.path = filename;
    }

    @Column(name = "ACTIVE", nullable = false, precision = 22, scale = 0)
    public BigDecimal getActive() {
        return this.active;
    }

    public void setActive(BigDecimal active) {
        this.active = active;
    }

    @Column(name = "IMAGE")
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "PATH", nullable = false)
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
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

        if (!(obj instanceof Imageproductline)) {
            return false;
        }

        Imageproductline compare = (Imageproductline) obj;
        return compare.imageid.equals(this.imageid);
    }

    @Override
    public int hashCode() {
        return imageid != null ? this.getClass().hashCode() + imageid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Image{id=" + imageid + ", line=" + getProductline() + ", path=" + getPath() + "}";
    }
}