package com.master.service;

import java.util.List;

import com.master.entity.MasterUserJuricdictionMapping;

public interface IMasterJuricdictionMappingService {

	String createUserJuricdictionMapping(MasterUserJuricdictionMapping masterUserJuricdictionMapping);

	List<?> getUserJurisdictionMapping(Integer userId);

	String deleteUserJurisdictionMapping(Integer mappingId);

}
