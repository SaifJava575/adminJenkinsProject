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
import com.master.entity.MasterDepot;
import com.master.service.IMasterDepotService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterDepotController {

	@Autowired
	private IMasterDepotService depotService;

	@PostMapping("/add-depot")
	public @ResponseBody Response<?> createDepot(@RequestBody MasterDepot masterDepot) {
		String savedStatus = "";
		try {
			savedStatus = depotService.createDepot(masterDepot);
			if (savedStatus == "depotAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" Depot already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "Depot created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

	@PostMapping(value = "/searchdepot/{currentPage}")
	public @ResponseBody PaginationModel searchMasterDepot(PaginationModel paginationModel,
			@PathVariable("currentPage") Integer currentPage, @RequestBody CommonSearchBean searchBean) {
		PaginationModel searchDepotName = null;
		try {
			searchDepotName = depotService.searchMasterDepot(paginationModel, currentPage, searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDepotName;
	}

	@GetMapping(value = "/get-all-depot-by-id")
	public @ResponseBody List<?> getDepotById(@RequestParam Integer depotId) {
		List<?> getAllDepotById = null;
		try {
			getAllDepotById = depotService.getDepotById(depotId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllDepotById;
	}

	@GetMapping(value = "/get-all-depot-by-region-id")
	public @ResponseBody List<?> getDepotByRegionId(@RequestParam Integer regionId) {
		List<?> getAllDepotByRegionId = null;
		try {
			getAllDepotByRegionId = depotService.getDepotByRegionId(regionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllDepotByRegionId;
	}

	@PutMapping(value = "/update-depot")
	public @ResponseBody Response<?> updateMasterDepot(@RequestBody MasterDepot masterDepot) {
		String savedStatus = "";
		try {
			savedStatus = depotService.updateMasterDepot(masterDepot);
			if (savedStatus == "depotNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Master Depot  name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Master Depot name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

}
