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
import com.master.entity.MasterDepot;
import com.master.entity.MasterZone;
import com.master.service.IMasterDepotService;

@Service
public class MasterDepotServiceImpl implements IMasterDepotService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterDepotServiceImpl.class);

	@Override
	@Transactional
	public String createDepot(MasterDepot masterDepot) {
		logger.info("create MasterDepot called: {} " + masterDepot.getDepotName());
		String status = "";
		String whereCondition = " and depotName= '" + masterDepot.getDepotName() + "'";
		List<?> existingDepotName = iGenericDao.executeDDLHQL(JavaConstant.MASTER_DEPOT + whereCondition,
				new Object[] {});
		if (existingDepotName != null && existingDepotName.size() > 0) {
			status = "depotAlreadyExist";
			logger.warn("MasterDepot with the name : '{}'" + masterDepot.getDepotName() + " already exist");
		} else {
			try {
				masterDepot.setActiveFlag(true);
				logger.info(masterDepot.toString());
				iGenericDao.save(masterDepot);
				status = "savedSucessFully";
				logger.info(status + " " + masterDepot.getDepotName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterDepot: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	@Override
	@Transactional
	public PaginationModel searchMasterDepot(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterDepot list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchHeadOffice = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and md.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getDepotName() != null && !searchBean.getDepotName().equals(""))
				whereCondition += " and md.depotName like '%" + searchBean.getDepotName() + "%'";

			if (searchBean.getHeadOfficeId() != null && searchBean.getHeadOfficeId() != 0)
				whereCondition += " and md.headOfficeId =" + searchBean.getHeadOfficeId();
			
			if (searchBean.getZoneId() != null && searchBean.getZoneId() != 0)
				whereCondition += " and md.zoneId =" + searchBean.getZoneId();
			
			if (searchBean.getRegionId() != null && searchBean.getRegionId() != 0)
				whereCondition += " and md.regionId =" + searchBean.getRegionId();
			

			String query = JavaConstant.SEARCH_DEPOT;
			query += whereCondition;

			searchHeadOffice = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching Vendor: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchHeadOffice;
	}

	@Override
	public List<?> getDepotById(Integer depotId) {
		logger.info("fetching getDepotById  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> regionList = null;
		try {
			regionList = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_DEPOT_BY_DEPOT_ID, new Object[] { depotId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getDepotById: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return regionList;
	}
	
	@Override
	public List<?> getDepotByRegionId(Integer regionId) {
		logger.info("fetching getDepotByRegionId  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getDepotRegionList = null;
		try {
			getDepotRegionList = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_DEPOT_BY_REGION_ID, new Object[] { regionId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getDepotByRegionId: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getDepotRegionList;
	}

	@Override
	@Transactional
	public String updateMasterDepot(MasterDepot masterDepot) {
		String updateStatus = "";
		List<MasterZone> fetchMasterDpeotName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_DEPOT_BY_DEPOT_ID,
				new Object[] { masterDepot.getDepotId() });
		if (fetchMasterDpeotName.size()>0) {
			iGenericDao.update(masterDepot);
			logger.info("masterRegion Name updated: '{}'" + masterDepot.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterDepot.getDepotName());
		} else

		{
			logger.warn("MasterDepot Name: '{}' " + masterDepot.getDepotName() + " and depotId : " + masterDepot.getDepotId()
					+ " not available!");
			updateStatus = "depotNameNotExist";
		}
		return updateStatus;
	}
	
	
}
