package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterDistrict;

public interface IMasterDistrictService {

	public String createDistrict(MasterDistrict masterDistrict);

	public PaginationModel searchMasterDistrict(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getAllDistictById(Integer districtCode);

	public List<?> getAllDistictByStateCode( String stateCode);

	public String updateMasterDistrict(MasterDistrict masterDistrict);
}
