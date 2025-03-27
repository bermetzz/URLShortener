# URL Shortener

A simple and fast URL Shortener (bit.ly-like) built with **Spring Boot**, **Redis**, and a minimal **HTML/JS frontend**.  
Shorten long URLs, store them temporarily, and redirect with a unique hash.

![image](https://github.com/user-attachments/assets/d961f95b-0ed3-4ba1-95d7-f3dea2932086)

## Features

- Shorten any long URL via REST API or UI
- Redis-based storage with automatic expiry (TTL = 24 hours (86400 seconds))
- Fast redirection by unique token
- Clean HTML UI (no frameworks)
- Swagger UI for API testing

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data Redis
- Redis (in-memory store)
- HTML + JavaScript (for UI)
- Swagger UI (via springdoc-openapi)

## How It Works

1. POST long URL to `/api/url/shorten`
2. Server generates a short hash (e.g., `OTZkODZk`) and stores it in Redis with TTL = 86400 seconds (24 hours)
3. Client gets short URL like `http://localhost:8080/api/url/OTZkODZk`
4. When accessing that short link, user is redirected (HTTP 302) to the original URL

## Getting Started

### Prerequisites

- Java 17+
- Redis installed locally (or via Docker)
- Maven

### Clone and Run

```bash
git clone https://github.com/bermetzz/URLShortener.git
cd URLShortener.git
