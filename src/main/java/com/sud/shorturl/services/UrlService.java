package com.sud.shorturl.services;

import org.springframework.stereotype.Service;

import com.sud.shorturl.model.Url;
import com.sud.shorturl.model.Urldto;

@Service
public interface UrlService {

	public Url generateShortUrl(Urldto urldto);
	public Url persistShortUrl(Url url);
	public Url getEncodedUrl(String url);
	public void deleteShortUrl(Url url);
}
