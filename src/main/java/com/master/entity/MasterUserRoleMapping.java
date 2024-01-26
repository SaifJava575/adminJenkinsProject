package com.master.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "master_user_role_mapping",schema = "master")
@NamedQuery(name = "MasterUserRoleMapping.findAll", query = "SELECT murm FROM MasterUserRoleMapping murm")
public class MasterUserRoleMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MasterUserRoleMappingIdPk id;

	@Column(name = "created_on", insertable = true, updatable = false)
	private Timestamp createdOn = new Timestamp(System.currentTimeMillis());

	@Column(name = "created_by")
	private BigInteger createdBy;

	@Column(name = "updated_on", insertable = true, updatable = false)
	private Timestamp updatedOn = new Timestamp(System.currentTimeMillis());

	@Column(name = "updated_by")
	private BigInteger updatedBy;

	@Column(name = "active_flag")
	private Boolean activeFlag;

	public MasterUserRoleMappingIdPk getId() {
		return id;
	}

	public void setId(MasterUserRoleMappingIdPk id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public BigInteger getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public BigInteger getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigInteger updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	
}
