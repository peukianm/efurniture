package com.furniture.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Itemspecification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ITEMSPECIFICATION", schema = "FURNITURE")
public class Itemspecification implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Specification specification;
	private Item item;
	private BigDecimal active;
	private Timestamp createdTimestamp;
	private Timestamp modifiedTimestamp;

	// Constructors

	/** default constructor */
	public Itemspecification() {
	}

	/** minimal constructor */
	public Itemspecification(BigDecimal id, Specification specification, Item item, BigDecimal active) {
		this.id = id;
		this.specification = specification;
		this.item = item;
		this.active = active;
	}

	/** full constructor */
	public Itemspecification(BigDecimal id, Specification specification, Item item, BigDecimal active, Timestamp createdTimestamp, Timestamp modifiedTimestamp) {
		this.id = id;
		this.specification = specification;
		this.item = item;
		this.active = active;
		this.createdTimestamp = createdTimestamp;
		this.modifiedTimestamp = modifiedTimestamp;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPECIFICATIONID", nullable = false)
	public Specification getSpecification() {
		return this.specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEMID", nullable = false)
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "ACTIVE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	@Column(name = "CREATED_TIMESTAMP", length = 11)
	public Timestamp getCreatedTimestamp() {
		return this.createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Column(name = "MODIFIED_TIMESTAMP", length = 11)
	public Timestamp getModifiedTimestamp() {
		return this.modifiedTimestamp;
	}

	public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}

}