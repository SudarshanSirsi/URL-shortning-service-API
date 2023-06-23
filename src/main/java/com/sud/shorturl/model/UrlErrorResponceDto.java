package com.sud.shorturl.model;

public class UrlErrorResponceDto {
	private String status;
	private String error;
	public UrlErrorResponceDto(String status, String error) {
		super();
		this.status = status;
		this.error = error;
	}
	
	public UrlErrorResponceDto() {
		super();
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "UrlErrorResponceDto [status=" + status + ", error=" + error + "]";
	}

}
