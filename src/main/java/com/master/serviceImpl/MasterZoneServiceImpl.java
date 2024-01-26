package com.master.serviceImpl;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.bean.CommonSearchBean;
import com.master.bean.JavaConstant;
import com.master.bean.PaginationModel;
import com.master.dao.IGenericDao;
import com.master.entity.MasterZone;
import com.master.service.IMasterZoneService;

@Service
public class MasterZoneServiceImpl implements IMasterZoneService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterZoneServiceImpl.class);

	@Override
	@Transactional
	public String createZone(MasterZone masterZone) {
		logger.info("create Zone called: {} " + masterZone.getZoneName());
		String status = "";
		String whereCondition = " and zoneName= '" + masterZone.getZoneName() + "'";
		List<?> existingHeadOffice = iGenericDao.executeDDLHQL(JavaConstant.ZONE_LIST + whereCondition,
				new Object[] {});
		if (existingHeadOffice != null && existingHeadOffice.size() > 0) {
			status = "ZoneAlreadyExist";
			logger.warn("zone with the name : '{}'" + masterZone.getZoneName() + " already exist");
		} else {
			try {
				masterZone.setActiveFlag(true);
				logger.info(masterZone.toString());
				iGenericDao.save(masterZone);
				status = "savedSucessFully";
				logger.info(status + " " + masterZone.getZoneName());
			} catch (Exception e) {
				logger.error("error occured while saving HeadOffice: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public PaginationModel searchZone(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {

		logger.info("fetching Vendor list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchHeadOffice = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and mz.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getZoneName() != null && !searchBean.getZoneName().equals(""))
				whereCondition += " and mz.zoneName like '%" + searchBean.getZoneName() + "%'";

			if (searchBean.getHeadOfficeId() != null && searchBean.getHeadOfficeId() != 0)
				whereCondition += " and mz.headOfficeId =" + searchBean.getHeadOfficeId();

			String query = JavaConstant.ALL_ZONE_LIST;
			query += whereCondition;

			searchHeadOffice = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching Vendor: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchHeadOffice;
	}

	@Override
	public List<?> getAllZone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getZoneeByIdList(Integer zoneId) {
		logger.info("fetching getAll Zone list: " + new Timestamp(System.currentTimeMillis()));
		List<?> zoneList = null;
		try {
			zoneList = iGenericDao.executeDDLHQL(JavaConstant.ALL_ZONE_LIST_BY_ID, new Object[] { zoneId });
		} catch (Exception e) {
			logger.error("error occcured while fetching Zone: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return zoneList;
	}
	
	@Override
	public List<?> getAllZoneByHeadOfficeId(Integer headOfficeId) {
		logger.info("fetching getAll Zone By HeadOfficeId: " + new Timestamp(System.currentTimeMillis()));
		List<?> zoneByHeadOfficeId = null;
		try {
			zoneByHeadOfficeId = iGenericDao.executeDDLHQL(JavaConstant.ALL_ZONE_LIST_BY_HEAD_OFFICE_ID, new Object[] { headOfficeId });
		} catch (Exception e) {
			logger.error("error occcured while fetching Zone by HeadOfficeId: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return zoneByHeadOfficeId;
	}

	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	@Override
	@Transactional
	public String updateZoneOffice(MasterZone masterZone) {
		String updateStatus = "";
		List<MasterZone> fetchMasterZoneName = iGenericDao.executeDDLHQL(JavaConstant.ALL_ZONE_LIST_BY_ID,
				new Object[] { masterZone.getZoneId() });
		if (fetchMasterZoneName.size()>0) {
			//MasterZone fetchMasterZoneNameList = fetchMasterZoneName.get(0);
			//copyNonNullProperties(masterZone, fetchMasterZoneNameList);
			iGenericDao.update(masterZone);
			logger.info("masterConsumbleItem updated: '{}'" + masterZone.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterZone.getZoneId());
		} else

		{
			logger.warn("MasterZoneName: '{}' " + masterZone.getZoneName() + " and ZoneId : " + masterZone.getZoneId()
					+ " not available!");
			updateStatus = "ZoneNameNotExist";
		}
		return updateStatus;
	}

}
