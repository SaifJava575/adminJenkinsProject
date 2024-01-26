package com.master.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.master.bean.SendViaEmailModel;
import com.master.bean.SendViaSMSModel;
import com.master.service.ICommonFunctionalityService;

@Service
public class CommonFunctionalityServiceImpl implements ICommonFunctionalityService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private VelocityEngine velocityEngine;

	@Override
	public String UploadFile(String fileprefix, MultipartFile inputFile) {
		String file_path = null;
		if (inputFile != null && !inputFile.equals("")) {
			if (!inputFile.isEmpty()) {
				try {
					Properties prop = new Properties();
					prop.load(new FileInputStream(
							new File(getClass().getClassLoader().getResource("application.properties").getFile())));
					String thisYear = new SimpleDateFormat("yyyy").format(new Date());
					String thisMonth = new SimpleDateFormat("MM").format(new Date());
					String thisDate = new SimpleDateFormat("dd").format(new Date());
					String originalFilename = inputFile.getOriginalFilename();
					file_path = "/uploaded/" + thisYear + "/" + thisMonth + "-" + thisYear + "/" + thisDate + "-"
							+ thisMonth + "-" + thisYear + "/" + "" + fileprefix + "" + new Date().getTime() + "."
							+ FilenameUtils.getExtension(originalFilename);
					File destinationFile = new File(prop.getProperty("infolnet.upload.savepath") + "" + file_path);
					if (!destinationFile.exists()) {
						if (destinationFile.mkdirs()) {
							inputFile.transferTo(destinationFile);
						}
					} else {
						inputFile.transferTo(destinationFile);
					}
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}
		return file_path;

	}

	@Override
	public String encryptPassword(String salt, String password) throws Exception {
		String generatedPassword = null;
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			sha256_HMAC.init(new SecretKeySpec(salt.getBytes(), "HmacSHA256"));
			generatedPassword = new String(Base64.encodeBase64(sha256_HMAC.doFinal(password.getBytes())));
		} catch (Exception e) {
			System.out.println("Error");
		} finally {
		}
		return generatedPassword;
	}

	@Override
	public boolean sendEmails(String emailSubject, String templatePath, Map<String, Object> model, String to, String cc,
			String bcc) {
		try {
			SendViaEmailModel mailobj = new SendViaEmailModel();
			mailobj.setToMail(to);
			mailobj.setCcEmail(cc);
			mailobj.setBccEmail(bcc);
			mailobj.setSubject(emailSubject);
			model.put("sSysDate", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			model.put("sSysTime", new SimpleDateFormat("HH:mm:ss").format(new Date()));

			model.put("HelpDeskName", "FCI Team");
			model.put("HelpDeskEmailID", "logicsoft120@gmail.com");
			model.put("HelpDeskMobileNo", "1800112100");
			model.put("CopyRightName", "FoSCoS");
			model.put("CopyRightYear", "2018");
			mailobj.setMailContent(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, model));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			HttpEntity entity = new HttpEntity(mailobj, headers);
			// TO-DO <--WILL BE CONNECT LAB_COMMUNICATION SERVICE-->
			restTemplate.exchange("http://COMMUNICATION-SERVICE" + "/saveemails", HttpMethod.POST, entity,
					String.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendSMS(String templatePath, Map<String, Object> model, String to, String templateId) {
		try {
			SendViaSMSModel smsObj = new SendViaSMSModel();
			smsObj.setToSms(to);
			smsObj.setTemplateId(templateId);
			model.put("HelpDeskName", "FCI Team");
			smsObj.setSmsContent(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, model));
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			@SuppressWarnings({ "rawtypes", "unchecked" })
			HttpEntity entity = new HttpEntity(smsObj, headers);
			//TO-DO <--WILL BE CONNECT LAB_COMMUNICATION SERVICE-->
			//restTemplate.exchange("http://COMMUNICATION-SERVICE/savesms", HttpMethod.POST, entity, String.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
