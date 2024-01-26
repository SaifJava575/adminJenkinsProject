package com.master.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ICommonFunctionalityService {

	public String UploadFile(String fileprefix, MultipartFile inputFile);

	public String encryptPassword(String salt, String password) throws Exception;

	public boolean sendEmails(String emailSubject, String templatePath,
			Map<String, Object> model, String to, String cc, String bcc);

	public boolean sendSMS(String templatePath,Map<String,Object> model,String to,String templateId);
}
