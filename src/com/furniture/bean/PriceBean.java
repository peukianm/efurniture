/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.entities.Catalogue;
import com.furniture.entities.Company;
import com.furniture.entities.Currency;
import com.furniture.entities.Price;
import com.furniture.entities.Product;
import com.furniture.entities.Users;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author peukianm
 */
@ManagedBean(name = "priceBean")
@ViewScoped
public class PriceBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private Product selectedProductByName;
    private Product selectedProduct;
    private Currency currency;
    private Date priceDate;
    private List<Catalogue> catalogues = new ArrayList<Catalogue>(0);
    private List<Company> companies = new ArrayList<Company>(0);
    private Catalogue selectedCatalogue;
    private Price price = new Price();
    private List<Price> prices = new ArrayList<Price>(0);
    private Double amount;
    private Double discount;
    private Double initialAmount;
    private Company selectedCompany;

    @PostConstruct
    public void init() {
        Users user = sessionBean.getUsers();
        if (!user.getRole().getRoleid().equals(BigDecimal.ONE) && user.getCompany()!=null) {
            selectedCompany = user.getCompany();
            catalogues = selectedCompany.getCatalogues();               
            Collections.sort(catalogues, new Comparator<Catalogue>() {
                public int compare(Catalogue one, Catalogue other) {
                    return one.getName().compareTo(other.getName());
                }
            });
        }                
    }

    public void reset() {
        selectedProductByName = null;
        selectedProduct = null;
        currency = null;
        priceDate = null;
        catalogues = new ArrayList<Catalogue>(0);
        selectedCatalogue = null;
        price = new Price();
        prices = new ArrayList<Price>(0);
        amount = null;
        discount = null;
        initialAmount = null;
        selectedCompany = null;
        companies = new ArrayList<Company>(0);
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    
    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Company getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(Company selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    public List<Catalogue> getCatalogues() {
        return catalogues;
    }

    public void setCatalogues(List<Catalogue> catalogues) {
        this.catalogues = catalogues;
    }

    public Catalogue getSelectedCatalogue() {
        return selectedCatalogue;
    }

    public void setSelectedCatalogue(Catalogue selectedCatalogue) {
        this.selectedCatalogue = selectedCatalogue;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public Product getSelectedProductByName() {
        return selectedProductByName;
    }

    public void setSelectedProductByName(Product selectedProductByName) {
        this.selectedProductByName = selectedProductByName;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
