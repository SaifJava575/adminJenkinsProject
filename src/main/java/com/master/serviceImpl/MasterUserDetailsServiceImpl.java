package com.master.serviceImpl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.master.bean.CommonSearchBean;
import com.master.bean.JavaConstant;
import com.master.bean.PaginationModel;
import com.master.bean.Response;
import com.master.dao.IGenericDao;
import com.master.entity.MasterState;
import com.master.entity.MasterUserDetails;
import com.master.entity.MasterZone;
import com.master.service.ICommonFunctionalityService;
import com.master.service.IMasterUserDetailsService;

@Service
public class MasterUserDetailsServiceImpl implements IMasterUserDetailsService {

	@Autowired
	private IGenericDao iGenericDao;
	@Autowired
	private ICommonFunctionalityService commonService;

	private static final Logger logger = LoggerFactory.getLogger(MasterUserDetailsServiceImpl.class);

	@Override
	@Transactional
	public String createUserDetails(MasterUserDetails masterUserDetails) {
		logger.info("create MasterUserDetails called: {} " + masterUserDetails.getLoginId());
		String status = "";
		String whereCondition = " and loginId= '" + masterUserDetails.getLoginId() + "'";
		List<?> existingUserName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_USER_DETAILS + whereCondition,
				new Object[] {});
		if (existingUserName != null && existingUserName.size() > 0) {
			status = "userAlreadyExist";
			logger.warn("MasterUserDetails with the name : '{}'" + masterUserDetails.getLoginId() + " already exist");
		} else {
			try {
				masterUserDetails.setActiveFlag(true);
				String password = commonService.encryptPassword("SALT", masterUserDetails.getLoginId());
				masterUserDetails.setUserPwd(password);
				logger.info(masterUserDetails.toString());
				iGenericDao.save(masterUserDetails);
				status = "savedSucessFully";
				logger.info(status + " " + masterUserDetails.getUserName());
			} catch (Exception e) {
				logger.error(
						"error occured while saving MasterUserDetails: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	@Transactional
	public PaginationModel searchMasterUserDetails(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterUserDetails list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchUserDetails = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and mud.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getUserName() != null && !searchBean.getUserName().equals(""))
				whereCondition += " and mud.userName like '%" + searchBean.getUserName() + "%'";

			if (searchBean.getLoginId() != null && !searchBean.getLoginId().equals(""))
				whereCondition += " and mud.loginId like '%" + searchBean.getLoginId() + "%'";

			if (searchBean.getEmail() != null && !searchBean.getEmail().equals(""))
				whereCondition += " and mud.email like '%" + searchBean.getEmail() + "%'";

			if (searchBean.getMobileNo() != null && !searchBean.getMobileNo().equals(""))
				whereCondition += " and mud.mobileNo like '%" + searchBean.getMobileNo() + "%'";

			if (searchBean.getDesignationId() != null && searchBean.getDesignationId() != 0)
				whereCondition += " and mud.designationId =" + searchBean.getDesignationId();

			String query = JavaConstant.SEARCH_MASTER_USER_DETAILS;
			query += whereCondition;

			searchUserDetails = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query,
					new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching searchMasterUserDetails: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchUserDetails;
	}

	@Override
	public List<?> MasterUserDetailsById(BigInteger userId) {
		List<?> userInfo = null;
		try {
			userInfo = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_USER_DETAILS_BY_USER_ID,
					new Object[] { userId });

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	@Transactional
	public String updateUserDetaile(MasterUserDetails userDetails) {
		String updateStatus = "";
		List<MasterZone> fetchMasterUserName = iGenericDao.executeDDLHQL(
				JavaConstant.SEARCH_MASTER_USER_DETAILS_BY_USER_ID, new Object[] { userDetails.getUserId() });
		if (fetchMasterUserName.size() > 0) {
			try {
				String password = commonService.encryptPassword("SALT", "saif123");
				userDetails.setUserPwd(password);
				iGenericDao.update(userDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("MasterUserDetails Name updated: '{}'" + userDetails.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + userDetails.getUserName());
		} else

		{
			logger.warn("MasterUserDetails : '{}' " + userDetails.getUserName() + " and userId : "
					+ userDetails.getUserId() + " not available!");
			updateStatus = "userNameNotExist";
		}
		return updateStatus;
	}

}
