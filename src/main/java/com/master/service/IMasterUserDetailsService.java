package com.master.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.entity.MasterUserDetails;

public interface IMasterUserDetailsService {

	String createUserDetails(MasterUserDetails masterUserDetails);

	PaginationModel searchMasterUserDetails(PaginationModel paginationModel, Integer currentPage,
			CommonSearchBean searchBean);

	public List<?> MasterUserDetailsById(@PathVariable BigInteger userId);
	
	String updateUserDetaile( MasterUserDetails userDetails);
}
