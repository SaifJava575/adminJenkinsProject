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
@Table(name = "master_depot",schema = "master")
@NamedQuery(name = "MasterDepot.findAll", query = "SELECT md FROM MasterDepot md")
public class MasterDepot implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "depot_id")
	private Integer depotId;
	
	@Column(name = "region_id")
	private Integer regionId;
	
	@Column(name = "zone_id")
	private Integer zoneId;

	@Column(name = "head_office_id")
	private Integer headOfficeId;
	
	@Column(name = "divisional_id")
	private Integer divisionalId;
 
	@Column(name = "depot_name")
	private String depotName;
	

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

	public Integer getDepotId() {
		return depotId;
	}

	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getHeadOfficeId() {
		return headOfficeId;
	}

	public void setHeadOfficeId(Integer headOfficeId) {
		this.headOfficeId = headOfficeId;
	}

	public Integer getDivisionalId() {
		return divisionalId;
	}

	public void setDivisionalId(Integer divisionalId) {
		this.divisionalId = divisionalId;
	}

	public String getDepotName() {
		return depotName;
	}

	public void setDepotName(String depotName) {
		this.depotName = depotName;
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

	public Boolean getActiveFlag() {
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
		return "MasterDepot [depotId=" + depotId + ", regionId=" + regionId + ", zoneId=" + zoneId + ", headOfficeId="
				+ headOfficeId + ", divisionalId=" + divisionalId + ", depotName=" + depotName + ", createdOn="
				+ createdOn + ", createdBy=" + createdBy + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy
				+ ", activeFlag=" + activeFlag + ", organizationInternalCode=" + organizationInternalCode + "]";
	}
}
