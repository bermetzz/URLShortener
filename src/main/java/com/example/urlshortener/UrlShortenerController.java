package com.example.urlshortener;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody UrlRequest request) {
        return urlShortenerService.shortenUrl(request.getUrl());
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String hash) {
        String originalUrl = urlShortenerService.getOriginalUrl(hash);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, originalUrl)
                .build();
    }

    @GetMapping("/{hash}/info")
    public String getOriginalUrlInfo(@PathVariable String hash) {
        return urlShortenerService.getOriginalUrl(hash);
    }
}
