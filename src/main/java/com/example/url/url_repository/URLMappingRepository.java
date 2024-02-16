package com.example.url.url_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.url.url_entity.UrlMapping;

@Repository
public interface URLMappingRepository extends JpaRepository<UrlMapping, Long> {

	@Query("SELECT u FROM UrlMapping u WHERE u.shortUrl = :shortUrl")
	UrlMapping findByShortUrl(String shortUrl);
}
