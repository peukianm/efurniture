/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.dao.ActionDAO;
import com.furniture.dao.CompanyDAO;
import com.furniture.dao.CurrencyDAO;
import com.furniture.dao.ItemDAO;
import com.furniture.dao.MeasurmentDAO;
import com.furniture.dao.ProductcategoryDAO;
import com.furniture.dao.RoleDAO;
import com.furniture.dao.SpecificationDAO;
import com.furniture.dao.SpecificationcategoryDAO;
import com.furniture.dao.SvalueDAO;
import com.furniture.entities.Action;
import com.furniture.entities.Category;
import com.furniture.entities.Company;
import com.furniture.entities.Companyproduct;
import com.furniture.entities.Currency;
import com.furniture.entities.Item;
import com.furniture.entities.Measurment;
import com.furniture.entities.Productcategory;
import com.furniture.entities.Role;
import com.furniture.entities.Specification;
import com.furniture.entities.Specificationcategory;
import com.furniture.entities.Svalue;
import com.furniture.util.FurnitureUtil;
import com.furniture.util.SystemParameters;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.TreeNode;

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

    
   List<Role> roles;

    public List<Role> getRoles() {
        if (roles == null) {
            RoleDAO dao = new RoleDAO();
            roles = dao.findAll();
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    
    
    public void resetCompanies() {
        this.companies = null;
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

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
    List<Specification> specifications;
    public List<Specification> getSpecifications() {
        if (specifications == null) {
            SpecificationDAO dao = new SpecificationDAO();
            specifications = dao.findByProperty("active", BigDecimal.ONE);
        }
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }
    
    
    List<Svalue> svalues;
    public List<Svalue> getSvalues() {
        if (svalues == null) {
            SvalueDAO dao = new SvalueDAO();
            svalues = dao.findAll();
        }
        return svalues;
    }

    public void setSvalues(List<Svalue> svalues) {
        this.svalues = svalues;
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
    
    
    List<Action> actions;
    public List<Action> getActions() {
        if (actions == null) {
            ActionDAO dao = new ActionDAO();
            actions = dao.findAll();
        }
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
    
    
    List<Specification> dimensionSpecifications;
    public List<Specification> getDimensionSpecifications() {
        if (dimensionSpecifications == null) {
            SpecificationDAO dao = new SpecificationDAO();
            dimensionSpecifications = dao.getDimensionSpecifications(true);
        }
        return dimensionSpecifications;
    }

    public void setDimensionSpecifications(List<Specification> dimensionSpecifications) {
        this.dimensionSpecifications = dimensionSpecifications;
    }
    
    
    List<Measurment> measurments;
    public List<Measurment> getmeasurments() {
        if (measurments == null) {
            MeasurmentDAO dao = new MeasurmentDAO();
            measurments = dao.findByProperty("active", BigDecimal.ONE);
        }
        return measurments;
    }

    public void setMeasurments(List<Measurment> measurments) {
        this.measurments = measurments;
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
            }

        }
        return companyproducts;
    }

    public void setCompanyproducts(List<Companyproduct> companyproducts) {
        this.companyproducts = companyproducts;
    }
    
    
    private TreeNode root;
    public TreeNode getRoot() {
        CompanyDAO dao = new CompanyDAO();
        List<Category> categories = dao.getAllRootCategories(true);
        root = FurnitureUtil.getCategoriesTree(categories);
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    
    private List<Category> rootCategories;
    public List<Category> getRootCategories() {
        CompanyDAO dao = new CompanyDAO();
        rootCategories = dao.getAllRootCategories(true);
        return rootCategories;
    }

    public void setRootCategories(List<Category> rootCategories) {
        this.rootCategories = rootCategories;
    }
}
