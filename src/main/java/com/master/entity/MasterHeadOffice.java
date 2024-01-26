package com.master.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "master_head_office",schema = "master")
@NamedQuery(name = "MasterHeadOffice.findAll", query = "SELECT mu FROM MasterHeadOffice mu")
public class MasterHeadOffice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "head_office_id")
	private Integer headOfficeId;

	@Column(name = "head_office_name")
	private String headOfficeName;
	
	@Column(name = "created_on", insertable = true, updatable = false)
	private Timestamp createdOn = new Timestamp(System.currentTimeMillis());

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_on", insertable = true, updatable = false)
	private Timestamp updatedOn = new Timestamp(System.currentTimeMillis());

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "active_flag")
	private Boolean activeFlag;
	
	@Column(name = "organization_internal_code")
	private Integer organizationInternalCode;

	public Integer getHeadOfficeId() {
		return headOfficeId;
	}

	public void setHeadOfficeId(Integer headOfficeId) {
		this.headOfficeId = headOfficeId;
	}

	public String getHeadOfficeName() {
		return headOfficeName;
	}

	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getOrganizationInternalCode() {
		return organizationInternalCode;
	}

	public void setOrganizationInternalCode(Integer organizationInternalCode) {
		this.organizationInternalCode = organizationInternalCode;
	}

	@Override
	public String toString() {
		return "MasterHeadOffice [headOfficeId=" + headOfficeId + ", headOfficeName=" + headOfficeName + ", createdOn="
				+ createdOn + ", createdBy=" + createdBy + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy
				+ ", activeFlag=" + activeFlag + ", organizationInternalCode=" + organizationInternalCode + "]";
	}
	
}
