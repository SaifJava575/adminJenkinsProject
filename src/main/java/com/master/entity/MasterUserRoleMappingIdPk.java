package com.master.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MasterUserRoleMappingIdPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private BigInteger userId;

	@Column(name = "role_id")
	private BigInteger roleId;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public BigInteger getRoleId() {
		return roleId;
	}

	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
}
