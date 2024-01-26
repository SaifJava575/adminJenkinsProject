package com.master.service;

import java.math.BigInteger;
import java.util.List;

import com.master.entity.MasterUserRoleMapping;

public interface IMasterRoleMappingService {
	public String createUserRoleMapping(MasterUserRoleMapping roleMapping);

	public List<?> getUserROleMapping(BigInteger userId);

	public String deleteUserRoleMapping(BigInteger userId, BigInteger roleId);
}
