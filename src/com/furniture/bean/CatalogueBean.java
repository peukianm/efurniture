/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.entities.Catalogue;
import com.furniture.entities.Company;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class CatalogueBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private List<Catalogue> catalogues = new ArrayList<Catalogue>(0);
    private List<Productline> productlines = new ArrayList<Productline>(0);
    private List<Product> products = new ArrayList<Product>(0);
    private Catalogue catalogue;
    private Productline productline;
    private Company company;

    @PostConstruct
    public void init() {
        System.out.println("INITIALIZING IN Catalogue Bean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111");
        company=sessionBean.getUsers().getCompany();
        catalogues = company.getCatalogues();
    }

    @PreDestroy
    public void reset() {
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    public List<Productline> getProductlines() {
        return productlines;
    }

    public void setProductlines(List<Productline> productlines) {
        this.productlines = productlines;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public Productline getProductline() {
        return productline;
    }

    public void setProductline(Productline productline) {
        this.productline = productline;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
