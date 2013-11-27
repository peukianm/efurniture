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
 * Videoproduct entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VIDEOPRODUCT", schema = "FURNITURE")
public class Videoproduct implements java.io.Serializable {

    // Fields
    private BigDecimal videoid;
    private Product product;
    private String description;
    private BigDecimal active;
    private String video;
    private String path;
    private BigDecimal primary;
    private String filename;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;

    // Constructors
    /**
     * default constructor
     */
    public Videoproduct() {
    }

    /**
     * minimal constructor
     */
    public Videoproduct(BigDecimal videoid, Product product, BigDecimal active, String path, String filename) {
        this.videoid = videoid;
        this.product = product;
        this.active = active;
        this.path = path;
        this.filename = filename;
    }

    /**
     * full constructor
     */
    public Videoproduct(BigDecimal videoid, Product product, String description, BigDecimal active, String video, String path, BigDecimal primary,
            String filename, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.videoid = videoid;
        this.product = product;
        this.description = description;
        this.active = active;
        this.video = video;
        this.path = path;
        this.primary = primary;
        this.filename = filename;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "VIDEOID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getVideoid() {
        return this.videoid;
    }

    public void setVideoid(BigDecimal videoid) {
        this.videoid = videoid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTID", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    @Column(name = "VIDEO")
    public String getVideo() {
        return this.video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Column(name = "PATH", nullable = false, length = 100)
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "PRIMARY", precision = 22, scale = 0)
    public BigDecimal getPrimary() {
        return this.primary;
    }

    public void setPrimary(BigDecimal primary) {
        this.primary = primary;
    }

    @Column(name = "FILENAME", nullable = false, length = 100)
    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

        if (!(obj instanceof Videoproduct)) {
            return false;
        }

        Videoproduct compare = (Videoproduct) obj;

        
        if (compare.videoid!=null && this.videoid!=null)
            return compare.videoid.equals(this.videoid);
        else
            return compare.filename.equals(this.filename);
    }

    @Override
    public int hashCode() {
        return videoid != null ? this.getClass().hashCode() + videoid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Video{" + "id=" + videoid + ", product=" + getProduct() + "}";
    }
}