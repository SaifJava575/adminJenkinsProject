package com.master.bean;

/**
 * The model class for the sending otp via message.
 * 
 */

public class SendViaSMSModel {

	private String smsContent;

	private String toSms;

	private String templateId;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getToSms() {
		return toSms;
	}

	public void setToSms(String toSms) {
		this.toSms = toSms;
	}

}