/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.furniture.bean;

import com.furniture.entities.Userroles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author peukianm
 */

@ManagedBean(name = "roleSelectionBean")
@ViewScoped
public class RoleSelectionBean  implements Serializable {
    
    private List<Userroles> userroles;
    private Userroles selectedRole;
    
    
    @PreDestroy
    public void reset() {
        userroles = null;
        selectedRole = null;                
    }

    public List<Userroles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<Userroles> userroles) {
        this.userroles = userroles;
    }

    public Userroles getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Userroles selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    
    
}
