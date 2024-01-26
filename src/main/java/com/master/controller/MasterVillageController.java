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
import com.master.entity.MasterVillage;
import com.master.service.IMasterVillageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterVillageController {

	@Autowired
	private IMasterVillageService villageService;
	
	@PostMapping("/add-village")
	public @ResponseBody Response<?> createVillage(@RequestBody MasterVillage masterVillage) {
		String savedStatus = "";
		try {
			savedStatus = villageService.createVillage(masterVillage);
			if (savedStatus == "talukAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" MasterVillage already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "MasterVillage created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/searchVillage/{currentPage}")
	public @ResponseBody PaginationModel searchMasterVillage(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchTalukName = null;
		try {
			searchTalukName = villageService.searchMasterVillage(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchTalukName;
	}
	
	@GetMapping(value = "/get-all-village-by-id")
	public @ResponseBody List<?> getAllVillageByVillageCode(@RequestParam Integer villageCdoe) {
		List<?> getAllVillageByVillageCode = null;
		try {
			getAllVillageByVillageCode =villageService. getAllVillageByVillageCode(villageCdoe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllVillageByVillageCode;
	}
	
	
	@PutMapping(value = "/update-village")
	public @ResponseBody Response<?> updateMasterVillage(@RequestBody MasterVillage masterVillage) {
		String savedStatus = "";
		try {
			savedStatus = villageService.updateMasterVillage(masterVillage);
			if (savedStatus == "villageNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"MasterVillage  name is not exist please enter valid input");
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
