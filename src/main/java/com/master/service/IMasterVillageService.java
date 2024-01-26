package com.master.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterVillage;

public interface IMasterVillageService {
	
	public String createVillage( MasterVillage masterVillage);
	public PaginationModel searchMasterVillage(PaginationModel paginationModel,  Integer currentPage, CommonSearchBean searchBean);
 
	public List<?> getAllVillageByVillageCode( Integer villageCdoe);
	public String updateMasterVillage(@RequestBody MasterVillage masterVillage);
}
