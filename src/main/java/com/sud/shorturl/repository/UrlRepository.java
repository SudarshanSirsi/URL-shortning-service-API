package com.sud.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sud.shorturl.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
	public Url findByShortUrl(String shortUrl);
}
