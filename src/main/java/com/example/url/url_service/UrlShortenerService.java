package com.example.url.url_service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.url.url_entity.UrlMapping;
import com.example.url.url_repository.URLMappingRepository;
import com.example.url.url_service_implements.UrlServiceImplements;

@Service
public class UrlShortenerService  implements UrlServiceImplements{

	@Autowired
	private URLMappingRepository urlMappingRepository;

	public String shortenUrl(String originalUrl) {
		// Implement URL shortening algorithm (e.g., generate a random string)
		String shortUrl = generateShortUrl();

		// Save mapping in the database
		UrlMapping urlMapping = new UrlMapping();
		urlMapping.setOriginalUrl(originalUrl);
		urlMapping.setShortUrl(shortUrl);
		urlMappingRepository.save(urlMapping);

		return shortUrl;
	}

	public String getOriginalUrl(String shortUrl) {
		UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
		return urlMapping != null ? urlMapping.getOriginalUrl() : null;
	}

	// Implement method to generate short URLs
	private String generateShortUrl() {
		// Your implementation here
		final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom secureRandom = new SecureRandom();

		// StringBuilder to store the generated string
		StringBuilder sb = new StringBuilder(4);

		// Generate characters until the length reaches 4
		for (int i = 0; i < 4; i++) {
			// Append a random character from the character set
			sb.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
		}
		return sb.toString();
	}
}
