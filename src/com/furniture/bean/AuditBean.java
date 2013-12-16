/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;
       
import com.furniture.entities.Action;
import com.furniture.entities.Auditing;
import com.furniture.entities.Company;
import com.furniture.entities.Product;
import com.furniture.entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class AuditBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    private Date searchFromActionDate;
    private Date searchToActionDate;
    private Users searchUser;
    private Users selectUser;
    private Action searchAction;
    private Product searchProduct;
    private Product selectProduct;
    private Company searchCompany;
    
    
    private List<Auditing> audits = new ArrayList<Auditing>(0);

    @PostConstruct
    public void init() {
    }

    @PreDestroy
    public void reset() {

        searchFromActionDate = null;
        searchToActionDate = null;
        searchUser = null;
        selectUser = null;

        searchAction = null;
        searchProduct = null;
        selectProduct = null;
        searchCompany = null;
        audits = new ArrayList<Auditing>(0);

    }

    public Company getSearchCompany() {
        return searchCompany;
    }

    public void setSearchCompany(Company searchCompany) {
        this.searchCompany = searchCompany;
    }

    
    
    public Product getSelectProduct() {
        return selectProduct;
    }

    public void setSelectProduct(Product selectProduct) {
        this.selectProduct = selectProduct;
    }

    public Users getSelectUser() {
        return selectUser;
    }

    public void setSelectUser(Users selectUser) {
        this.selectUser = selectUser;
    }

    public Date getSearchFromActionDate() {
        return searchFromActionDate;
    }

    public void setSearchFromActionDate(Date searchFromActionDate) {
        this.searchFromActionDate = searchFromActionDate;
    }

    public Date getSearchToActionDate() {
        return searchToActionDate;
    }

    public void setSearchToActionDate(Date searchToActionDate) {
        this.searchToActionDate = searchToActionDate;
    }

    public Users getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(Users searchUser) {
        this.searchUser = searchUser;
    }

    public Action getSearchAction() {
        return searchAction;
    }

    public void setSearchAction(Action searchAction) {
        this.searchAction = searchAction;
    }

    public Product getSearchProduct() {
        return searchProduct;
    }

    public void setSearchProduct(Product searchProduct) {
        this.searchProduct = searchProduct;
    }

    public List<Auditing> getAudits() {
        return audits;
    }

    public void setAudits(List<Auditing> audits) {
        this.audits = audits;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}
