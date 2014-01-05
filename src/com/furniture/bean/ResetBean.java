/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.dao.UsersDAO;
import com.furniture.entities.Users;
import com.furniture.util.PersistenceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author peukianm
 */
@ManagedBean
@ViewScoped
public class ResetBean implements Serializable{
    
    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;
    
    @EJB
    private PersistenceHelper persistenceHelper;
    
    private String password;
    private String rePassword;
    private String email;
    private String sessionid;
    private Users user;
    private String userid;
    private boolean show = false;
    
    
    @PostConstruct
    public void init() {        
        System.out.println("Sessionid="+sessionid);
        System.out.println("User="+user);
        System.out.println("Userid="+userid);
        
        if (userid!=null && sessionid!=null) {            
            user = (Users)persistenceHelper.find(Users.class, new BigDecimal(userid));
            if (user.getPassword().equals(sessionid)){
                show = true;
            } else {
                show =false;
            }
        } else {
            show = false;
        }
        
        System.out.println("INITIALIZING IN RESET BEAN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111");

    }

    
    
    
    @PreDestroy
    public void reset() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    
    
    
    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    
    
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    
    
    
    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
    
    

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
    
}
