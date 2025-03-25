package com.example.urlshortener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "ShortenedURL", timeToLive = 86400)
public class ShortenedUrl {
    @Id
    private String id;
    private String originalUrl;
}
