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
 * Imagevalue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IMAGEVALUE", schema = "FURNITURE")
public class Imagevalue implements java.io.Serializable {

    // Fields
    private BigDecimal imageid;
    private Svalue svalue;
    private String description;
    private BigDecimal active;
    private String image;
    private String path;
    private String filename;
    private BigDecimal primary;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Imagevalue() {
    }

    /**
     * minimal constructor
     */
    public Imagevalue(BigDecimal imageid, Svalue svalue, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.imageid = imageid;
        this.svalue = svalue;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    /**
     * full constructor
     */
    public Imagevalue(BigDecimal imageid, Svalue svalue, String description, BigDecimal active, String image, String path, BigDecimal primary, String filename) {
        this.imageid = imageid;
        this.svalue = svalue;
        this.description = description;
        this.active = active;
        this.image = image;
        this.path = path;
        this.primary = primary;
        this.filename = filename;
    }

    // Property accessors
    @Column(name = "PRIMARY", precision = 22, scale = 0)
    public BigDecimal getPrimary() {
        return this.primary;
    }

    public void setPrimary(BigDecimal primary) {
        this.primary = primary;
    }

    @Id
    @Column(name = "IMAGEID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getImageid() {
        return this.imageid;
    }

    public void setImageid(BigDecimal imageid) {
        this.imageid = imageid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VALUEID", nullable = false)
    public Svalue getSvalue() {
        return this.svalue;
    }

    public void setSvalue(Svalue svalue) {
        this.svalue = svalue;
    }

    @Column(name = "DESCRIPTION", length = 80)
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
    
    @Column(name = "FILENAME", nullable = false)
    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.path = filename;
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

        if (!(obj instanceof Imagevalue)) {
            return false;
        }

        Imagevalue compare = (Imagevalue) obj;
        return compare.imageid.equals(this.imageid);
    }

    @Override
    public int hashCode() {
        return imageid != null ? this.getClass().hashCode() + imageid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Image{id=" + imageid + ", value=" + getSvalue() + ", path=" + getPath() + "}";
    }
}