/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.dao.CompanyDAO;
import com.furniture.dao.CurrencyDAO;
import com.furniture.dao.ItemDAO;
import com.furniture.dao.ProductcategoryDAO;
import com.furniture.dao.SpecificationcategoryDAO;
import com.furniture.entities.Company;
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Currency;
import com.furniture.entities.Item;
import com.furniture.entities.Productcategory;
import com.furniture.entities.Specificationcategory;
import com.furniture.util.SystemParameters;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ApplicationScoped
public class ApplicationBean implements Serializable {

    String propertyValue;

    public String getPropertyValue(String key) {
        propertyValue = SystemParameters.getInstance().getProperty(key);
        return propertyValue;
    }
    List<Specificationcategory> specificationCategories;

    public List<Specificationcategory> getSpecificationCategories() {
        if (specificationCategories == null) {
            SpecificationcategoryDAO dao = new SpecificationcategoryDAO();
            specificationCategories = dao.findByProperty("active", BigDecimal.ONE);
        }
        return specificationCategories;
    }

    public void setSpecificationCategories(List<Specificationcategory> specificationCategories) {
        this.specificationCategories = specificationCategories;
    }
    List<Company> companies;

    public List<Company> getCompanies() {
        if (companies == null) {
            CompanyDAO dao = new CompanyDAO();
            companies = dao.findByProperty("active", BigDecimal.ONE);
        }
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    List<Productcategory> productcategories;

    public List<Productcategory> getProductcategories() {
        if (productcategories == null) {
            ProductcategoryDAO dao = new ProductcategoryDAO();
            productcategories = dao.findByProperty("active", BigDecimal.ONE);
        }
        return productcategories;
    }

    public void setProductcategories(List<Productcategory> Productcategories) {
        this.productcategories = productcategories;
    }
    List<Item> items;

    public List<Item> getItems() {
        if (items == null) {
            ItemDAO dao = new ItemDAO();
            items = dao.findByProperty("active", BigDecimal.ONE);
        }
        return items;
    }

    public void setItems(List<Item> Items) {
        this.items = items;
    }
    List<Currency> currencies;

    public List<Currency> getCurrencies() {
        if (currencies == null) {
            CurrencyDAO dao = new CurrencyDAO();
            currencies = dao.findAll();
        }
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
    List<Companyproduct> companyproducts = new ArrayList<Companyproduct>(0);

    public List<Companyproduct> getCompanyproducts() {
        if (companyproducts.size() == 0) {
            CompanyDAO dao = new CompanyDAO();
            List<Company> companies = dao.findByProperty("active", BigDecimal.ONE);
            for (int i = 0; i < companies.size(); i++) {
                Company company = companies.get(i);
                Companyproduct companyproduct = new Companyproduct();
                companyproduct.setCompany(company);
                companyproducts.add(companyproduct);
                System.out.println(companyproduct);
            }

        }
        return companyproducts;
    }

    public void setCompanyproducts(List<Companyproduct> companyproducts) {
        this.companyproducts = companyproducts;
    }
}
