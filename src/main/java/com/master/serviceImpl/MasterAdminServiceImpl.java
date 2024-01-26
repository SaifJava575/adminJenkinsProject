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
import com.master.entity.MasterHeadOffice;
import com.master.service.IMasterAdminService;

@Service
public class MasterAdminServiceImpl implements IMasterAdminService {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterAdminServiceImpl.class);

	@Override
	@Transactional
	public String createHeadOffice(MasterHeadOffice masterHeadOffice) {
		logger.info("createHeadOffice called: {} " + masterHeadOffice.getHeadOfficeName());
		String status = "";
		String whereCondition = " and headOfficeName= '" + masterHeadOffice.getHeadOfficeName() + "'";
		List<?> existingHeadOffice = iGenericDao.executeDDLHQL(JavaConstant.HEAD_OFFICE_LIST + whereCondition,
				new Object[] {});
		if (existingHeadOffice != null && existingHeadOffice.size() > 0) {
			status = "headOfficeAlreadyExist";
			logger.warn("HeadOffice with the name : '{}'" + masterHeadOffice.getHeadOfficeName() + " already exist");
		} else {
			try {
				masterHeadOffice.setActiveFlag(true);
				logger.info(masterHeadOffice.toString());
				iGenericDao.save(masterHeadOffice);
				status = "savedSucessFully";
				logger.info(status + " " + masterHeadOffice.getHeadOfficeName());
			} catch (Exception e) {
				logger.error("error occured while saving HeadOffice: '{}' " + e.getCause() + " " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public PaginationModel searchHeadOffice(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching Vendor list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchHeadOffice = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and mh.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getHeadOfficeName() != null && !searchBean.getHeadOfficeName().equals(""))
				whereCondition += " and mh.headOfficeName like '%" + searchBean.getHeadOfficeName() + "%'";

			String query = JavaConstant.SEARCH_HEAD_OFFICE;
			query += whereCondition;

			searchHeadOffice = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching Vendor: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchHeadOffice;
	}

	@Override
	public List<?> getAllHeadOffice() {
		logger.info("fetching getAllHeadOffice list: " + new Timestamp(System.currentTimeMillis()));
		List<?> headOffice = null;
		try {
			headOffice = iGenericDao.executeDDLHQL(JavaConstant.HEAD_OFFICE_LIST, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllHeadOffice: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return headOffice;
	}

	@Override
	public List<?> getAllHeadOfficeById(Integer headOfficeId) {
		logger.info("fetching getAllHeadOffice list: " + new Timestamp(System.currentTimeMillis()));
		List<?> headOffice = null;
		try {
			String whereCondition = " and me.headOfficeId=" + headOfficeId;
			headOffice = iGenericDao.executeDDLHQL(JavaConstant.HEAD_OFFICE_LIST + whereCondition, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllHeadOffice: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return headOffice;
	}

	@Override
	@Transactional
	public String updateHeadOffice(MasterHeadOffice masterHeadOffice) {
		String updateStatus = "";
		List<?> fetchMasterHeadOffice = iGenericDao.executeDDLHQL(JavaConstant.HEAD_OFFICE_LIST_BY_ID,
				new Object[] { masterHeadOffice.getHeadOfficeId() });

		if (fetchMasterHeadOffice.size() > 0) {
			//MasterHeadOffice fetchMasterHeadOfficeList = (MasterHeadOffice) fetchMasterHeadOffice.get(0);
			//copyNonNullProperties(masterHeadOffice, fetchMasterHeadOfficeList);
			iGenericDao.update(masterHeadOffice);

			logger.info("masterConsumbleItem updated: '{}'" + masterHeadOffice.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterHeadOffice.getHeadOfficeId());
		} else

		{
			logger.warn("MasterHeadOffice: '{}' " + masterHeadOffice.getHeadOfficeName() + " and headOfficeId : "
					+ masterHeadOffice.getHeadOfficeId() + " not available!");
			updateStatus = "HeadOfficeNotExist";
		}
		return updateStatus;
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
}
