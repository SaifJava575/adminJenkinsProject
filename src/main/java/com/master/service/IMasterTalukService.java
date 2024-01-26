package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterTaluk;

public interface IMasterTalukService {

	public String createTaluk(MasterTaluk masterTaluk);

	public PaginationModel searchMasterTaluk(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getAllTalukByTalukCode(Integer talukCode);
	
	public List<?> getAllTalukByDistrictCode( Integer districtCode);
	
	public String updateMasterTaluk( MasterTaluk masterTaluk);
}
