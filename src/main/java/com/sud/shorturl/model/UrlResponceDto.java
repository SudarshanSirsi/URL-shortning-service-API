package com.sud.shorturl.model;

import java.time.LocalDateTime;

public class UrlResponceDto {
	private String originalUrl;
	private String shortUrl;
	private LocalDateTime expirationDate;
	public UrlResponceDto(String originalUrl, String shortUrl, LocalDateTime expirationDate) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.expirationDate = expirationDate;
	}
	
	public UrlResponceDto() {
		super();
	}

	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Override
	public String toString() {
		return "UrlResponceDto [originalUrl=" + originalUrl + ", shortUrl=" + shortUrl + ", expirationDate="
				+ expirationDate + "]";
	}
	
}
