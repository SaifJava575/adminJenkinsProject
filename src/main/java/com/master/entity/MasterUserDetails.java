package com.master.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "master_user_details",schema = "master")
@NamedQuery(name = "MasterUserDetails.findAll", query = "SELECT mud FROM MasterUserDetails mud")
public class MasterUserDetails implements Serializable{
  
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private BigInteger userId;
	
	@Column(name = "login_id")
	private String loginId;
	
	@Column(name = "user_pwd")
	private String userPwd;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile_no")
	private String mobileNo;	
	
	@Column(name = "date_of_charge")
	private Date dateOfCharge;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "date_of_joining")
	private Date dateOfjoining;
	
	@Column(name = "designation_id")
	private Integer designationId;
	
	@Column(name = "state_code")
	private String stateCode;
	
	@Column(name = "district_code")
	private Integer districtCode;
	
	@Column(name = "taluk_code")
	private Integer talukCode;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "dept_id")
	private Integer deptId;
	
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

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDateOfCharge() {
		return dateOfCharge;
	}

	public void setDateOfCharge(Date dateOfCharge) {
		this.dateOfCharge = dateOfCharge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfjoining() {
		return dateOfjoining;
	}

	public void setDateOfjoining(Date dateOfjoining) {
		this.dateOfjoining = dateOfjoining;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Integer districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getTalukCode() {
		return talukCode;
	}

	public void setTalukCode(Integer talukCode) {
		this.talukCode = talukCode;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
		return "MasterUserDetails [userId=" + userId + ", loginId=" + loginId + ", userPwd=" + userPwd + ", userName="
				+ userName + ", email=" + email + ", mobileNo=" + mobileNo + ", dateOfCharge=" + dateOfCharge
				+ ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", dateOfjoining=" + dateOfjoining
				+ ", designationId=" + designationId + ", stateCode=" + stateCode + ", districtCode=" + districtCode
				+ ", talukCode=" + talukCode + ", otp=" + otp + ", deptId=" + deptId + ", createdOn=" + createdOn
				+ ", createdBy=" + createdBy + ", updatedOn=" + updatedOn + ", updatedBy=" + updatedBy + ", activeFlag="
				+ activeFlag + "]";
	}
	
}
