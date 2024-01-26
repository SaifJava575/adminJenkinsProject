package com.master.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "master_user_jurisdiction_mapping",schema = "master")
@NamedQuery(name = "MasterUserJuricdictionMapping.findAll", query = "SELECT mujm FROM MasterUserJuricdictionMapping mujm")
public class MasterUserJuricdictionMapping implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapping_id")
	private Integer mappingId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "head_office_id")
	private Integer headOfficeId;
	
	@Column(name = "zone_id")
	private Integer zoneId;
	
	@Column(name = "region_id")
	private Integer regionId;
	
	@Column(name = "depot_id")
	private Integer depotId;
	
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

	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHeadOfficeId() {
		return headOfficeId;
	}

	public void setHeadOfficeId(Integer headOfficeId) {
		this.headOfficeId = headOfficeId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getDepotId() {
		return depotId;
	}

	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
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

	@Override
	public String toString() {
		return "MasterUserJuricdictionMapping [mapping_id=" + mappingId + ", userId=" + userId + ", headOfficeId="
				+ headOfficeId + ", zoneId=" + zoneId + ", regionId=" + regionId + ", depotId=" + depotId
				+ ", createdOn=" + createdOn + ", createdBy=" + createdBy + ", updatedOn=" + updatedOn + ", updatedBy="
				+ updatedBy + ", activeFlag=" + activeFlag + "]";
	}
}
