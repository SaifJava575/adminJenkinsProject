package com.master.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.master.bean.Response;
import com.master.entity.MasterUserDetails;
import com.master.entity.MasterUserRoleMapping;
import com.master.service.IMasterRoleMappingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterUserRoleMappingController {

	@Autowired
	private IMasterRoleMappingService roleMappingService;

	@PostMapping("/role-assignment")
	public @ResponseBody Response<?> createUserRoleMapping(@RequestBody MasterUserRoleMapping roleMapping) {
		String savedStatus = "";
		try {
			savedStatus = roleMappingService.createUserRoleMapping(roleMapping);
			if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK),
						"MasterUserRoleMapping created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

	@GetMapping(value = "/getuserrolemapping/{userId}")
	public @ResponseBody List<?> getUserROleMapping(@PathVariable BigInteger userId) {
		List<?> getAllUserROleById = null;
		try {
			getAllUserROleById = roleMappingService.getUserROleMapping(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllUserROleById;
	}

	@DeleteMapping(value = "/deleteUserRoleMapping/{userId}/{roleId}")
	public @ResponseBody String deleteUserRoleMapping(@PathVariable BigInteger userId,@PathVariable BigInteger roleId) {
		String getAllUserROleById = null;
		try {
			getAllUserROleById = roleMappingService.deleteUserRoleMapping(userId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllUserROleById;
	}

}
