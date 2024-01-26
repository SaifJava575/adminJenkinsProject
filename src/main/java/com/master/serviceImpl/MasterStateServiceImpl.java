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
import com.master.entity.MasterState;
import com.master.entity.MasterZone;
import com.master.service.IMasterStateService;

@Service
public class MasterStateServiceImpl implements IMasterStateService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterStateServiceImpl.class);

	@Override
	@Transactional
	public String createState(MasterState masterState) {
		logger.info("create MasterState called: {} " + masterState.getStateName());
		String status = "";
		String whereCondition = " and stateName= '" + masterState.getStateName()+ "'";
		List<?> existingStateName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_STATE + whereCondition,
				new Object[] {});
		if (existingStateName != null && existingStateName.size() > 0) {
			status = "statetAlreadyExist";
			logger.warn("MasterState with the name : '{}'" + masterState.getStateName() + " already exist");
		} else {
			try {
				masterState.setActiveFlag(true);
				logger.info(masterState.toString());
				iGenericDao.save(masterState);
				status = "savedSucessFully";
				logger.info(status + " " + masterState.getStateName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterState: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	@Override
	@Transactional
	public PaginationModel searchMasterState(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterState list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchState = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getStateName() != null && !searchBean.getStateName().equals(""))
				whereCondition += " and stateName like '%" + searchBean.getStateName() + "%'";

			if (searchBean.getStateCode() != null && !searchBean.getStateCode().equals(""))
				whereCondition += " and stateCode ='" + searchBean.getStateCode() + "'";

			String query = JavaConstant.SEARCH_MASTER_STATE;
			query += whereCondition;

			searchState = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching Vendor: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchState;
	}

	@Override
	public List<?> getStateById(Integer stateId) {
		logger.info("fetching getStateById  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> stateList = null;
		try {
			stateList = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_STATE_BY_ID, new Object[] { stateId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getStateById: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return stateList;
	}
	
	@Override
	@Transactional
	public String updateMasterState(MasterState masterState) {
		String updateStatus = "";
		List<?> fetchMasterStateName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_STATE_BY_ID,
				new Object[] { masterState.getStateId() });
		if (fetchMasterStateName.size()>0) {
			iGenericDao.update(masterState);
			logger.info("master State Name updated: '{}'" + masterState.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterState.getStateName());
		} else

		{
			logger.warn("MasterState Name: '{}' " + masterState.getStateName() + " and stateId : " + masterState.getStateId()
					+ " not available!");
			updateStatus = "stateNameNotExist";
		}
		return updateStatus;
	}

	@Override
	public List<?> getAllState() {
		logger.info("fetching getAllState  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> stateList = null;
		try {
			stateList = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_STATE, new Object[] {  });
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllState: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return stateList;
	}

}
