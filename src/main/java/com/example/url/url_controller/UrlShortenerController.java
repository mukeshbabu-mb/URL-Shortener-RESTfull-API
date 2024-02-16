package com.example.url.url_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.url.url_service.UrlShortenerService;

@RestController
public class UrlShortenerController {

	@Autowired
	private UrlShortenerService urlShortenerService;

	@PostMapping("/shorten")
	public String shortenUrl(@RequestBody String originalUrl) {
		return urlShortenerService.shortenUrl(originalUrl);
	}

	@GetMapping("/{shortUrl}")
	public String redirectToOriginalUrl(@PathVariable String shortUrl) {
		String originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
		return originalUrl != null ? "redirect:" + originalUrl : "URL not found";
	}
}
