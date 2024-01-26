package com.master.serviceImpl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.bean.JavaConstant;
import com.master.dao.IGenericDao;
import com.master.entity.MasterUserRoleMapping;
import com.master.service.IMasterRoleMappingService;

@Service
public class MasterRoleMappingServiceImpl implements IMasterRoleMappingService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterRoleMappingServiceImpl.class);

	@Override
	@Transactional
	public String createUserRoleMapping(MasterUserRoleMapping roleMapping) {
		logger.info("create createUserRoleMapping called: {} ");
		String status = "";
		try {
			roleMapping.setActiveFlag(true);
			logger.info(roleMapping.toString());
			iGenericDao.save(roleMapping);
			status = "savedSucessFully";
		} catch (Exception e) {
			logger.error(
					"error occured while saving createUserRoleMapping: '{}' " + e.getCause() + " " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<?> getUserROleMapping(BigInteger userId) {
		logger.info("fetching getUserROleMapping  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> userROleList = null;
		try {
			userROleList = iGenericDao.executeDDLHQL(JavaConstant.ALL_USER_ROLE_MAPPING, new Object[] {userId});
		} catch (Exception e) {
			logger.error("error occcured while fetching getUserROleMapping: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return userROleList;
	}

	@Override
	@Transactional
	public String deleteUserRoleMapping(BigInteger userId, BigInteger roleId) {
		String status = "";
		try {
			iGenericDao.executeDMLHQL(JavaConstant.DLETE_USER_ROLE_MAPPING, new Object[] { userId, roleId });
			status = "DELETE";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
