package com.sud.shorturl.model;

public class Urldto {
	private String url;
	private String expirationDate;
	public Urldto(String url, String expirationDate) {
		super();
		this.url = url;
		this.expirationDate = expirationDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Override
	public String toString() {
		return "Urldto [url=" + url + ", expirationDate=" + expirationDate + "]";
	}
	

}
