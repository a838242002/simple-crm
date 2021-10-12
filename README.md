# CRM Project

#### Tech Stack : 
- Java Version : 17
- Web Framework : Spring Boot
- ORM Framework : Spring JPA + Hibernate
- Generate Code Automatically : Lombok
- AA : Spring Security + JWT
- Database : MySQL (Heroku)
- Cache Mechanism : Cache Manager + Redis(Heroku)
- API Documentation : Swagger UI
- CI/CD : GitHub Action + Heroku
- Container For Service : Docker

#### Implementation Detail : 
1. Initial DB(MySQL + Redis) by Heroku.
2. Use Spring Boot to build RESTful API.
3. Using Spring JPA to manage Database layer.
4. Implement JWT authentication and authorize API by the user's role.
5. Establish Cache mechanism by Redis to enhance performance and reduce MySQL DB loading.
6. Invoke Swagger UI to implement a interactive API documentation.
7. Build image by Docker and start service on container level.
8. Establish CI/CD flow by GitHub Action and Heroku, automatic build code, build docker image and release to Heroku registry for deploy service on the Heroku cloud.