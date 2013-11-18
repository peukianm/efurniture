package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Auditing entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AUDITING", schema = "FURNITURE")
public class Auditing implements java.io.Serializable {

	// Fields

	private BigDecimal auditingid;
	private Specification specification;
	private Action action;
	private Productline productline;
	private Company company;
	private Product product;
	private Users users;
	private Item item;
	private Catalogue catalogue;
	private String comments;
	private Date actiondate;
	private Timestamp actiontime;

	// Constructors

	/** default constructor */
	public Auditing() {
	}

	/** minimal constructor */
	public Auditing(BigDecimal auditingid, Action action, Users users, Date actiondate) {
		this.auditingid = auditingid;
		this.action = action;
		this.users = users;
		this.actiondate = actiondate;
	}

	/** full constructor */
	public Auditing(BigDecimal auditingid, Specification specification, Action action, Productline productline, Company company, Product product, Users users,
			Item item, Catalogue catalogue, String comments, Date actiondate, Timestamp actiontime) {
		this.auditingid = auditingid;
		this.specification = specification;
		this.action = action;
		this.productline = productline;
		this.company = company;
		this.product = product;
		this.users = users;
		this.item = item;
		this.catalogue = catalogue;
		this.comments = comments;
		this.actiondate = actiondate;
		this.actiontime = actiontime;
	}

	// Property accessors
	@Id
	@Column(name = "AUDITINGID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getAuditingid() {
		return this.auditingid;
	}

	public void setAuditingid(BigDecimal auditingid) {
		this.auditingid = auditingid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPECIFICATIONID")
	public Specification getSpecification() {
		return this.specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIONID", nullable = false)
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTLINEID")
	public Productline getProductline() {
		return this.productline;
	}

	public void setProductline(Productline productline) {
		this.productline = productline;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANYID")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTID")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEMID")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATALOGUEID")
	public Catalogue getCatalogue() {
		return this.catalogue;
	}

	public void setCatalogue(Catalogue catalogue) {
		this.catalogue = catalogue;
	}

	@Column(name = "COMMENTS", length = 400)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACTIONDATE", nullable = false, length = 7)
	public Date getActiondate() {
		return this.actiondate;
	}

	public void setActiondate(Date actiondate) {
		this.actiondate = actiondate;
	}

	@Column(name = "ACTIONTIME", length = 11)
	public Timestamp getActiontime() {
		return this.actiontime;
	}

	public void setActiontime(Timestamp actiontime) {
		this.actiontime = actiontime;
	}

}