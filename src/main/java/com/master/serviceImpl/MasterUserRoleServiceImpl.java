package com.master.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.bean.CommonSearchBean;
import com.master.bean.JavaConstant;
import com.master.bean.PaginationModel;
import com.master.dao.IGenericDao;
import com.master.entity.MasterUserRoles;
import com.master.entity.MasterZone;
import com.master.service.IMasterUserRole;

@Service
public class MasterUserRoleServiceImpl implements IMasterUserRole {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterUserRoleServiceImpl.class);

	@Override
	@Transactional
	public String createUserROle(MasterUserRoles masterUserRoles) {
		logger.info("create MasterUserRoles called: {} " + masterUserRoles.getRoleName());
		String status = "";
		String whereCondition = " and roleName= '" + masterUserRoles.getRoleName() + "'";
		List<?> existingRoleName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_USER_ROLE + whereCondition,
				new Object[] {});
		if (existingRoleName != null && existingRoleName.size() > 0) {
			status = "roleAlreadyExist";
			logger.warn("MasterUserRoles with the name : '{}'" + masterUserRoles.getRoleName() + " already exist");
		} else {
			try {
				masterUserRoles.setActiveFlag(true);
				logger.info(masterUserRoles.toString());
				iGenericDao.save(masterUserRoles);
				status = "savedSucessFully";
				logger.info(status + " " + masterUserRoles.getRoleName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterUserRoles: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	@Transactional
	public PaginationModel searchUserRole(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchUserRole list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchUserROle = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getRoleName() != null && !searchBean.getRoleName().equals(""))
				whereCondition += " and roleName like '%" + searchBean.getRoleName() + "%'";

			String query = JavaConstant.SEARCH_MASTER_USER_ROLE;
			query += whereCondition;

			searchUserROle = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching User ROle: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchUserROle;
	}

	@Override
	public List<?> getUserROleById(Integer roleId) {
		logger.info("fetching getUserROleById  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> userROleList = null;
		try {
			userROleList = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_USER_ROLE_BY_ID, new Object[] { roleId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getUserROleById: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return userROleList;
	}

	@Override
	@Transactional
	public String updateUserROleMaster(MasterUserRoles masterUserRoles) {
		String updateStatus = "";
		List<?> fetchMasterStateName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_USER_ROLE_BY_ID,
				new Object[] { masterUserRoles.getRoleId() });
		if (fetchMasterStateName.size()>0) {
			iGenericDao.update(masterUserRoles);
			logger.info("master User ROle Name updated: '{}'" + masterUserRoles.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterUserRoles.getRoleName());
		} else

		{
			logger.warn("MasterUserRole Name: '{}' " + masterUserRoles.getRoleName() + " and ROleId : " + masterUserRoles.getRoleId()
					+ " not available!");
			updateStatus = "roleNameNotExist";
		}
		return updateStatus;
	}

	@Override
	public List<?> getUserROle() {
		logger.info("fetching getUserROle  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> userROleList = null;
		try {
			userROleList = iGenericDao.executeDDLHQL(JavaConstant.ALL_USER_ROLE, new Object[] { });
		} catch (Exception e) {
			logger.error("error occcured while fetching getUserROle: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return userROleList;
	}

}
