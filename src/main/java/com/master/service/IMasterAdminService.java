package com.master.service;

import java.util.List;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterHeadOffice;

public interface IMasterAdminService {

	public String createHeadOffice(MasterHeadOffice masterHeadOffice);

	public PaginationModel searchHeadOffice(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> getAllHeadOffice();

	public List<?> getAllHeadOfficeById(Integer headOfficeId);

	public String updateHeadOffice( MasterHeadOffice masterHeadOffice);
}
