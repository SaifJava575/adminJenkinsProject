package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterRegion;

public interface IMasterRegionService {
	public String createRegion(MasterRegion masterRegion);

	public PaginationModel searchMasterRegion(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getRegionById(Integer regionId);
	
	public List<?> getAllRegionByZoneId(Integer zoneId); 

	public String updateMasterRegion(MasterRegion masterRegion);
}
