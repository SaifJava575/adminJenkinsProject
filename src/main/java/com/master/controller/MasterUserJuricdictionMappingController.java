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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.master.bean.Response;
import com.master.entity.MasterUserJuricdictionMapping;
import com.master.service.IMasterJuricdictionMappingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MasterUserJuricdictionMappingController {

	@Autowired
	private IMasterJuricdictionMappingService iMasterJuricdictionMappingService;

	@PostMapping("/saveUserJurisdictionMapping")
	public @ResponseBody Response<?> createUserJuricdictionMapping(
			@RequestBody MasterUserJuricdictionMapping masterUserJuricdictionMapping) {
		String savedStatus = "";
		try {
			savedStatus = iMasterJuricdictionMappingService
					.createUserJuricdictionMapping(masterUserJuricdictionMapping);
			if (savedStatus == "savedSucessFully") {
				return new Response<>(String.valueOf(HttpServletResponse.SC_OK),
						"MasterUserJuricdictionMapping created sucessfully");
			} else {
				return new Response<>(String.valueOf(HttpServletResponse.SC_BAD_REQUEST), " something went wrong!! ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(String.valueOf(HttpServletResponse.SC_EXPECTATION_FAILED), e.toString());
		}
	}

	@GetMapping(value = "/getUserJurisdictionMapping/{userId}")
	public @ResponseBody List<?> getUserJurisdictionMapping(@PathVariable Integer userId) {
		List<?> getAllJuricdictionMappingUserList = null;
		try {
			getAllJuricdictionMappingUserList = iMasterJuricdictionMappingService.getUserJurisdictionMapping(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllJuricdictionMappingUserList;
	}
	
	@DeleteMapping(value = "/deleteUserJurisdictionMapping")
	public @ResponseBody String deleteUserJurisdictionMapping(@RequestParam Integer mappingId) {
		String deleteJuricdtionMappingByUserMappingId = null;
		try {
			deleteJuricdtionMappingByUserMappingId = iMasterJuricdictionMappingService.deleteUserJurisdictionMapping(mappingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteJuricdtionMappingByUserMappingId;
	}

}
