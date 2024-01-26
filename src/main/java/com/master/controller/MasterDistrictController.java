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
import com.master.entity.MasterDistrict;
import com.master.service.IMasterDistrictService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterDistrictController {

	@Autowired
	private IMasterDistrictService districtService;
	
	@PostMapping("/add-district")
	public @ResponseBody Response<?> createDistrict(@RequestBody MasterDistrict masterDistrict) {
		String savedStatus = "";
		try {
			savedStatus = districtService.createDistrict(masterDistrict);
			if (savedStatus == "DistrictAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" MasterDistrict already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "MasterDistrict created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/searchDistrict/{currentPage}")
	public @ResponseBody PaginationModel searchMasterDistrict(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchDistrictName = null;
		try {
			searchDistrictName = districtService.searchMasterDistrict(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDistrictName;
	}
	
	@GetMapping(value = "/get-all-district-by-id")
	public @ResponseBody List<?> getAllDistictById(@RequestParam Integer districtCode) {
		List<?> getAllDistrictById = null;
		try {
			getAllDistrictById =districtService. getAllDistictById(districtCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllDistrictById;
	}
	
	@GetMapping(value = "/get-all-district-by-state-id")
	public @ResponseBody List<?> getAllDistictByStateCode(@RequestParam String stateCode) {
		List<?> getAllDistrictByStateCode = null;
		try {
			getAllDistrictByStateCode =districtService. getAllDistictByStateCode(stateCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllDistrictByStateCode;
	}
	
	@PutMapping(value = "/update-district")
	public @ResponseBody Response<?> updateMasterDistrict(@RequestBody MasterDistrict masterDistrict) {
		String savedStatus = "";
		try {
			savedStatus = districtService.updateMasterDistrict(masterDistrict);
			if (savedStatus == "districtNameNotExist") {
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
