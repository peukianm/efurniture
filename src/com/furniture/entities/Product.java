package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Product entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCT", schema = "FURNITURE")
@SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
@Cacheable(false)
public class Product implements java.io.Serializable {

    // Fields
    private BigDecimal productid;
    private Product product;
    private Item item;
    private String name;
    private String description;
    private BigDecimal active;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    //private BigDecimal subproduct;
    private Productcategory productcategory;
    private List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);
    private List<Productspecification> productspecifications = new ArrayList<Productspecification>(0);
    private List<Productlineproduct> productlineproducts = new ArrayList<Productlineproduct>(0);
    private List<Imageproduct> imageproducts = new ArrayList<Imageproduct>(0);
    //private List<Product> products = new ArrayList<Product>(0);
    private Date createddate;
    private Set<Auditing> auditings = new HashSet<Auditing>(0);
    private List<Price> prices = new ArrayList<Price>(0);
    private List<Catalogue> catalogues = new ArrayList<Catalogue>(0);
    private List<Company> orderedCompanies = new ArrayList<Company>(0);
    private List<Productline> orderedProductlines = new ArrayList<Productline>(0);
    private List<Videoproduct> videoproducts = new ArrayList<Videoproduct>(0);
    private List<Category> categories = new ArrayList<Category>(0);
    private List<Product> subproducts = new ArrayList<Product>(0);
    private List<Product> parentproducts = new ArrayList<Product>(0);

    // Constructors
    /**
     * default constructor
     */
    public Product() {
    }

    /**
     * minimal constructor
     */
    public Product(BigDecimal productid, String name) {
        this.productid = productid;
        this.name = name;
    }

    /**
     * full constructor
     */
    public Product(BigDecimal productid, Product product, Item item, String name, String description, BigDecimal active, Timestamp createdTimestamp, Productcategory productcategory,
            Timestamp modifiedTimestamp, List<Companyproduct> companyproducts, List<Productspecification> productspecifications, Date createddate, List<Product> parentproducts,
            List<Productlineproduct> productlineproducts, List<Imageproduct> imageproducts, Set<Auditing> auditings, List<Price> prices, List<Category> categories) {
        this.productid = productid;
        this.product = product;
        this.item = item;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
        //this.subproduct = subproduct;
        this.companyproducts = companyproducts;
        this.productspecifications = productspecifications;
        this.productlineproducts = productlineproducts;
        this.imageproducts = imageproducts;
        //this.products = products;
        this.auditings = auditings;
        this.prices = prices;
        this.productcategory = productcategory;
        this.createddate = createddate;
        this.categories = categories;
        this.parentproducts = parentproducts;
        this.subproducts = subproducts;
    }
 
    // Property accessors
    @Id
    @Column(name = "PRODUCTID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    public BigDecimal getProductid() {
        return this.productid;
    }

    public void setProductid(BigDecimal productid) {
        this.productid = productid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENTPRODUCTID")
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEMID")
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATEDDATE", length = 7)
    public Date getCreateddate() {
        return this.createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Column(name = "NAME", nullable = false, length = 80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION", length = 800)
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

    @Version
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

//    @Column(name = "SUBPRODUCT", precision = 22, scale = 0)
//    public BigDecimal getSubproduct() {
//        return this.subproduct;
//    }
//
//    public void setSubproduct(BigDecimal subproduct) {
//        this.subproduct = subproduct;
//    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public List<Companyproduct> getCompanyproducts() {
        return this.companyproducts;
    }

    public void setCompanyproducts(List<Companyproduct> companyproducts) {
        this.companyproducts = companyproducts;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public List<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public List<Productspecification> getProductspecifications() {
        return this.productspecifications;
    }

    public void setProductspecifications(List<Productspecification> productspecifications) {
        this.productspecifications = productspecifications;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public List<Productlineproduct> getProductlineproducts() {
        return this.productlineproducts;
    }

    public void setProductlineproducts(List<Productlineproduct> productlineproducts) {
        this.productlineproducts = productlineproducts;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public List<Imageproduct> getImageproducts() {
        return this.imageproducts;
    }

    public void setImageproducts(List<Imageproduct> imageproducts) {
        this.imageproducts = imageproducts;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
//    public List<Product> getProducts() {
//        return this.products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public List<Videoproduct> getVideoproducts() {
        return this.videoproducts;
    }

    public void setVideoproducts(List<Videoproduct> videoproducts) {
        this.videoproducts = videoproducts;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    public Set<Auditing> getAuditings() {
        return this.auditings;
    }

    public void setAuditings(Set<Auditing> auditings) {
        this.auditings = auditings;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTCATEGORYID", nullable = true)
    public Productcategory getProductcategory() {
        return this.productcategory;
    }

    public void setProductcategory(Productcategory productcategory) {
        this.productcategory = productcategory;
    }

    @Transient
    public List<Catalogue> getCatalogues() {
        Set<Catalogue> catal = new HashSet<Catalogue>(0);
        if (productlineproducts != null) {
            for (int i = 0; i < productlineproducts.size(); i++) {
                Productlineproduct productlineproduct = productlineproducts.get(i);
                Productline productline = productlineproduct.getProductline();
                if (productline.getActive().equals(BigDecimal.ONE)) {
                    for (int j = 0; j < productline.getCatalogues().size(); j++) {
                        Catalogue catalogue = productline.getCatalogues().get(j);
                        if (catalogue.getActive().equals(BigDecimal.ONE)) {
                            catal.add(catalogue);
                        }
                    }
                }
            }
            catalogues = new ArrayList<Catalogue>(catal);
            Collections.sort(catalogues, new Comparator<Catalogue>() {
                public int compare(Catalogue one, Catalogue other) {
                    return one.getName().compareTo(other.getName());
                }
            });

            return catalogues;
        } else {
            return new ArrayList<Catalogue>(0);
        }
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    /**
     * @return the catalogues
     */
    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "CATPROD",
    joinColumns = {
        @JoinColumn(name = "PRODUCTID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "CATEGORYID")
    })
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the books to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "CONNPRODUCT",
    joinColumns = {
        @JoinColumn(name = "SUBPRODUCTID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "PARENTPRODUCTID")
    })
    public List<Product> getParentproducts() {
        return parentproducts;
    }

    public void setParentproducts(List<Product> parentproducts) {
        this.parentproducts = parentproducts;
    }

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "CONNPRODUCT",
    joinColumns = {
        @JoinColumn(name = "PARENTPRODUCTID")
    },
    inverseJoinColumns = {
        @JoinColumn(name = "SUBPRODUCTID")
    })
    public List<Product> getSubproducts() {
        return subproducts;
    }

    public void setSubproducts(List<Product> subproducts) {
        this.subproducts = subproducts;
    }

    
    
    @Transient
    public List<Productspecification> getDimesnionProductSpecifications() {
        List<Productspecification> retVal = new ArrayList<Productspecification>(0);
        if (productspecifications!=null && productspecifications.size()>0){
            for (int i = 0; i < productspecifications.size(); i++) {
                Productspecification ps = productspecifications.get(i);
                if (ps.getSpecification().getDimension().equals(BigDecimal.ONE)) {
                    retVal.add(ps);
                }                
            }
        }
        return retVal;
    }
    
    @Transient
    public List<Productspecification> getColorProductSpecifications() {
        List<Productspecification> retVal = new ArrayList<Productspecification>(0);
        if (productspecifications!=null && productspecifications.size()>0){
            for (int i = 0; i < productspecifications.size(); i++) {
                Productspecification ps = productspecifications.get(i);
                if (ps.getSpecification().getColor().equals(BigDecimal.ONE)) {
                    retVal.add(ps);
                }                
            }
        }
        return retVal;
    }
    
    
     @Transient
    public List<Productspecification> getOtherProductSpecifications() {
        List<Productspecification> retVal = new ArrayList<Productspecification>(0);
        if (productspecifications!=null && productspecifications.size()>0){
            for (int i = 0; i < productspecifications.size(); i++) {
                Productspecification ps = productspecifications.get(i);
                if (!ps.getSpecification().getDimension().equals(BigDecimal.ONE)) {
                    retVal.add(ps);
                }                
            }
        }
        return retVal;
    }
    
    @Transient
    public Company getFirstCompany() {
        if (companyproducts!=null && companyproducts.size()>0) {
            return companyproducts.get(0).getCompany();
        } else {
            return null;
        }
    }
    
    
    
    @Transient
    public Category getFirstCategory() {
        if (categories!=null && categories.size()>0) {
            return categories.get(0);
        } else {
            return null;
        }
    }
    
    
    @Transient
    public Price getFirstPrice() {
        if (prices!=null && prices.size()>0) {
            return prices.get(0);
        } else {
            return null;
        }        
    }
    
    @Transient
    public List<Company> getOrderedCompanies() {
        orderedCompanies = new ArrayList<Company>(0);
        if (companyproducts != null) {
            for (int i = 0; i < companyproducts.size(); i++) {
                Companyproduct companyproduct = companyproducts.get(i);
                Company company = companyproduct.getCompany();
                if (company.getActive().equals(BigDecimal.ONE)) {
                    orderedCompanies.add(company);
                }
            }

            Collections.sort(orderedCompanies, new Comparator<Company>() {
                public int compare(Company one, Company other) {
                    return one.getName().compareTo(other.getName());
                }
            });
            return orderedCompanies;
        } else {
            return new ArrayList<Company>(0);
        }
    }

    public void setOrderedCompanies(List<Company> orderedCompanies) {
        this.orderedCompanies = orderedCompanies;
    }

    @Transient
    public List<Productline> getOrderedProductlines() {
        orderedProductlines = new ArrayList<Productline>(0);
        if (productlineproducts != null) {
            for (int i = 0; i < productlineproducts.size(); i++) {
                Productlineproduct productlineproduct = productlineproducts.get(i);
                Productline productline = productlineproduct.getProductline();
                if (productline.getActive().equals(BigDecimal.ONE)) {
                    orderedProductlines.add(productline);
                }
            }
            Collections.sort(orderedProductlines, new Comparator<Productline>() {
                public int compare(Productline one, Productline other) {
                    return one.getName().compareTo(other.getName());
                }
            });
            return orderedProductlines;
        } else {
            return new ArrayList<Productline>(0);
        }
    }

    public void setOrderedProductlines(List<Productline> orderedProductlines) {
        this.orderedProductlines = orderedProductlines;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Product)) {
            return false;
        }

        Product compare = (Product) obj;

        return compare.productid.equals(this.productid);
    }

    @Override
    public int hashCode() {
        return productid != null ? this.getClass().hashCode() + productid.hashCode() : super.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + productid + ", name=" + getName() + "}";
    }
}