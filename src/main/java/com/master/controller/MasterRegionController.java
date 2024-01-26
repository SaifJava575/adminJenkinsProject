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
import com.master.entity.MasterRegion;
import com.master.service.IMasterRegionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterRegionController {
	@Autowired
	private IMasterRegionService regionService;

	@PostMapping("/add-region")
	public @ResponseBody Response<?> createRegion(@RequestBody MasterRegion masterRegion) {
		String savedStatus = "";
		try {
			savedStatus = regionService.createRegion(masterRegion);
			if (savedStatus == "regionAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" Region already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "region created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/searchMasterRegion/{currentPage}")
	public @ResponseBody PaginationModel searchMasterRegion(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchZoneName = null;
		try {
			searchZoneName = regionService.searchMasterRegion(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchZoneName;
	}
	
	@GetMapping(value = "/get-all-region-by-id")
	public @ResponseBody List<?> getRegionById(@RequestParam Integer regionId) {
		List<?> getAllRegionById = null;
		try {
			getAllRegionById = regionService.getRegionById(regionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllRegionById;
	}
	
	@GetMapping(value = "/get-all-region-by-zone-id")
	public @ResponseBody List<?> getAllRegionByZoneId(@RequestParam Integer zoneId) {
		List<?> getAllRegionZoneId = null;
		try {
			getAllRegionZoneId = regionService.getAllRegionByZoneId(zoneId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllRegionZoneId;
	}

	@PutMapping(value = "/update-region")
	public @ResponseBody Response<?> updateMasterRegion(@RequestBody MasterRegion masterRegion) {
		String savedStatus = "";
		try {
			savedStatus = regionService.updateMasterRegion(masterRegion);
			if (savedStatus == "regionNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Master Region name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Region name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

}
