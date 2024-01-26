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
import com.master.entity.MasterDesignation;
import com.master.service.IMasterDesigantion;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterDesgnationController {
	@Autowired
	private IMasterDesigantion desgService;
	
	@PostMapping("/create-designation")
	public @ResponseBody Response<?> createDesinationMaster(@RequestBody MasterDesignation masterDesignation) {
		String savedStatus = "";
		try {
			savedStatus = desgService.createDesinationMaster(masterDesignation);
			if (savedStatus == "designationAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" Desination Master already exist please enter valid inpiut");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), " DesinationMaster created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping("/searchdesignation/{currentPage}")
	public @ResponseBody PaginationModel searchDesignation(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchDesignation = null;
		try {
			searchDesignation = desgService.searchDesignation(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDesignation;
	}
	
	@GetMapping(value = "/get-all-designation-by-id")
	public @ResponseBody List<?> getAllDesination(@RequestParam Integer designationId) {
		List<?> getAllDesignation = null;
		try {
			getAllDesignation = desgService.getAllDesination(designationId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllDesignation;
	}
	
	@GetMapping(value = "/designation-list")
	public @ResponseBody List<?> getAllDesinationList() {
		List<?> getAllDesignation = null;
		try {
			getAllDesignation = desgService.getAllDesinationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllDesignation;
	}
	
	@PutMapping(value = "/update-designation")
	public @ResponseBody Response<?> updateDesignation(@RequestBody MasterDesignation masterDesignation) {
		String savedStatus = "";
		try {
			savedStatus = desgService.updateDesignation(masterDesignation);
			if (savedStatus == "designationNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"MasterDesignation is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating MasterDesignation");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

}
