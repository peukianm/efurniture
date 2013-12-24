/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.action;

import com.furniture.bean.CatalogueBean;
import com.furniture.bean.CompanyBean;
import com.furniture.bean.ErrorBean;
import com.furniture.bean.NewProductBean;
import com.furniture.bean.ProductSearchBean;
import com.furniture.bean.SessionBean;
import com.furniture.dao.CatalogueDAO;
import com.furniture.dao.CompanyproductDAO;
import com.furniture.dao.ProductlineDAO;
import com.furniture.entities.Catalogue;
import com.furniture.entities.Company;
import com.furniture.entities.Product;
import com.furniture.entities.Productline;
import com.furniture.util.FacesUtils;
import com.furniture.util.MessageBundleLoader;
import com.furniture.util.PersistenceHelper;
import com.furniture.util.PersistenceUtil;
import com.furniture.util.SystemParameters;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author peukianm
 */
public class CatalogueAction {

    private static final Logger logger = Logger.getLogger(CatalogueAction.class);
    private SessionBean sessionBean = (SessionBean) FacesUtils.getManagedBean("sessionBean");
    @EJB
    private PersistenceUtil persistenceUtil;
    @EJB
    private PersistenceHelper persistenceHelper;
    private CatalogueBean catalogueBean = FacesUtils.getManagedBeanJSF2("catalogueBean");

