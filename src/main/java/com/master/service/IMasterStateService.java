package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterState;

public interface IMasterStateService {

	public String createState(MasterState masterState);
	public PaginationModel searchMasterState(PaginationModel paginationModel, Integer currentPage, CommonSearchBean searchBean);

	public List<?> getStateById( Integer stateId);
	public List<?> getAllState();
	public String updateMasterState( MasterState masterState);
}
