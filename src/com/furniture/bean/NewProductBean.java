/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.entities.Company; 
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Imageproduct;
import com.furniture.entities.Item;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import com.furniture.entities.Productspecification;
import com.furniture.entities.Productvalue;
import com.furniture.entities.Specification;
import com.furniture.entities.Specificationcategory;
import com.furniture.entities.Specificationvalue;
import com.furniture.entities.Users;
import com.furniture.entities.Videoproduct;
import com.furniture.util.FacesUtils;  
import com.furniture.util.FurnitureUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *  
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class NewProductBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private static final Logger logger = Logger.getLogger(NewProductBean.class);
    private Product newProduct = new Product();
    private List<Company> selectedCompanies = new ArrayList<Company>(0);
    private List<Productline> productLines = new ArrayList<Productline>(0);
    private List<Productline> selectedProductLines = new ArrayList<Productline>(0);
    private List<Imageproduct> images = new ArrayList<Imageproduct>(0);
    private Imageproduct newImage = new Imageproduct();
    private List<Videoproduct> videos = new ArrayList<Videoproduct>(0);
    private Videoproduct newVideo = new Videoproduct();
    private List<Specification> specifications = new ArrayList<Specification>(0);
    private List<Specification> selectedSpecification = new ArrayList<Specification>(0);
    private List<Specificationcategory> selectedSpecificationCat = new ArrayList<Specificationcategory>(0);
    private Specification specification = new Specification();    
    
    //Value attributes  
    private List<Specificationvalue> svalues = new ArrayList<Specificationvalue>(0);
    private List<Specificationvalue> selectedSvalues = new ArrayList<Specificationvalue>(0);
    private Specificationvalue selectedSvalue;
    private String svalue = new String();
    private List<Productspecification> productSpecifications = new ArrayList<Productspecification>(0);
    private List<Productvalue> productValues = new ArrayList<Productvalue>(0);
    private Productspecification productSpecification;
    private List<Product> parentProductList = new ArrayList<Product>(0);
    private List<Item> items = new ArrayList<Item>(0);
    private Item selectedItem;
    
   
    @PostConstruct
    public void init() {
        reset();
        System.out.println("ARXIKO INITIALIZATION NEWPRODUCT BEAN!!!!!!!!!!!!!!!");
        Users user = sessionBean.getUsers();

        Company company = user.getCompany();
        if (company != null) {
            selectedCompanies.add(company);
            Companyproduct companyProduct = new Companyproduct();
            companyProduct.setCompany(company);
            newProduct.getCompanyproducts().add(companyProduct);
            newProduct.setSubproduct(BigDecimal.ZERO);
            productLines = FurnitureUtil.getProductLineFromCompany(company);
        }
    } 

    //PreDestroy
    public void reset() {
        newProduct = new Product();
        //selectedCompanies = new ArrayList<Company>(0);
        productLines = new ArrayList<Productline>(0);
        selectedProductLines = new ArrayList<Productline>(0);

        images = new ArrayList<Imageproduct>(0);
        newImage = new Imageproduct();
    }

    
    public List<Imageproduct> getImages() {
        return images;
    }

    public void setImages(List<Imageproduct> images) {
        this.images = images;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(Product newProduct) {
        this.newProduct = newProduct;
    }

//    public List<Companycatalogue> getCatalogues() {
//        return catalogues;
//    }
//
//    public void setCatalogues(List<Companycatalogue> catalogues) {
//        this.catalogues = catalogues;
//    }
//
//    public List<Companycatalogue> getSelectedCatalogues() {
//        return selectedCatalogues;
//    }
//
//    public void setSelectedCatalogues(List<Companycatalogue> selectedCatalogues) {
//        this.selectedCatalogues = selectedCatalogues;
//    }
//
//    public List<Catalogueproductline> getProductLines() {
//        return productLines;
//    }
//
//    public void setProductLines(List<Catalogueproductline> productLines) {
//        this.productLines = productLines;
//    }
//
//    public List<Catalogueproductline> getSelectedProductLines() {
//        return selectedProductLines;
//    }
//
//    public void setSelectedProductLines(List<Catalogueproductline> selectedProductLines) {
//        this.selectedProductLines = selectedProductLines;
//    }
    public List<Company> getSelectedCompanies() {
        return selectedCompanies;
    }

    public void setSelectedCompanies(List<Company> selectedCompanies) {
        this.selectedCompanies = selectedCompanies;
    }

    public List<Productline> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<Productline> productLines) {
        this.productLines = productLines;
    }

    public List<Productline> getSelectedProductLines() {
        return selectedProductLines;
    }

    public void setSelectedProductLines(List<Productline> selectedProductLines) {
        this.selectedProductLines = selectedProductLines;
    }

//    private StreamedContent imagem = new DefaultStreamedContent();
//    public StreamedContent getImagem() {
//        return imagem;
//    }
//
//    public void setImagem(StreamedContent imagem) {
//        this.imagem = imagem;
//    }
//    
//    
//    
    public Imageproduct getNewImage() {
        return newImage;
    }

    public void setNewImage(Imageproduct newImage) {
        this.newImage = newImage;
    }
    
     public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
        
    
    public List<Product> getParentProductList() {
        return parentProductList;
    }

    public void setParentProductList(List<Product> parentProductList) {
        this.parentProductList = parentProductList;
    }

    public Productspecification getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(Productspecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getSvalue() {
        return svalue;
    }

    public void setSvalue(String svalue) {
        this.svalue = svalue;
    }

    public Specificationvalue getSelectedSvalue() {
        return selectedSvalue;
    }

    public void setSelectedSvalue(Specificationvalue selectedSvalue) {
        this.selectedSvalue = selectedSvalue;
    }

    public List<Productspecification> getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(List<Productspecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public List<Productvalue> getProductValues() {
        return productValues;
    }

    public void setProductValues(List<Productvalue> productValues) {
        this.productValues = productValues;
    }

    public List<Specificationvalue> getSvalues() {
        return svalues;
    }

    public void setSvalues(List<Specificationvalue> svalues) {
        this.svalues = svalues;
    }

    public List<Specificationvalue> getSelectedSvalues() {
        return selectedSvalues;
    }

    public void setSelectedSvalues(List<Specificationvalue> selectedSvalues) {
        this.selectedSvalues = selectedSvalues;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public List<Specification> getSelectedSpecification() {
        return selectedSpecification;
    }
 
    public void setSelectedSpecification(List<Specification> selectedSpecification) {
        this.selectedSpecification = selectedSpecification;
    }

    public List<Specificationcategory> getSelectedSpecificationCat() {
        return selectedSpecificationCat;
    } 

    public void setSelectedSpecificationCat(List<Specificationcategory> selectedSpecificationCat) {
        this.selectedSpecificationCat = selectedSpecificationCat;
    }

    public List<Videoproduct> getVideos() {
        return videos;
    }

    public void setVideos(List<Videoproduct> videos) {
        this.videos = videos;
    }

    public Videoproduct getNewVideo() {
        return newVideo;
    }

    public void setNewVideo(Videoproduct newVideo) {
        this.newVideo = newVideo;
    }
    
    

}
