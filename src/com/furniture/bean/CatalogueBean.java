/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.dao.CatalogueDAO;
import com.furniture.dao.CompanyproductDAO;
import com.furniture.dao.ProductlineDAO;
import com.furniture.entities.Catalogue;
import com.furniture.entities.Company;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

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
    private DualListModel<Productline> productLineList = new DualListModel<Productline>(new ArrayList<Productline>(0), new ArrayList<Productline>(0));
    private DualListModel<Product> productList= new DualListModel<Product>(new ArrayList<Product>(0), new ArrayList<Product>(0));
    
    private DualListModel<Productline> updateProductLineList = new DualListModel<Productline>(new ArrayList<Productline>(0), new ArrayList<Productline>(0));
    private DualListModel<Product> updateProductList = new DualListModel<Product>(new ArrayList<Product>(0), new ArrayList<Product>(0));
    
    
    
    @PostConstruct
    public void init() {
        System.out.println("INITIALIZING IN Catalogue Bean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111");
        if (company==null)
            company=sessionBean.getUsers().getCompany();  
        initializeValues(company);
    }

    
    
    public void initializeValues(Company company) {        
        CatalogueDAO da = new CatalogueDAO();
        catalogues = da.getCompanyCatalogues(company);
        
        for (int i = 0; i < catalogues.size(); i++) {
            Catalogue catalogue1 = catalogues.get(i);                       
        }
        
                
        ProductlineDAO dao = new ProductlineDAO();
        productlines = dao.getCompanyProductlines(company);    
        productLineList = new DualListModel<Productline>(productlines, new ArrayList<Productline>(0));                
        
        CompanyproductDAO dao1 = new CompanyproductDAO();
        List<Product> pr = dao1.getCompanyProducts(company, Boolean.TRUE);
        Collections.sort(pr, new Comparator<Product>() {
        public int compare(Product one, Product other) {
                return one.getName().compareTo(other.getName());
            }
        });                        
        productList = new DualListModel<Product>(pr, new ArrayList<Product>(0) );
        
        
        
    }
    
    
    @PreDestroy
    public void reset() {
    }

    public DualListModel<Productline> getUpdateProductLineList() {
        return updateProductLineList;
    }

    public void setUpdateProductLineList(DualListModel<Productline> updateProductLineList) {
        this.updateProductLineList = updateProductLineList;
    }

    public DualListModel<Product> getUpdateProductList() {
        return updateProductList;
    }

    public void setUpdateProductList(DualListModel<Product> updateProductList) {
        this.updateProductList = updateProductList;
    }

    
    
    
    public DualListModel<Productline> getProductLineList() {
        return productLineList;
    }

    public void setProductLineList(DualListModel<Productline> productLineList) {
        this.productLineList = productLineList;
    }

    public DualListModel<Product> getProductList() {
        return productList;
    }

    public void setProductList(DualListModel<Product> productList) {
        this.productList = productList;
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
