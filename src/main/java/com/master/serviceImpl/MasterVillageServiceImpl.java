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
import com.master.entity.MasterVillage;
import com.master.entity.MasterZone;
import com.master.service.IMasterVillageService;

@Service
public class MasterVillageServiceImpl implements IMasterVillageService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterVillageServiceImpl.class);

	@Override
	@Transactional
	public String createVillage(MasterVillage masterVillage) {
		logger.info("create MasterVillage called: {} " + masterVillage.getVillageName());
		String status = "";
		String whereCondition = " and villageName= '" + masterVillage.getVillageName() + "'";
		List<?> existingVillageName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_VILLAGE + whereCondition,
				new Object[] {});
		if (existingVillageName != null && existingVillageName.size() > 0) {
			status = "talukAlreadyExist";
			logger.warn("MasterVillage with the name : '{}'" + masterVillage.getVillageName() + " already exist");
		} else {
			try {
				masterVillage.setActiveFlag(true);
				logger.info(masterVillage.toString());
				iGenericDao.save(masterVillage);
				status = "savedSucessFully";
				logger.info(status + " " + masterVillage.getVillageName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterVillage: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	@Override
	@Transactional
	public PaginationModel searchMasterVillage(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterVillage list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchState = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and mv.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getVillageName() != null && !searchBean.getVillageName().equals(""))
				whereCondition += " and mv.villageName like '%" + searchBean.getVillageName() + "%'";

			if (searchBean.getStateCode() != null && !searchBean.getStateCode().equals(""))
				whereCondition += " and mv.stateCode ='" + searchBean.getStateCode() + "'";

			if (searchBean.getDistrictCode() != null && searchBean.getDistrictCode() != 0)
				whereCondition += " and mv.districtCode ='" + searchBean.getDistrictCode() + "'";

			if (searchBean.getTalukCode() != null && searchBean.getTalukCode() != 0)
				whereCondition += " and mv.talukCode ='" + searchBean.getTalukCode() + "'";

			String query = JavaConstant.SEARCH_MASTER_VILLAGE;
			query += whereCondition;

			searchState = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching searchMasterTaluk: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchState;
	}

	@Override
	public List<?> getAllVillageByVillageCode(Integer villageCdoe) {
		logger.info("fetching getAllVillageByVillageCode  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getAllVillageByVillageCode = null;
		try {
			getAllVillageByVillageCode = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_VILLAGE_BY_ID,
					new Object[] { villageCdoe });
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllVillageByVillageCode: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getAllVillageByVillageCode;
	}

	@Override
	@Transactional
	public String updateMasterVillage(MasterVillage masterVillage) {
		String updateStatus = "";
		List<MasterZone> fetchMasterVillageName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_MASTER_VILLAGE_BY_ID,
				new Object[] { masterVillage.getVillageCode() });
		if (fetchMasterVillageName.size() > 0) {
			iGenericDao.update(masterVillage);
			logger.info(" Master Village  Name updated: '{}'" + masterVillage.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterVillage.getVillageName());
		} else

		{
			logger.warn("MasterVillage Name: '{}' " + masterVillage.getVillageName() + " and villageCode : "
					+ masterVillage.getVillageCode() + " not available!");
			updateStatus = "villageNameNotExist";
		}
		return updateStatus;
	}

}
