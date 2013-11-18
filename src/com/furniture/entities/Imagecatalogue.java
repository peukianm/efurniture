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
 * Imagecatalogue entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "IMAGECATALOGUE", schema = "FURNITURE")
public class Imagecatalogue implements java.io.Serializable {

    // Fields
    private BigDecimal imageid;
    private Catalogue catalogue;
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
    public Imagecatalogue() {
    }

    /**
     * minimal constructor
     */
    public Imagecatalogue(BigDecimal imageid, Catalogue catalogue, BigDecimal active) {
        this.imageid = imageid;
        this.catalogue = catalogue;
        this.active = active;
    }

    /**
     * full constructor
     */
    public Imagecatalogue(BigDecimal imageid, Catalogue catalogue, String description, BigDecimal active, String image, String path, BigDecimal primary, String filename,
               Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.imageid = imageid;
        this.catalogue = catalogue;
        this.description = description;
        this.active = active;
        this.image = image;
        this.path = path;
        this.primary = primary;
        this.filename = filename;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
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
    @JoinColumn(name = "CATALOGUEID", nullable = false)
    public Catalogue getCatalogue() {
        return this.catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
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

    @Column(name = "PRIMARY", precision = 22, scale = 0)
    public BigDecimal getPrimary() {
        return this.primary;
    }

    public void setPrimary(BigDecimal primary) {
        this.primary = primary;
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

        if (!(obj instanceof Imagecatalogue)) {
            return false;
        }

        Imagecatalogue compare = (Imagecatalogue) obj;
        return compare.imageid.equals(this.imageid);
    }

    @Override
    public int hashCode() {
        return imageid != null ? this.getClass().hashCode() + imageid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Image{id=" + imageid + ", catalogue=" + getCatalogue() + ", path=" + getPath() + "}";
    }
}