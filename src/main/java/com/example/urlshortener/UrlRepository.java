package com.example.urlshortener;

import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<ShortenedUrl, String> {

}
