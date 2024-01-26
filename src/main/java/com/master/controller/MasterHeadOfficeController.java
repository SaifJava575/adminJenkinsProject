package com.master.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.master.bean.CommonSearchBean;
import com.master.bean.PaginationModel;
import com.master.bean.Response;
import com.master.entity.MasterHeadOffice;
import com.master.service.IMasterAdminService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterHeadOfficeController {

	@Autowired
	private IMasterAdminService adminservice;

	/*
	 * Head office Api
	 */
	@PostMapping("/add-headoffice")
	public @ResponseBody Response<?> createHeadOffice(@RequestBody MasterHeadOffice masterHeadOffice) {
		String savedStatus = "";
		try {
			savedStatus = adminservice.createHeadOffice(masterHeadOffice);
			if (savedStatus == "headOfficeAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" headOffice already exist please enter valid inpiut");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "headOffice created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping("/searchHeadOffice/{currentPage}")
	public @ResponseBody PaginationModel searchHeadOffice(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchHeadOffice = null;
		try {
			searchHeadOffice = adminservice.searchHeadOffice(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchHeadOffice;
	}
	
	@GetMapping(value = "/get-all-headOffice")
	public @ResponseBody List<?> getAllHeadOffice() {
		List<?> getAllHeadOffice = null;
		try {
			getAllHeadOffice = adminservice.getAllHeadOffice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllHeadOffice;
	}
	
	@GetMapping(value = "/get-all-headOffice-by-id")
	public @ResponseBody List<?> getAllHeadOfficeById(@RequestParam Integer headOfficeId) {
		List<?> getAllHeadOfficeById = null;
		try {
			getAllHeadOfficeById = adminservice.getAllHeadOfficeById(headOfficeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllHeadOfficeById;
	}
	
	@PutMapping(value = "/update-headOffice")
	public @ResponseBody Response<?> updateHeadOffice(@RequestBody MasterHeadOffice masterHeadOffice) {
		String savedStatus = "";
		try {
			savedStatus = adminservice.updateHeadOffice(masterHeadOffice);
			if (savedStatus == "HeadOfficeNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Head office is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Head office");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	
}
