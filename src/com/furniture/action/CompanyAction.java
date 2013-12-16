/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.action;

import com.furniture.bean.ApplicationBean;
import com.furniture.bean.CompanyBean;
import com.furniture.bean.ErrorBean;
import com.furniture.bean.NewProductBean;
import com.furniture.bean.ProductSearchBean;
import com.furniture.bean.SessionBean;
import com.furniture.dao.CompanyproductDAO;
import com.furniture.entities.Address;
import com.furniture.entities.Category;
import com.furniture.entities.Company;
import com.furniture.entities.Product;
import com.furniture.util.FacesUtils;
import com.furniture.util.MessageBundleLoader;
import com.furniture.util.PersistenceHelper;
import com.furniture.util.PersistenceUtil;
import com.furniture.util.SystemParameters;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author peukianm
 */
public class CompanyAction {

    private static final Logger logger = Logger.getLogger(CompanyAction.class);
    private SessionBean sessionBean = (SessionBean) FacesUtils.getManagedBean("sessionBean");
    @EJB
    private PersistenceUtil persistenceUtil;
    @EJB
    private PersistenceHelper persistenceHelper;
    private CompanyBean companyBean = FacesUtils.getManagedBeanJSF2("companyBean");

    public String adminCompany() {
        try {

            companyBean.reset();

            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_CREATE_COMPANY"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("adminCompany"));
            return "adminCompany?faces-redirect=true ";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String removeCompany(Company company) {

        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            company.setActive(BigDecimal.ZERO);
            CompanyproductDAO dao1 = new CompanyproductDAO();
            List<Product> pr = dao1.getCompanyProducts(company, Boolean.TRUE);
            for (int i = 0; i < pr.size(); i++) {
                Product product = pr.get(i);
                product.setActive(BigDecimal.ZERO);
                persistenceHelper.edit(product);
            }
            
            
            persistenceHelper.edit(company);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_DELETECOMPANY")), null, null, null, null, null, null);
            userTransaction.commit();
            
            ApplicationBean appBean = (ApplicationBean)FacesUtils.getManagedBean("applicationBean");           
            appBean.resetCompanies();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("companyDeleted"));
            
            return adminCompany();
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

    public String insertCompany() {

        ApplicationBean appBean = (ApplicationBean)FacesUtils.getManagedBean("applicationBean");
        appBean.setCompanies(null);  
        appBean.resetCompanies();
            
        UserTransaction userTransaction = null;
        try {                       
            Company company = companyBean.getCompany();
            company.setActive(BigDecimal.ONE);
            company.setCategories(companyBean.getSelectedCategories());                        
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin(); 
            persistenceHelper.create(company);            
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTCOMPANY")), null, null, null, null, null, null);
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("companyInserted"));
            return adminCompany();
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

    public void goInsertCompany() {

        try {
            companyBean.setIsInsert(true);
            Company company = new Company();
            companyBean.setCompany(company);
            FacesUtils.callRequestContext("editCompanyDialogWidget.show()");            
            FacesUtils.updateHTMLComponnetWIthJS("editCompanyForm");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
    
     public void goInsertAddress() {

        try {            
            Address address = new Address();
            companyBean.setAddress(address);
            FacesUtils.callRequestContext("editAddressDialogWidget.show()");            
            FacesUtils.updateHTMLComponnetWIthJS("editAddressForm");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }
    
          
             
     public void insertAddress() {

                   
        UserTransaction userTransaction = null;
        try {                       
            Address address = companyBean.getAddress();
            address.setCompany(companyBean.getCompany());
            companyBean.getCompany().getAddresses().add(address);
            
            if (!companyBean.getIsInsert()) {
                userTransaction = persistenceHelper.getUserTransaction();
                userTransaction.begin(); 
                persistenceHelper.create(address);            
                persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATECOMPANY")), null, null, null, null, null, null);
                userTransaction.commit();
                FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("addressInserted"));
            }
            
            FacesUtils.callRequestContext("editAddressDialogWidget.hide()");
            
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
     

    public void goUpdateCompany(Company company) {

        try {            
            companyBean.setIsInsert(false);
            companyBean.setCompany(company);
            companyBean.setSelectedCategories(company.getCategories());              
            FacesUtils.callRequestContext("editCompanyDialogWidget.show()");  
//            FacesUtils.updateHTMLComponnetWIthJS(":editCompanyForm");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String updateCompany() {

        ApplicationBean appBean = (ApplicationBean)FacesUtils.getManagedBean("applicationBean");
        appBean.setCompanies(null);  
        appBean.resetCompanies();
        
        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();
            Company company = companyBean.getCompany();
            company.setCategories(companyBean.getSelectedCategories());
            userTransaction.begin();
            persistenceHelper.edit(company);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATECOMPANY")), null, null, null, null, null, null);
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("companyUpdated"));
            return adminCompany();
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
    
    
    public void deleteAddress(Address address) {
        
        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();            
            if (!companyBean.getIsInsert()) {
                userTransaction.begin();
                persistenceHelper.remove(address);
                persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATECOMPANY")), null, null, null, null, null, null);
                userTransaction.commit();            
                FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("addressDeleted"));
            }
            companyBean.getCompany().getAddresses().remove(address);
            
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
    
    public void showCompany(BigDecimal companyID) {
        try {
            
            Map<String,Object> options = new HashMap<String, Object>();  
            options.put("modal", true);  
            options.put("draggable", false);  
            options.put("resizable", false);  
            options.put("contentHeight", 550);  
            options.put("contentWidth", 760); 
            //options.put("productid", productID);
            
            
            Map<String,List<String>> params = new HashMap<String, List<String>>(); 
            List<String> data = new ArrayList<String>(0);
            data.add(companyID.toString());
            params.put("companyid", data);
            
            RequestContext.getCurrentInstance().openDialog("viewOnlyCompany.jsf?companyid="+companyID, options, params);

        } catch (Exception e) {
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
