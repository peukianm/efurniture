/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */  
package com.furniture.bean;

import com.furniture.dao.CatalogueDAO; 
import com.furniture.dao.ProductcategoryDAO;
import com.furniture.dao.ProductlineDAO;
import com.furniture.entities.Catalogue;
import com.furniture.entities.Company;
import com.furniture.entities.Product;  
import com.furniture.entities.Productcategory;
import com.furniture.entities.Productline;
import com.furniture.entities.Users;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
  
/**
 *
 * @author peukianm
 */   
@ManagedBean
@ViewScoped
public class ProductSearchBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private Company searchByCompany;
    private Productline searchByProductline;
    private Catalogue searchByCatalogue;
    private Productcategory searchByProductcategory;
    private String searchByName;
    private List<Company> companies;
    private List<Productline> productLines;
    private List<Productcategory> productCategories;
    private List<Catalogue> catalogues;
    private Product selectedProduct;
    private transient DataModel<Product> productsModel;
    private List<Product> products;

    @PostConstruct
    public void init() {
//        Users user = sessionBean.getUsers();     
//        Company company = user.getCompany();

        System.out.println("INIT IN SEARCH PRODUCT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111");
        ProductlineDAO pldao = new ProductlineDAO();
        productLines = pldao.findByProperty("active", BigDecimal.ONE);
        CatalogueDAO catdao = new CatalogueDAO();
        catalogues = catdao.findByProperty("active", BigDecimal.ONE);
        ProductcategoryDAO pcdao = new ProductcategoryDAO();
        productCategories = pcdao.findByProperty("active", BigDecimal.ONE);

    }

    public void reset() {
        searchByCompany = null;   
        searchByProductline = null;
        searchByCatalogue = null;
        searchByProductcategory = null;
        searchByName = null;
        selectedProduct = null;
        productsModel = null;
        products = null;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public DataModel<Product> getProductsModel() {
        return productsModel;
    }

    public void setProductsModel(DataModel<Product> productsModel) {
        this.productsModel = productsModel;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Company getSearchByCompany() {
        return searchByCompany;
    }

    public void setSearchByCompany(Company searchByCompany) {
        this.searchByCompany = searchByCompany;
    }

    public Productline getSearchByProductline() {
        return searchByProductline;
    }

    public void setSearchByProductline(Productline searchByProductline) {
        this.searchByProductline = searchByProductline;
    }

    public Catalogue getSearchByCatalogue() {
        return searchByCatalogue;
    }

    public void setSearchByCatalogue(Catalogue searchByCatalogue) {
        this.searchByCatalogue = searchByCatalogue;
    }

    public Productcategory getSearchByProductcategory() {
        return searchByProductcategory;
    }

    public void setSearchByProductcategory(Productcategory searchByProductcategory) {
        this.searchByProductcategory = searchByProductcategory;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Productline> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<Productline> productLines) {
        this.productLines = productLines;
    }

    public List<Productcategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<Productcategory> productCategories) {
        this.productCategories = productCategories;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }
}
