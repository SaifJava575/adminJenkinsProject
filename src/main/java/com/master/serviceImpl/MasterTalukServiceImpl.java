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
import com.master.entity.MasterTaluk;
import com.master.entity.MasterZone;
import com.master.service.IMasterTalukService;

@Service
public class MasterTalukServiceImpl implements IMasterTalukService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterTalukServiceImpl.class);

	@Override
	@Transactional
	public String createTaluk(MasterTaluk masterTaluk) {
		logger.info("create MasterTaluk called: {} " + masterTaluk.getTalukName());
		String status = "";
		String whereCondition = " and talukName= '" + masterTaluk.getTalukName() + "'";
		List<?> existingDistrictName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_TALUK + whereCondition,
				new Object[] {});
		if (existingDistrictName != null && existingDistrictName.size() > 0) {
			status = "talukAlreadyExist";
			logger.warn("MasterTaluk with the name : '{}'" + masterTaluk.getTalukName() + " already exist");
		} else {
			try {
				masterTaluk.setActiveFlag(true);
				logger.info(masterTaluk.toString());
				iGenericDao.save(masterTaluk);
				status = "savedSucessFully";
				logger.info(status + " " + masterTaluk.getTalukName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterTaluk: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	@Transactional
	public PaginationModel searchMasterTaluk(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterTaluk list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchState = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and mt.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getTalukName() != null && !searchBean.getTalukName().equals(""))
				whereCondition += " and mt.talukName like '%" + searchBean.getTalukName() + "%'";

			if (searchBean.getStateCode() != null && !searchBean.getStateCode().equals(""))
				whereCondition += " and mt.stateCode ='" + searchBean.getStateCode() + "'";

			if (searchBean.getDistrictCode() != null && searchBean.getDistrictCode() != 0)
				whereCondition += " and mt.districtCode ='" + searchBean.getDistrictCode() + "'";

			String query = JavaConstant.SEARCH_MASTER_TALUK;
			query += whereCondition;

			searchState = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching searchMasterTaluk: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchState;
	}

	@Override
	public List<?> getAllTalukByTalukCode(Integer talukCode) {
		logger.info("fetching getAllTalukByTalukCode  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getTalukById = null;
		try {
			getTalukById = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_TALUK_BY_ID,
					new Object[] { talukCode });
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllTalukByTalukCode: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getTalukById;
	}

	@Override
	public List<?> getAllTalukByDistrictCode(Integer districtCode) {
		logger.info("fetching getAllTalukByDistrictCode  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getTalukByDistrictCode = null;
		try {
			getTalukByDistrictCode = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_TALUK_BY_DISTRICT_CODE,
					new Object[] { districtCode });
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllTalukByDistrictCode: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getTalukByDistrictCode;
	}

	@Override
	@Transactional
	public String updateMasterTaluk(MasterTaluk masterTaluk) {
		String updateStatus = "";
		List<MasterZone> fetchMasterStateName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_TALUK_BY_ID,
				new Object[] { masterTaluk.getTalukCode() });
		if (fetchMasterStateName.size() > 0) {
			iGenericDao.update(masterTaluk);
			logger.info("Master MasterTaluk  Name updated: '{}'" + masterTaluk.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterTaluk.getTalukName());
		} else

		{
			logger.warn("MasterDistrict Name: '{}' " + masterTaluk.getTalukName() + " and districtCode : "
					+ masterTaluk.getTalukCode() + " not available!");
			updateStatus = "talukNameNotExist";
		}
		return updateStatus;
	}

}
