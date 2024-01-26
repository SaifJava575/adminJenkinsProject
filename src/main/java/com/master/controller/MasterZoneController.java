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
import com.master.entity.MasterZone;
import com.master.service.IMasterZoneService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterZoneController {

	@Autowired
	private IMasterZoneService zoneService;

	/*
	 * Zone Api
	 */
	@PostMapping("/add-zone")
	public @ResponseBody Response<?> createZone(@RequestBody MasterZone masterZone) {
		String savedStatus = "";
		try {
			savedStatus = zoneService.createZone(masterZone);
			if (savedStatus == "ZoneAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" zone already exist please enter valid inpiut");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "zone created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/searchMasterZone/{currentPage}")
	public @ResponseBody PaginationModel searchZone(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchZoneName = null;
		try {
			searchZoneName = zoneService.searchZone(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchZoneName;
	}
	
	@GetMapping(value = "/get-all-zone-by-id")
	public @ResponseBody List<?> getZoneeByIdList(@RequestParam Integer zoneId) {
		List<?> getAllZoneById = null;
		try {
			getAllZoneById = zoneService.getZoneeByIdList(zoneId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllZoneById;
	}
	
	@GetMapping(value = "/get-all-zone-by-headoffice-id")
	public @ResponseBody List<?> getAllZoneByHeadOfficeId(@RequestParam Integer headOfficeId) {
		List<?> getAllZoneByHeadOfficeId = null;
		try {
			getAllZoneByHeadOfficeId = zoneService.getAllZoneByHeadOfficeId(headOfficeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllZoneByHeadOfficeId;
	}
	
	@PutMapping(value = "/update-zone")
	public @ResponseBody Response<?> updateZoneOffice(@RequestBody MasterZone masterZone) {
		String savedStatus = "";
		try {
			savedStatus = zoneService.updateZoneOffice(masterZone);
			if (savedStatus == "ZoneNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Zone name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Zone name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

}
