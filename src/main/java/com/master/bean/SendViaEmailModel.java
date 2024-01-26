package com.master.bean;

public class SendViaEmailModel{
	
		private String toMail;
		
		private String subject;
		
		private String mailContent;
		
		private String uploadFile;
		
		private String uploadFilePath;
	  
		private String ccEmail;
		
		private String bccEmail;

		public String getToMail() {
			return toMail;
		}

		public void setToMail(String toMail) {
			this.toMail = toMail;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getMailContent() {
			return mailContent;
		}

		public void setMailContent(String mailContent) {
			this.mailContent = mailContent;
		}

		public String getUploadFile() {
			return uploadFile;
		}

		public void setUploadFile(String uploadFile) {
			this.uploadFile = uploadFile;
		}

		public String getUploadFilePath() {
			return uploadFilePath;
		}

		public void setUploadFilePath(String uploadFilePath) {
			this.uploadFilePath = uploadFilePath;
		}

		public String getCcEmail() {
			return ccEmail;
		}

		public void setCcEmail(String ccEmail) {
			this.ccEmail = ccEmail;
		}

		public String getBccEmail() {
			return bccEmail;
		}

		public void setBccEmail(String bccEmail) {
			this.bccEmail = bccEmail;
		}
		
		

		
		

}
