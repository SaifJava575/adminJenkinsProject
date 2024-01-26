package com.master.serviceImpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.bean.JavaConstant;
import com.master.dao.IGenericDao;
import com.master.entity.MasterUserJuricdictionMapping;
import com.master.service.IMasterJuricdictionMappingService;

@Service
public class MasterJuricdictionMappingServiceImpl implements IMasterJuricdictionMappingService {


	@Autowired
	private IGenericDao iGenericDao;

	private static final Logger logger = LoggerFactory.getLogger(MasterJuricdictionMappingServiceImpl.class);

	
	@Override
	@Transactional
	public String createUserJuricdictionMapping(MasterUserJuricdictionMapping masterUserJuricdictionMapping) {
		logger.info("create createUserJuricdictionMapping called: {} ");
		String status = "";
		try {
			masterUserJuricdictionMapping.setActiveFlag(true);
			logger.info(masterUserJuricdictionMapping.toString());
			iGenericDao.save(masterUserJuricdictionMapping);
			status = "savedSucessFully";
		} catch (Exception e) {
			logger.error(
					"error occured while saving createUserJuricdictionMapping: '{}' " + e.getCause() + " " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public List<?> getUserJurisdictionMapping(Integer userId) {
		logger.info("fetching getUserJurisdictionMapping  list: " + new Timestamp(System.currentTimeMillis()));
		List<?> getUserJuricdictionList = null;
		try {
			getUserJuricdictionList = iGenericDao.executeDDLHQL(JavaConstant.USER_JURICDICTION_MAPPING_BY_USER_ID, new Object[] { userId });
		} catch (Exception e) {
			logger.error("error occcured while fetching getUserJurisdictionMapping: " + e.getMessage() + e.getCause());
			e.printStackTrace();
		}
		return getUserJuricdictionList;
	}
	
	@Override
	@Transactional
	public String deleteUserJurisdictionMapping(Integer mappingId) {
		String status = "";
		try {
			iGenericDao.executeDMLHQL(JavaConstant.DELETE_USER_JURICDICTION_MAPPPING, new Object[] { mappingId });
			status = "DELETE";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
