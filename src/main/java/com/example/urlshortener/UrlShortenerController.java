package com.example.urlshortener;

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

    @GetMapping("/shorten")
    public String shortenUrl(@RequestParam String url) {
        return urlShortenerService.shortenUrl(url);
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
}
