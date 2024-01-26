package com.master.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.bean.Response;
import com.master.entity.MasterUserDetails;
import com.master.service.IMasterUserDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterUserDetailsController {

	@Autowired
	private IMasterUserDetailsService userDetailsService;

	@PostMapping("/save-user-details")
	public @ResponseBody Response<?> createUserDetails(@RequestBody MasterUserDetails masterUserDetails) {
		String savedStatus = "";
		try {
			savedStatus = userDetailsService.createUserDetails(masterUserDetails);
			if (savedStatus == "userAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" MasterUserDetails already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK),
						"MasterUserDetails created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

	@PostMapping(value = "/searchuserdetailsforcreate/{currentPage}")
	public @ResponseBody PaginationModel searchMasterUserDetails(PaginationModel paginationModel,
			@PathVariable("currentPage") Integer currentPage, @RequestBody CommonSearchBean searchBean) {
		PaginationModel searchDepotName = null;
		try {
			searchDepotName = userDetailsService.searchMasterUserDetails(paginationModel, currentPage, searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDepotName;
	}

	@GetMapping(value = "/getUserDetails/{userId}")
	public @ResponseBody List<?> MasterUserDetailsById(@PathVariable BigInteger userId) {
		List<?> searchUserDetails = null;
		try {
			searchUserDetails = userDetailsService.MasterUserDetailsById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchUserDetails;
	}

	@PutMapping(value = "/update-user-details")
	public @ResponseBody Response<?> updateUserDetaile(@RequestBody MasterUserDetails userDetails) {
		String savedStatus = "";
		try {
			savedStatus = userDetailsService.updateUserDetaile(userDetails);
			if (savedStatus == "userNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"MasterUserDetails  name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating MasterUserDetails name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

}
