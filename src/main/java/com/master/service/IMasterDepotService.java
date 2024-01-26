package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterDepot;

public interface IMasterDepotService {

	public String createDepot(MasterDepot masterDepot);

	public PaginationModel searchMasterDepot(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getDepotById(Integer depotId);

	public String updateMasterDepot(MasterDepot masterDepot);

	public List<?> getDepotByRegionId(Integer regionId);
}
