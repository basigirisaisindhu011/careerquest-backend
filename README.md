# CareerQuest Kids

A Dream Career Discovery Platform that helps children explore careers through quizzes, recommendations, games, and personalized roadmaps.

## Features

- User Registration & Login
- JWT Authentication
- Career Management
- Interest Assessment Quiz
- Career Recommendation Engine
- Quiz History Tracking
- Parent Dashboard (In Progress)

## Tech Stack

Backend:
- Java 17
- Spring Boot
- Spring Security
- JWT
- JPA/Hibernate
- MySQL

Tools:
- Maven
- Git
- GitHub

## API Endpoints

Authentication
POST /api/users/register
POST /api/users/login

Careers
GET /api/careers
POST /api/careers

Quiz
GET /api/quiz
POST /api/quiz
POST /api/quiz/submit

Results
GET /api/quiz/results/{email}
