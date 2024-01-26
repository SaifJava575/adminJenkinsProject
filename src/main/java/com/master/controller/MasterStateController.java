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
import com.master.entity.MasterState;
import com.master.service.IMasterStateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterStateController {

	@Autowired
	private IMasterStateService stateService;
	
	@PostMapping("/add-state")
	public @ResponseBody Response<?> createState(@RequestBody MasterState masterState) {
		String savedStatus = "";
		try {
			savedStatus = stateService.createState(masterState);
			if (savedStatus == "statetAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" State already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "state created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/getmasterstate/{currentPage}")
	public @ResponseBody PaginationModel searchMasterState(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchStateName = null;
		try {
			searchStateName = stateService.searchMasterState(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchStateName;
	}
	
	@GetMapping(value = "/get-state-by-id")
	public @ResponseBody List<?> getStateById(@RequestParam Integer stateId) {
		List<?> getAllStatetById = null;
		try {
			getAllStatetById =stateService. getStateById(stateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllStatetById;
	}
	
	@GetMapping(value = "/get-all-state")
	public @ResponseBody List<?> getAllState() {
		List<?> getAllStatet = null;
		try {
			getAllStatet =stateService. getAllState();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllStatet;
	}
	
	@PutMapping(value = "/update-state")
	public @ResponseBody Response<?> updateMasterState(@RequestBody MasterState masterState) {
		String savedStatus = "";
		try {
			savedStatus = stateService.updateMasterState(masterState);
			if (savedStatus == "stateNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Master state  name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Master state name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	

}
