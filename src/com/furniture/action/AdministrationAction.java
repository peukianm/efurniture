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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

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
            
            Users temp = null;
            List<Users> users = userDAO.findByProperty("username", userBean.getUsername());
            if (users == null || users.size()>0) {
               if (FurnitureUtil.check(userBean.getPassword(), users.get(0).getPassword()))
                   temp = users.get(0);
               else
                   temp = null;
            } else {                
                temp = null;
            }
            
            
            //Users temp = userDAO.findUser(userBean.getUsername(), userBean.getPassword());
            
            
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
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();

            // furniture Editor
            if (temp.getRole().getRoleid().intValue() == 1 || temp.getRole().getRoleid().intValue() == 2 || temp.getRole().getRoleid().intValue() == 3) {
                persistenceUtil.audit(temp, new BigDecimal(SystemParameters.getInstance().getProperty("ACT_LOGINUSER")), null);
                sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_FURNITURE_HOME"));
                sessionBean.setPageName(MessageBundleLoader.getMessage("homePage"));
                return "backend/main?faces-redirect=true";
            }

            userTransaction.commit();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
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

    public void invalidateSession() {
        FacesUtils.resetManagedBeanJSF2("sessionBean");
        FacesUtils.invalidateSession();
    }

    public String gotoMain() {
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

    public String auditControl() {
        try {
            AuditBean auditBean = (AuditBean) FacesUtils.getManagedBean("auditBean");
            auditBean.reset();
            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_AUDIT_CONTROL"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("audit"));
            return "auditControl?faces-redirect=true ";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String userAdmin() {
        try {
            UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
            userBean.reset();
            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_USER_ADMIN"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("userAdmin"));
            return "userAdmin?faces-redirect=true ";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public List<Users> completeUser(String username) {
        try {
            if (username != null && !username.trim().isEmpty() && username.trim().length() >= 1) {
                username = username.trim();
                UsersDAO userDAO = new UsersDAO();
                List<Users> users = userDAO.fetchUserAutoCompleteUsername(username);
                return users;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return null;
        }
    }

    public void autocompleteUsernameSelectUser(SelectEvent event) {
        try {
            AuditBean auditBean = (AuditBean) FacesUtils.getManagedBean("auditBean");
            Users user = auditBean.getSelectUser();
            auditBean.setSearchUser(user);
            auditBean.setSelectUser(null);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public List<Product> completeProduct(String name) {
        try {
            if (name != null && !name.trim().isEmpty() && name.trim().length() >= 1) {
                name = name.trim();
                ProductDAO dao = new ProductDAO();
                List<Product> products = dao.fetchProductAutoCompleteName(name, null);
                return products;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return null;
        }
    }

    public void autocompleteNameSelectProduct(SelectEvent event) {
        try {
            AuditBean auditBean = (AuditBean) FacesUtils.getManagedBean("auditBean");
            Product product = auditBean.getSelectProduct();
            auditBean.setSearchProduct(product);
            auditBean.setSelectProduct(null);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String resetSearchAudit() {
        try {
            return auditControl();
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void searchAudit() {
        try {

            AuditBean auditBean = (AuditBean) FacesUtils.getManagedBean("auditBean");


            Timestamp from = null;
            if (auditBean.getSearchFromActionDate() != null) {
                from = FormatUtils.formatDateToTimestamp(auditBean.getSearchFromActionDate());
            }

            Timestamp to = null;
            if (auditBean.getSearchToActionDate() != null) {
                to = FormatUtils.formatDateToTimestamp(auditBean.getSearchToActionDate());
            }

            AuditingDAO dao = new AuditingDAO();
            List<Auditing> auditings = dao.searchAudit(auditBean.getSearchUser(), auditBean.getSearchAction(), from, to, auditBean.getSearchProduct(), auditBean.getSearchCompany());
            auditBean.setAudits(auditings);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String resetUserAdmin() {
        try {
            return userAdmin();
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public void searchUser() {
        try {
            UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
            UsersDAO dao = new UsersDAO();
            List<Users> users = dao.searchUser(userBean.getSearchByRole(), userBean.getSearchByCompany(), userBean.getSearchByUsername(), userBean.getSearchBySurname());
            userBean.setUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void goInsertUser() {
        try {
            UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
            Users user = new Users();
            user.setActive(BigDecimal.ONE);
            userBean.setRoles(new ArrayList<Role>(0));

            userBean.setUser(user);
            FacesUtils.callRequestContext("createUserDialogWidget.show()");
            FacesUtils.updateHTMLComponnetWIthJS("createUserPanelID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void goResetPasword() {
        try {
            UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");

            FacesUtils.callRequestContext("resetPasswordDialogWidget.show()");
            FacesUtils.updateHTMLComponnetWIthJS("resetPasswordUserPanelID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public void goUpdateUser() {
        try {
            UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");

            List<Userroles> userroles = userBean.getUser().getUserroleses();
            userBean.setRoles(new ArrayList<Role>(0));
            for (int i = 0; i < userroles.size(); i++) {
                Userroles userrole = userroles.get(i);
                userBean.getRoles().add(userrole.getRole());
            }
            
            if (userBean.getUser().getActive().equals(BigDecimal.ONE)) {
                userBean.setActive(true);
            } else {
                userBean.setActive(false);
            }

            FacesUtils.callRequestContext("updateUserDialogWidget.show()");
            FacesUtils.updateHTMLComponnetWIthJS("updateUserPanelID");
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
        }
    }

    public String insertUser() {
        UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
        UserTransaction userTransaction = null;
        try {
            userTransaction = persistenceHelper.getUserTransaction();
            List<Role> roles = userBean.getRoles();
            userTransaction.begin();
            Users user = userBean.getUser();
            
            List<Users> usrs = userDAO.findByProperty("username", userBean.getUser().getUsername().trim());
            if (usrs.size()==1 ) {                
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("useranameAlreadyUsed"));
                FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                FacesUtils.callRequestContext("generalAlertWidget.show()");                
                return "";
            }
            
            usrs = userDAO.findByProperty("email", userBean.getUser().getEmail().trim());
            
            if (usrs.size()==1) {                
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("emailAlreadyUsed"));
                FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                FacesUtils.callRequestContext("generalAlertWidget.show()");                
                return "";
            }
            
            
            

            List<Userroles> userroles = new ArrayList<Userroles>(0);
            for (int i = 0; i < roles.size(); i++) {
                Role role = roles.get(i);
                Userroles userrole = new Userroles();
                userrole.setUsers(user);
                userrole.setRole(role);
                userroles.add(userrole);
            }

            String hashedPassword = FurnitureUtil.getSaltedHash(userBean.getPassword());
            
            user.setPassword(hashedPassword);
            user.setUserroleses(userroles);
            persistenceHelper.create(user);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_INSERTUSER")), "User " + user.getUsername() + " inserted");
            userTransaction.commit();


            FacesUtils.callRequestContext("createUserDialogWidget.hide()");
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("newUserInserted"));
            return userAdmin();
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

    public void deleteUser() {

        UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
        UserTransaction userTransaction = null;
        try {
            Users user = userBean.getUser();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();
            user.setActive(BigDecimal.ZERO);
            user = persistenceHelper.editPersist(user);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_DELETEUSER")), "User " + user.getUsername() + " removed");
            userTransaction.commit();

            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("userDeleted"));
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

    public String updateUser() {
        UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
        UserTransaction userTransaction = null;
        try {
            Users user = userBean.getUser();
            
            List<Users> usrs = userDAO.findByProperty("username", userBean.getUser().getUsername().trim());
            if (usrs.size()==1 && !usrs.get(0).equals(user) ) {                
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("useranameAlreadyUsed"));
                FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                FacesUtils.callRequestContext("generalAlertWidget.show()");                
                return "";
            }
            
            usrs = userDAO.findByProperty("email", userBean.getUser().getEmail().trim());
            
            if (usrs.size()==1 && !usrs.get(0).equals(user)) {                
                sessionBean.setAlertMessage(MessageBundleLoader.getMessage("emailAlreadyUsed"));
                FacesUtils.updateHTMLComponnetWIthJS("alertPanel");
                FacesUtils.callRequestContext("generalAlertWidget.show()");                
                return "";
            }
            
            
            
            userTransaction = persistenceHelper.getUserTransaction();
            if (userBean.isActive()) {
                user.setActive(BigDecimal.ONE);
            } else {
                user.setActive(BigDecimal.ZERO);
            }

            userTransaction.begin();

            //removing
            for (int i = 0; i < user.getUserroleses().size(); i++) {
                Userroles userrole = user.getUserroleses().get(i);                
                persistenceHelper.remove(userrole);
            }
            user.setUserroleses(null);

            //adding
            List<Role> roles = userBean.getRoles();
            List<Userroles> userroles = new ArrayList<Userroles>(0);
            for (int i = 0; i < roles.size(); i++) {
                Role role = roles.get(i);               
                Userroles userrole = new Userroles();
                userrole.setUsers(user);
                userrole.setRole(role);
                userroles.add(userrole);
            }
                       
            user.setUserroleses(userroles);

            user = persistenceHelper.editPersist(user);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEUSER")), "User " + user.getUsername() + " updated");
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("userUpdated"));
            FacesUtils.callRequestContext("updateUserDialogWidget.hide()");
            return userAdmin();

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

    public String resetPassword() {
        UserBean userBean = (UserBean) FacesUtils.getManagedBean("userBean");
        UserTransaction userTransaction = null;
        try {
            Users user = userBean.getUser();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();

            String hashedPassword = FurnitureUtil.getSaltedHash(userBean.getPassword());
            user.setPassword(hashedPassword);

            user = persistenceHelper.editPersist(user);
            persistenceUtil.audit(sessionBean.getUsers(), new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPASSWORD")), "User Password " + user.getUsername() + " updated");
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("userUpdated"));
            FacesUtils.callRequestContext("updateUserDialogWidget.hide()");
            return userAdmin();

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

    public String goResetPasswordEmail() {
        try {
            //ResetBean resetBean = (ResetBean)FacesUtils.getManagedBean("resetBean");                        
            sessionBean.setPageCode(SystemParameters.getInstance().getProperty("PAGE_RESET_PASSWORD"));
            sessionBean.setPageName(MessageBundleLoader.getMessage("passwordReminder"));
            return "resetPassword?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    public String sendResetPasswordEmail() {
        try {
            ResetBean resetBean = (ResetBean) FacesUtils.getManagedBean("resetBean");
            String email = resetBean.getEmail().trim();            
            List<Users> users = userDAO.findByProperty("email", email);
            if (users != null && users.size() == 1) {
                Users user = users.get(0);                
                String username = user.getUsername();
                String[] emails = new String[1];
                emails[0] = user.getEmail();
                
                String link = SystemParameters.getInstance().getProperty("resetPassword_link")+user.getPassword() +"&userid="+user.getUserid() ;                
                String host = SystemParameters.getInstance().getProperty("gmail_host");
                String port = SystemParameters.getInstance().getProperty("gmail_port");
                String account = SystemParameters.getInstance().getProperty("gmail_account");
                String password = SystemParameters.getInstance().getProperty("gmail_password");
                String subject = SystemParameters.getInstance().getProperty("email_subject") + username;
                String body = SystemParameters.getInstance().getProperty("email_body") + link;
                
                FurnitureUtil.sendFromGMail(host, port, account, password, emails, subject, body);
                FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("successUserEmail"));
                return "";
            } else {                
                FacesUtils.addErrorMessage(MessageBundleLoader.getMessage("falseUserEmail"));
                return "";                
            }

            //return "loginPage?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            sessionBean.setErrorMsgKey("errMsg_GeneralError");
            goError(e);
            return "";
        }
    }

    
    public void resetPasswordEmail() {
        ResetBean resetBean = (ResetBean) FacesUtils.getManagedBean("resetBean");
        UserTransaction userTransaction = null;
        try {
            Users user = resetBean.getUser();
            userTransaction = persistenceHelper.getUserTransaction();
            userTransaction.begin();

            String hashedPassword = FurnitureUtil.getSaltedHash(resetBean.getPassword());
            user.setPassword(hashedPassword);

            user = persistenceHelper.editPersist(user);
            persistenceUtil.audit(user, new BigDecimal(SystemParameters.getInstance().getProperty("ACT_UPDATEPASSWORD")), "User Password " + user.getUsername() + " updated");
            userTransaction.commit();
            FacesUtils.addInfoMessage(MessageBundleLoader.getMessage("userUpdated"));
           
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
