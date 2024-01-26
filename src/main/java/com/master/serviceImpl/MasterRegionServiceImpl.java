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
import com.master.entity.MasterRegion;
import com.master.entity.MasterZone;
import com.master.service.IMasterRegionService;

@Service
public class MasterRegionServiceImpl implements IMasterRegionService {

	@Autowired
	private IGenericDao iGenericDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MasterRegionServiceImpl.class);


	@Override
	@Transactional
	public String createRegion(MasterRegion masterRegion) {
		logger.info("create MasterRegion called: {} " + masterRegion.getRegionName());
		String status = "";
		String whereCondition = " and regionName= '" + masterRegion.getRegionName() + "'";
		List<?> existingHeadOffice = iGenericDao.executeDDLHQL(JavaConstant.MASTER_REGION + whereCondition,
				new Object[] {});
		if (existingHeadOffice != null && existingHeadOffice.size() > 0) {
			status = "regionAlreadyExist";
			logger.warn("MasterRegion with the name : '{}'" + masterRegion.getRegionName() + " already exist");
		} else {
			try {
				masterRegion.setActiveFlag(true);
				logger.info(masterRegion.toString());
				iGenericDao.save(masterRegion);
				status = "savedSucessFully";
				logger.info(status + " " + masterRegion.getRegionName());
			} catch (Exception e) {
				logger.error("error occured while saving MasterRegion: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}


	@Override
	@Transactional
	public PaginationModel searchMasterRegion(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchMasterRegion list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchHeadOffice = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and mr.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getRegionName() != null && !searchBean.getRegionName().equals(""))
				whereCondition += " and mr.regionName like '%" + searchBean.getRegionName() + "%'";

			if (searchBean.getHeadOfficeId() != null && searchBean.getHeadOfficeId() != 0)
				whereCondition += " and mr.headOfficeId =" + searchBean.getHeadOfficeId();
			
			if (searchBean.getZoneId() != null && searchBean.getZoneId() != 0)
				whereCondition += " and mr.zoneId =" + searchBean.getZoneId();

			String query = JavaConstant.SEARCH_REGION;
			query += whereCondition;

			searchHeadOffice = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching Vendor: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchHeadOffice;
	}


	@Override
	public List<?> getRegionById(Integer regionId) {
		logger.info("fetching getRegionById  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> regionList = null;
		try {
			regionList = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_REGION_BY_REGION_ID, new Object[] { regionId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getRegionById: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return regionList;
	}
	
	@Override
	public List<?> getAllRegionByZoneId(Integer zoneId) {
		logger.info("fetching getRegionByZoneId  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> regionListByZoneId = null;
		try {
			regionListByZoneId = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_REGION_BY_REGION_ZONE_ID, new Object[] { zoneId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getRegionById: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return regionListByZoneId;
	}
	
	@Override
	@Transactional
	public String updateMasterRegion(MasterRegion masterRegion) {
		String updateStatus = "";
		List<MasterZone> fetchMasterRegionName = iGenericDao.executeDDLHQL(JavaConstant.SEARCH_REGION_BY_REGION_ID,
				new Object[] { masterRegion.getRegionId() });
		if (fetchMasterRegionName.size()>0) {
			iGenericDao.update(masterRegion);
			logger.info("masterRegion Name updated: '{}'" + masterRegion.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterRegion.getRegionId());
		} else

		{
			logger.warn("MasterRegionName: '{}' " + masterRegion.getRegionName() + " and ZoneId : " + masterRegion.getRegionId()
					+ " not available!");
			updateStatus = "regionNameNotExist";
		}
		return updateStatus;
	}
}
