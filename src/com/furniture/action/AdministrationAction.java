/**
 *
 */
package com.furniture.action;

import com.furniture.bean.*;
import com.furniture.dao.*;
import com.furniture.entities.*;
import com.furniture.util.*;
import com.furniture.util.*;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;

public class AdministrationAction implements Serializable {

    UsersDAO userDAO = new UsersDAO();
    private static final Logger logger = Logger.getLogger(AdministrationAction.class);
    private SessionBean sessionBean = (SessionBean) FacesUtils.getManagedBean("sessionBean");
    @EJB
    private PersistenceUtil persistenceUtil;

     @EJB
    private PersistenceHelper persistenceHelper;
    
    public AdministrationAction() {
    }

    public String loginAction() {
        try {
//            ExAssertionDAO dao = new ExAssertionDAO();
//            dao.test();  

            UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
            Users temp = userDAO.findUser(userBean.getUsername(), userBean.getPassword());
            if (temp == null) {
                userBean.setPassword(null);
                sessionBean.setErrorMsgKey("errMsg_InvalidCredentials");
                FacesUtils.addErrorMessage(MessageBundleLoader.getMessage("errMsg_InvalidCredentials"));
                return "loginPage";
            }

            List<Userroles> userroles = userDAO.getUserRoles(temp);
            RoleSelectionBean roleSelectionBean = (RoleSelectionBean) FacesUtils.getManagedBean("roleSelectionBean");
            roleSelectionBean.setUserroles(userroles);
            temp.setUserroleses(userroles);
            sessionBean.setUsers(temp);


            if (userroles.size() > 1) {
                FacesUtils.callRequestContext("selectRoleDialog.show()");
            } else if (userroles.size() == 1) {
                temp.setRole(userroles.get(0).getRole());
                return mainPageForward(temp);
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String selectRole() {
        RoleSelectionBean roleSelectionBean = (RoleSelectionBean) FacesUtils.getManagedBean("roleSelectionBean");
        Userroles selectedRole = roleSelectionBean.getSelectedRole();
        Users temp = sessionBean.getUsers();
        temp.setRole(selectedRole.getRole());
        sessionBean.setUsers(temp);

        return mainPageForward(temp);

    }

    private String mainPageForward(Users temp) {
        
        UserTransaction userTransaction = null; 
        try {
            
           //userTransaction = persistenceHelper.getUserTransaction();
           //userTransaction.begin();
            
            
            // furniture Editor
            if (temp.getRole().getRoleid().intValue() == 1 || temp.getRole().getRoleid().intValue() == 2 || temp.getRole().getRoleid().intValue() == 3) {
                
                persistenceUtil.audit(temp, new BigDecimal(SystemParameters.getInstance().getProperty("ACT_LOGINUSER")), null);
                                
                sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
                sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
                return "backend/main?faces-redirect=true";
            }


            //userTransaction.commit();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
//            try {
//                userTransaction.rollback();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void invalidateSession() {
        FacesUtils.resetManagedBeanJSF2("sessionBean");
        FacesUtils.invalidateSession();
    }

    public String gotoMain() {
        System.out.println("GOING TO MAIN!!!!!!!!!!!!!!!!!!!!!!!1111111");
        try {
            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
            return "main?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String getPropertyValue(String key) {
        String propertyValue = SystemParameters.getInstance().getProperty(key);
        return propertyValue;
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

    public String logoutAction() {
        try {
            FacesUtils.resetManagedBeanJSF2("sessionBean");
            FacesUtils.invalidateSession();
            FacesUtils.redirectAJAX(FacesUtils.getContextPath() + "/loginPage.jsf?faces-redirect=true");
            return "./loginPage?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String logoutAction(int t) {
        try {
            FacesUtils.resetManagedBeanJSF2("sessionBean");
            FacesUtils.invalidateSession();
            //FacesUtils.redirectAJAX(FacesUtils.getContextPath() + "/loginPage.jsf?faces-redirect=true");
            return "/loginPage?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void closeAlertDlg() {
        sessionBean.setShowGeneralDialog(false);
        sessionBean.setShowMsgDialog(false);
    }
}
