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
import com.master.entity.MasterTaluk;
import com.master.service.IMasterTalukService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterTalukController {
	
	@Autowired
	private IMasterTalukService talukService;
	
	@PostMapping("/add-taluk")
	public @ResponseBody Response<?> createTaluk(@RequestBody MasterTaluk masterTaluk) {
		String savedStatus = "";
		try {
			savedStatus = talukService.createTaluk(masterTaluk);
			if (savedStatus == "talukAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" MasterTaluk already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "MasterTaluk created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/searchtaluk/{currentPage}")
	public @ResponseBody PaginationModel searchMasterTaluk(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchTalukName = null;
		try {
			searchTalukName = talukService.searchMasterTaluk(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchTalukName;
	}
	
	@GetMapping(value = "/get-all-taluk-by-id")
	public @ResponseBody List<?> getAllTalukByTalukCode(@RequestParam Integer talukCode) {
		List<?> getAllTalukById = null;
		try {
			getAllTalukById =talukService. getAllTalukByTalukCode(talukCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllTalukById;
	}
	
	@GetMapping(value = "/get-all-taluk-by-district-id")
	public @ResponseBody List<?> getAllTalukByDistrictCode(@RequestParam Integer districtCode) {
		List<?> getAllTalukById = null;
		try {
			getAllTalukById =talukService. getAllTalukByDistrictCode(districtCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllTalukById;
	}
	
	@PutMapping(value = "/update-taluk")
	public @ResponseBody Response<?> updateMasterTaluk(@RequestBody MasterTaluk masterTaluk) {
		String savedStatus = "";
		try {
			savedStatus = talukService.updateMasterTaluk(masterTaluk);
			if (savedStatus == "talukNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Master District  name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Master District name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
}
