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
import com.master.entity.MasterUserRoles;
import com.master.service.IMasterUserRole;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterUserRoleController {

	@Autowired
	private IMasterUserRole userRoleService;

	@PostMapping("/add-role")
	public @ResponseBody Response<?> createUserROle(@RequestBody MasterUserRoles masterUserRoles) {
		String savedStatus = "";
		try {
			savedStatus = userRoleService.createUserROle(masterUserRoles);
			if (savedStatus == "roleAlreadyExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_MODIFIED),
						" User ROle already exist please enter valid input");
			} else if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), "User ROle created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}
	
	@PostMapping(value = "/searchrole/{currentPage}")
	public @ResponseBody PaginationModel searchUserRole(PaginationModel paginationModel, @PathVariable("currentPage") Integer currentPage,@RequestBody CommonSearchBean searchBean) {
		PaginationModel searchUserRole = null;
		try {
			searchUserRole = userRoleService.searchUserRole(paginationModel,currentPage,searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchUserRole;
	}
	
	@GetMapping(value = "/get-all-role-by-id")
	public @ResponseBody List<?> getUserROleById(@RequestParam Integer roleId) {
		List<?> getAllUserROleById = null;
		try {
			getAllUserROleById =userRoleService. getUserROleById(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllUserROleById;
	}
	
	@GetMapping(value = "/get-all-role")
	public @ResponseBody List<?> getUserROle() {
		List<?> getAllUserROleById = null;
		try {
			getAllUserROleById =userRoleService. getUserROle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllUserROleById;
	}
	
	@PutMapping(value = "/update-role")
	public @ResponseBody Response<?> updateUserROleMaster(@RequestBody MasterUserRoles masterUserRoles) {
		String savedStatus = "";
		try {
			savedStatus = userRoleService.updateUserROleMaster(masterUserRoles);
			if (savedStatus == "roleNameNotExist") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_NOT_FOUND),
						"Master User ROle  name is not exist please enter valid input");
			} else if (savedStatus == "updatedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK), savedStatus);
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST),
						"error occured while updating Master User ROle name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

}
