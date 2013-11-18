/**
 *
 */
package com.furniture.bean;

import com.furniture.entities.Role;
import com.furniture.entities.Userroles;
import java.util.List;

/**
 * @author peukianm
 *
 */
public class UserBean {

    private java.lang.String username;
    private java.lang.String password;

    public UserBean() {
    }

    public void reset() {
        username = null;
        password = null;
    }

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }
}
