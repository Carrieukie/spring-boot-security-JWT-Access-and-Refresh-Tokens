# Spring Boot Security JWT Access and Refresh Tokens

## Introduction

- This project encompasses two security concepts, Authentication and Authorization.
    - Authentication is verifying who the user is, do we know them? Do they exist in the database? Are their credentials correct? If they are known to you then allow them into the application and give them a token which they will use to access resources (API endpoints) in the application.
    - Authorization is giving and not giving access to users to certain resources on the application based on the specific roles assigned to them. A normal user for example, will not have the same access levels as a super admin and so on.
## Tech stack

- Dependencies
    - Spring Data JPA  - Persists data in SQL stores with Java persistence API using spring data and Hibernate.
    - MySQL Driver  - MySQL JDBC and R2DBC driver.
    - Spring Web  - Build web, including RESTFUL applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
    - Spring Security  - Highly customizable authentication and access-control framework for spring applications.
    - Spring Dev tools - Provides fast application restarts, Live reload and configurations for enhanced development experience.
    - Lombok - Java annotation library which helps reduce boilerplate code.
    
## ScreenShots
   - Login to get the access and refresh token.

      <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/login.png"/>
      
   - When you login using bad credentials

      <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/badcredentials.png" />
   
   - Unauthorized access to an endpoint

      <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/notAdmin.png" />
   
   - Refresh token

      <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/refreshtoken.png" />
 
   - Getting all the users in the database.

      <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/allusers.png" />
   
   - Adding users to the database, You need to have the admin role
   
         <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/addMember.png"/>

   - User already exists in the database.
    
      <img src="https://github.com/Carrieukie/spring-boot-security-JWT-Access-and-Refresh-Tokens/blob/main/assets/useralredyExists.png"/>
  