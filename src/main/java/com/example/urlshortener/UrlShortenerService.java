package com.example.urlshortener;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {
        String hash = generateHash();
        Optional<ShortenedUrl> existing = urlRepository.findById(hash);
        if (existing.isPresent()) {
            return hash;
        }
        ShortenedUrl shortenedUrl = new ShortenedUrl(hash, originalUrl);
        urlRepository.save(shortenedUrl);
        return hash;
    }

    private String generateHash() {
        String uuid = UUID.randomUUID().toString();
        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(
                uuid.getBytes(StandardCharsets.UTF_8));
        return base64.substring(0, 8);
    }

    public String getOriginalUrl(String hash) {
        return urlRepository.findById(hash).map(ShortenedUrl::getOriginalUrl).orElse(null);
    }
}
