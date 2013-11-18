package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Users entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USERS", schema = "FURNITURE")
public class Users implements java.io.Serializable {

    // Fields
    private BigDecimal userid;
    private Role role;
    private Company company;
    private String username;
    private String password;
    private String description;
    private String name;
    private String surname;
    private Timestamp createdTimestamp;
    private Timestamp modifiedTimestamp;
    private List<Userroles> userroleses = new ArrayList<Userroles>(0);

    // Constructors
    /**
     * default constructor
     */
    public Users() {
    }

    /**
     * minimal constructor
     */
    public Users(BigDecimal userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    /**
     * full constructor
     */
    public Users(BigDecimal userid, Role role, String username, String password, String description, String name, String surname, List<Userroles> userroleses, 
            Company company, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
        this.userid = userid;
        this.role = role;
        this.company = company;
        this.username = username;
        this.password = password;
        this.description = description;
        this.name = name;
        this.surname = surname;
        this.userroleses = userroleses;
        this.createdTimestamp = createdTimestamp;
        this.modifiedTimestamp = modifiedTimestamp;
    }

    // Property accessors
    @Id
    @Column(name = "USERID", unique = true, nullable = false, precision = 22, scale = 0)
    public BigDecimal getUserid() {
        return this.userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID")
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANYID")
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column(name = "USERNAME", nullable = false, length = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = 100)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "DESCRIPTION", length = 100)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "NAME", length = 80)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME", length = 80)
    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
    public List<Userroles> getUserroleses() {
        return this.userroleses;
    }

    public void setUserroleses(List<Userroles> userroleses) {
        this.userroleses = userroleses;
    }

    @Column(name = "CREATED_TIMESTAMP", length = 11, insertable = false, updatable = true)
    public Timestamp getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    @Column(name = "MODIFIED_TIMESTAMP", length = 11, insertable = false, updatable = true)
    public Timestamp getModifiedTimestamp() {
        return this.modifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
    }
}