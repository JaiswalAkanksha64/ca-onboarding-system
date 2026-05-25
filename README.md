# CA Onboarding Automation System

A Java Spring Boot application that automates the 
Community Ambassador (CA) Onboarding Process for 
Industry Academia Community (IAC).

## Features
- Automated UTM link generation for each CA
- Welcome email automation via Gmail SMTP
- Performance Dashboard to view all CAs
- Duplicate email prevention
- Input validation (Frontend + Backend)

## Tech Stack
- Java 21
- Spring Boot 3.5
- MySQL 8.0
- Spring Mail
- HTML/CSS/JavaScript

## Setup
1. Clone the repository
2. Create MySQL database: `ca_onboarding`
3. Copy `application.properties.example` to `application.properties`
4. Fill in your credentials
5. Run the application

## API Endpoints
- POST /api/onboard - Register new CA
- GET /api/cas - Get all CAs
- GET /api/cas/{id} - Get CA by ID
- GET /api/dashboard - Dashboard stats
- DELETE /api/cas/{id} - Delete CA
