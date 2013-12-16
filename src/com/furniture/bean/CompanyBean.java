/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.entities.Address;
import com.furniture.entities.Category;
import com.furniture.entities.Company;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class CompanyBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private List<Company> companies = new ArrayList<Company>(0);
    private Company company;
    private List<Category> selectedCategories = new ArrayList<Category>(0);
    private Boolean isInsert = false;
    private Address address;

    @PostConstruct
    public void init() {        
        System.out.println("INITIALIZING IN Company Bean!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111");

    }

    @PreDestroy
    public void reset() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(Boolean isInsert) {
        this.isInsert = isInsert;
    }

    public List<Category> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<Category> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}
