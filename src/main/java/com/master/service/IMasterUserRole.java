package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterUserRoles;

public interface IMasterUserRole {

	public String createUserROle(MasterUserRoles masterUserRoles);

	public PaginationModel searchUserRole(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getUserROleById(Integer roleId);

	public List<?> getUserROle();

	String updateUserROleMaster(MasterUserRoles masterUserRoles);
}