    public String adminCatalogue() {
        try {
            catalogueBean.reset();
            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_ADMIN_CATALOGUE"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("adminCatalogue"));
            return "adminCatalogue?faces-redirect=true ";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void handleCompanySelect() {
        try {
            Company company = catalogueBean.getCompany();
            catalogueBean.setCompany(company);
            catalogueBean.init();
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            FacesUtils.goError(ex, logger, sessionBean.getUsers(), sessionBean.getErrorMsgKey());
        }

    }

    public void goInsertCatalogue() {
        try {
            Catalogue newCatalogue = new Catalogue();
            newCatalogue.setActive(BigDecimal.ONE);  
            List<Company> companies = new ArrayList<Company>(0);
            companies.add(catalogueBean.getCompany());
            newCatalogue.setCompanies(companies);
            catalogueBean.setCatalogue(newCatalogue); 
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void goUpdateCatalogue() {
        try {
            ProductlineDAO dao = new ProductlineDAO();
            List<Productline> pls = dao.getCompanyProductlines(catalogueBean.getCompany());
            
            CatalogueDAO dao1 = new CatalogueDAO();
            System.out.println(dao1.getCatalogueProductlines(catalogueBean.getCatalogue()));
            System.out.println(catalogueBean.getCatalogue().getProductlines());
            
            
            pls.removeAll(catalogueBean.getCatalogue().getProductlines());
            catalogueBean.setUpdateProductLineList(new DualListModel<Productline>(pls, catalogueBean.getCatalogue().getProductlines()));
            FacesUtils.callRequestContext("updateCatalogueDialogWidget.show()");
            FacesUtils.updateHTMLComponnetWIthJS("updateCataloguePanelID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void insertCatalogue() {

        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            Catalogue catalogue = catalogueBean.getCatalogue();
            catalogue.setProductlines(catalogueBean.getProductLineList().getTarget());
            persistenceHelper.create(catalogue);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTCATALOGUE")),"Catalogue "+catalogue.getName()+" inserted");
            userTransaction.commit();

            catalogueBean.initializeValues(catalogueBean.getCompany());
            FacesUtils.callRequestContext("createCatalogueDialogWidget.hide()");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("newCatalogueInserted"));
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

     public void removeCatalogue() {

        UserTransaction userTransaction = null;
        try {
            Catalogue catalogue = catalogueBean.getCatalogue();            
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();            
            persistenceHelper.remove(catalogue);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_DELETECATALOGUE")), "Catalogue "+catalogue.getName()+" removed");
            userTransaction.commit();
            catalogueBean.initializeValues(catalogueBean.getCompany());
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("catalogueDeleted"));
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    public void updateCatalogue() {
        UserTransaction userTransaction = null;
        try {
            Catalogue catalogue = catalogueBean.getCatalogue();                       
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            catalogue.setProductlines(catalogueBean.getUpdateProductLineList().getTarget());
            catalogue = persistenceHelper.editPersist(catalogue);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATECATALOGUE")), "Catalogue "+catalogue.getName()+" updated");
            userTransaction.commit(); 
            
            catalogueBean.initializeValues(catalogueBean.getCompany());
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("catalogueUpdated"));
            FacesUtils.callRequestContext("updateCatalogueDialogWidget.hide()");
            
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
    
    
        public void goInsertProductline() {
        try {            
            
            Productline newProductline = new Productline();
            newProductline.setActive(BigDecimal.ONE);
            catalogueBean.setProductline(newProductline);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void goUpdateProductline() {
        try {
            Company company = catalogueBean.getCompany();
            Productline productline = catalogueBean.getProductline();            
            CompanyproductDAO dao1 = new CompanyproductDAO();
            List<Product> pr = dao1.getCompanyProducts(company, Boolean.TRUE);
            Collections.sort(pr, new Comparator<Product>() {
                public int compare(Product one, Product other) {
                    return one.getName().compareTo(other.getName());
                }
            });            
                 
            pr.removeAll(productline.getProducts());
            catalogueBean.setUpdateProductList(new DualListModel<Product>( pr ,  productline.getProducts() ));
            FacesUtils.callRequestContext("updateProductlineDialogWidget.show()"); 
            FacesUtils.updateHTMLComponnetWIthJS("updateProductlinePanelID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
    public void insertProductline() {

        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            Productline productlien = catalogueBean.getProductline();
            
            List<Product> products = catalogueBean.getProductList().getTarget();
            List<Company> companies = new ArrayList<Company>(0);            
            companies.add(catalogueBean.getCompany());
            productlien.setProducts(products);
            productlien.setCompanies(companies);            
            persistenceHelper.create(productlien);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTLINE")), "Productline "+productlien.getName()+" inserted");
            userTransaction.commit();
            
            catalogueBean.initializeValues(catalogueBean.getCompany());
            
            FacesUtils.callRequestContext("createProductlineDialogWidget.hide()");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("newProductlineInserted"));

        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
     public void updateProductline() {
        UserTransaction userTransaction = null;
        try {
            Productline productline = catalogueBean.getProductline();                    
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            productline.setProducts(catalogueBean.getUpdateProductList().getTarget());
            persistenceHelper.editPersist(productline);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATELINE")), "Productline "+productline.getName()+" updated");
            userTransaction.commit();
            
            catalogueBean.initializeValues(catalogueBean.getCompany());
            
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productlineUpdated"));
            FacesUtils.callRequestContext("updateProductlineDialogWidget.hide()");
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
     
     
      public void removeProductline() {

        UserTransaction userTransaction = null;
        try {
            Productline productline = catalogueBean.getProductline();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();            
            persistenceHelper.remove(productline);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_DELETELINE")), "Productline "+productline.getName()+" deleted");
            userTransaction.commit();
            catalogueBean.initializeValues(catalogueBean.getCompany());
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("productlineDeleted"));
            FacesUtils.updateHTMLComponnetWIthJS("");
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
    
      
      
      

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                          TEMPLATES!!!!!!!!!!!!!!
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void test() {

        try {
            ProductSearchBean productSearchBean = (ProductSearchBean) FacesUtils.getManagedBean("productSearchBean");
            Product product = productSearchBean.getSelectedProduct();
            productSearchBean.setSelectedProduct(product);
            FacesUtils.updateHTMLComponnetWIthJS("confDeleteProductDlgID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void templateNoReturnAction() {

        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");

        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String templateReturnAction() {

        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");


            return "main?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String templateUpdateDBAction() {

        UserTransaction userTransaction = null;
        try {
            NewProductBean newProductBean = (NewProductBean) FacesUtils.getManagedBean("newProductBean");
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();


            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTPRODUCT")), null, null, null, null, null, null);
            userTransaction.commit();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
            return "main?faces-redirect=true";
        } catch (Exception e) {
            try {
                userTransaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void goError(Exception ex) {
        try {
            logger.error("-----------AN ERROR HAPPENED !!!! -------------------- : " + ex.toString());
            if (sessionBean.getUsers() != null) {
                logger.error("User=" + sessionBean.getUsers().getUsername());
            }
            logger.error("Cause=" + ex.getCause());
            logger.error("Class=" + ex.getClass());
            logger.error("Message=" + ex.getLocalizedMessage());
            logger.error(ex, ex);
            logger.error("--------------------- END OF ERROR --------------------------------------------------------\n\n");

            ErrorBean errorBean = (ErrorBean) FacesUtils.getManagedBean("errorBean");
            errorBean.reset();
            errorBean.setErrorMSG(MessageBundleLoader.getMessage(sessionBean.getErrorMsgKey()));
            //FacesUtils.redirectAJAX("./templates/error.jsf?faces-redirect=true");
            FacesUtils.redirectAJAX(FacesUtils.getContextPath() + "/common/error.jsf?faces-redirect=true");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
