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
import com.master.entity.MasterDistrict;
import com.master.entity.MasterZone;
import com.master.service.IMasterDistrictService;

@Service
public class MasterDistrictServiceImpl implements IMasterDistrictService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterDistrictServiceImpl.class);

	@Override
	@Transactional
	public String createDistrict(MasterDistrict masterDistrict) {
		logger.info("create MasterDistrict called: {} " + masterDistrict.getDistrictName());
		String status = "";
		String whereCondition = " and districtName= '" + masterDistrict.getDistrictName() + "'";
		List<?> existingDistrictName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_DISTRICT + whereCondition,
				new Object[] {});
		if (existingDistrictName != null && existingDistrictName.size() > 0) {
			status = "DistrictAlreadyExist";
			logger.warn("MasterDistrict with the name : '{}'" + masterDistrict.getDistrictName() + " already exist");
		} else {
			try {
				masterDistrict.setActiveFlag(true);
				logger.info(masterDistrict.toString());
				iGenericDao.save(masterDistrict);
				status = "savedSucessFully";
				logger.info(status + " " + masterDistrict.getDistrictName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterDistrict: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	@Transactional
	public PaginationModel searchMasterDistrict(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterDistrict list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchState = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and md.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getDistrictName() != null && !searchBean.getDistrictName().equals(""))
				whereCondition += " and md.districtName like '%" + searchBean.getDistrictName() + "%'";

			if (searchBean.getStateCode() != null && !searchBean.getStateCode().equals(""))
				whereCondition += " and md.stateCode ='" + searchBean.getStateCode() + "'";

			String query = JavaConstant.SEARCH_MASTER_DISTRICT;
			query += whereCondition;

			searchState = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching Vendor: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchState;
	}

	@Override
	public List<?> getAllDistictById(Integer districtCode) {
		logger.info("fetching getAllDistictById  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getDistrictById = null;
		try {
			getDistrictById = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_DISTRICT_BY_ID, new Object[] { districtCode });
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllDistictById: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getDistrictById;
	}
	
	@Override
	@Transactional
	public String updateMasterDistrict(MasterDistrict masterDistrict) {
		String updateStatus = "";
		List<MasterZone> fetchMasterStateName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_DISTRICT_BY_ID,
				new Object[] { masterDistrict.getDistrictCode() });
		if (fetchMasterStateName.size()>0) {
			iGenericDao.update(masterDistrict);
			logger.info("Master District  Name updated: '{}'" + masterDistrict.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterDistrict.getDistrictName());
		} else

		{
			logger.warn("MasterDistrict Name: '{}' " + masterDistrict.getDistrictName() + " and districtCode : " + masterDistrict.getDistrictCode()
					+ " not available!");
			updateStatus = "districtNameNotExist";
		}
		return updateStatus;
	}

	@Override
	public List<?> getAllDistictByStateCode(String stateCode) {
		logger.info("fetching getAllDistictByStateCode  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getDistrictByStateCode = null;
		try {
			getDistrictByStateCode = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_DISTRICT_BY_STATE_CODE, new Object[] { stateCode });
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllDistictByStateCode: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getDistrictByStateCode;
	}

}
