package com.sud.shorturl.services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.Hashing;
import com.sud.shorturl.model.Url;
import com.sud.shorturl.model.Urldto;
import com.sud.shorturl.repository.UrlRepository;

import io.micrometer.common.util.StringUtils;

@Component
public class UrlServiceImpl implements UrlService {
	@Autowired
	private UrlRepository repository;

	@Override
	public Url generateShortUrl(Urldto urldto) {
		if (StringUtils.isNotEmpty(urldto.getUrl())) {
			String encodedUrl = encodeUrl(urldto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setOriginalUrl(urldto.getUrl());
			urlToPersist.setShortUrl(encodedUrl);
			urlToPersist
					.setExpirationDate(getExpirationDate(urldto.getExpirationDate(), urlToPersist.getCreationDate()));
			Url urlToRet = persistShortUrl(urlToPersist);
			
			if(urlToRet != null) {
				return urlToRet;
			}
			
			return null;
		}
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
		if (StringUtils.isBlank(expirationDate)) {
			return creationDate.plusSeconds(60);
		}
		LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
		return expirationDateToRet;
	}

	private String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32().hashString(time.toString(), StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	@Override
	public Url persistShortUrl(Url url) {
		Url urlToRet = repository.save(url);
		return urlToRet;
	}

	@Override
	public Url getEncodedUrl(String url) {
		Url urlToRet = repository.findByShortUrl(url);
		return urlToRet;
	}

	@Override
	public void deleteShortUrl(Url url) {
		repository.delete(url);

	}

}
