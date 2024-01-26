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
import com.master.entity.MasterDesignation;
import com.master.service.IMasterDesigantion;

@Service
public class MasterDesignationService implements IMasterDesigantion {

	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterDesignationService.class);

	@Override
	@Transactional
	public String createDesinationMaster(MasterDesignation masterDesignation) {
		logger.info("createDesinationMaster called: {} " + masterDesignation.getDesignationName());
		String status = "";
		String whereCondition = " and md.designationName= '" + masterDesignation.getDesignationName() + "'";
		List<?> existingDesignation = iGenericDao.executeDDLHQL(JavaConstant.MASTER_DESIGANTION + whereCondition,
				new Object[] {});
		if (existingDesignation != null && existingDesignation.size() > 0) {
			status = "designationAlreadyExist";
			logger.warn("createDesinationMaster with the name : '{}'" + masterDesignation.getDesignationName()
					+ " already exist");
		} else {
			try {
				masterDesignation.setActiveFlag(true);
				logger.info(masterDesignation.toString());
				iGenericDao.save(masterDesignation);
				status = "savedSucessFully";
				logger.info(status + " " + masterDesignation.getDesignationName());
			} catch (Exception e) {
				logger.error("error occured while saving createDesinationMaster: '{}' " + e.getCause() + " "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	@Transactional
	public PaginationModel searchDesignation(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean) {
		logger.info("fetching searchDesignation list: " + new Timestamp(System.currentTimeMillis()));
		PaginationModel searchDesignation = null;

		try {
			String whereCondition = " where 1=1 ";
			if (searchBean.getActiveFlag() != null)
				whereCondition += " and md.activeFlag =" + searchBean.getActiveFlag();

			if (searchBean.getDesignationName() != null && !searchBean.getDesignationName().equals(""))
				whereCondition += " and md.designationName like '%" + searchBean.getDesignationName() + "%'";

			String query = JavaConstant.SEARCH_DESIGANTION;
			query += whereCondition;

			searchDesignation = iGenericDao.getPaginationWithQuery(paginationModel, currentPage, query,
					new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching searchDesignation: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return searchDesignation;
	}

	@Override
	public List<?> getAllDesination(Integer designationId) {
		logger.info("fetching getAllDesination list: " + new Timestamp(System.currentTimeMillis()));
		List<?> headOffice = null;
		try {
			headOffice = iGenericDao.executeDDLHQL(JavaConstant.MASTER_DESIGANTION_BY_ID, new Object[] {designationId});
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllDesination: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return headOffice;
	}
	
	@Override
	public List<?> getAllDesinationList() {
		logger.info("fetching getAllDesination list: " + new Timestamp(System.currentTimeMillis()));
		List<?> headOffice = null;
		try {
			headOffice = iGenericDao.executeDDLHQL(JavaConstant.MASTER_DESIGANTION, new Object[] {});
		} catch (Exception e) {
			logger.error("error occcured while fetching getAllDesination: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return headOffice;
	}

	@Override
	@Transactional
	public String updateDesignation(MasterDesignation masterDesignation) {
		String updateStatus = "";
		List<?> fetchMasterDesignation = iGenericDao.executeDDLHQL(JavaConstant.MASTER_DESIGANTION_BY_ID,
				new Object[] { masterDesignation.getDesignationId() });

		if (fetchMasterDesignation.size() > 0) {
			iGenericDao.update(masterDesignation);
			logger.info("MasterDesignation updated: '{}'" + masterDesignation.toString());
			updateStatus = "updatedSucessFully";
			logger.info(updateStatus + " " + masterDesignation.getDesignationId());
		} else

		{
			logger.warn("MasterDesignation: '{}' " + masterDesignation.getDesignationName() + " and headOfficeId : "
					+ masterDesignation.getDesignationId() + " not available!");
			updateStatus = "designationNotExist";
		}
		return updateStatus;
	}

}
