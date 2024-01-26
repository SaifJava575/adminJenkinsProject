package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterZone;

public interface IMasterZoneService {

	public String createZone(MasterZone masterZone);

	public PaginationModel searchZone(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getAllZone();

	public List<?> getZoneeByIdList(Integer zoneId);
	public List<?> getAllZoneByHeadOfficeId(Integer headOfficeId);

	public String updateZoneOffice(MasterZone masterZone);

}
