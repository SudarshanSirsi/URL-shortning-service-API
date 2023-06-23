package com.sud.shorturl.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sud.shorturl.model.Url;
import com.sud.shorturl.model.UrlErrorResponceDto;
import com.sud.shorturl.model.UrlResponceDto;
import com.sud.shorturl.model.Urldto;
import com.sud.shorturl.services.UrlService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlShortingController {
	
	@Autowired
	private UrlService service;
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateShortUrl(@RequestBody Urldto urldto){
		
		Url urlToRet = service.generateShortUrl(urldto);
		
		if(urlToRet != null ) {
			
			UrlResponceDto responceDto = new UrlResponceDto();
			responceDto.setOriginalUrl(urlToRet.getOriginalUrl());
			responceDto.setShortUrl(urlToRet.getShortUrl());
			responceDto.setExpirationDate(urlToRet.getExpirationDate());
			
			return new ResponseEntity<UrlResponceDto>(responceDto, HttpStatus.OK);
		}
		
		UrlErrorResponceDto errorResponceDto = new UrlErrorResponceDto();
		errorResponceDto.setStatus("404");
		errorResponceDto.setError("There was an error processing your request please try again!!");
		
		return new ResponseEntity<UrlErrorResponceDto>(errorResponceDto, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/{shorturl}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shorturl, HttpServletResponse response) throws IOException{
		
		if(StringUtils.isEmpty(shorturl)) {
			UrlErrorResponceDto errorResponceDto = new UrlErrorResponceDto();
			errorResponceDto.setError("Invalid URL");
			errorResponceDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponceDto>(errorResponceDto, HttpStatus.OK);
			
		}
		
		Url urlToRet = service.getEncodedUrl(shorturl);
		if(urlToRet == null) {
			UrlErrorResponceDto errorResponceDto = new UrlErrorResponceDto();
			errorResponceDto.setError("URL does not exist or it might have expired");
			errorResponceDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponceDto>(errorResponceDto, HttpStatus.OK);
		}
		
		if(urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
			service.deleteShortUrl(urlToRet);
			UrlErrorResponceDto errorResponceDto = new UrlErrorResponceDto();
			errorResponceDto.setError("URL expired. Please try generating the fresh one");
			errorResponceDto.setStatus("20W0");
			return new ResponseEntity<UrlErrorResponceDto>(errorResponceDto, HttpStatus.OK);
		}
		
		response.sendRedirect(urlToRet.getOriginalUrl());
		
		return null;
		
	}
}
