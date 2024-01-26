package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterDesignation;

public interface IMasterDesigantion {

	public String createDesinationMaster(MasterDesignation masterDesignation);

	public PaginationModel searchDesignation(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getAllDesination(Integer designationId);

	public List<?> getAllDesinationList();

	public String updateDesignation(MasterDesignation masterDesignation);
}
