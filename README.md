# Assignment
A simple SpirngBoot Application with some CRUD operation and with implementation of simple Rate Limiter.
In this application Bucket4j-core is used as a RateLimiter and spring-data-jpa to integrate spring application with JPA.
Bucket4j-core is a java rate limiting library based on token-bucket algorithm.  

To run this application we have to add some dependencies in pom.xml 
_____________________________________________________________________________________________________________________________________
Bucket4j-core

        <dependency>
			<groupId>com.github.vladimir-bukhtoyarov</groupId>
			<artifactId>bucket4j-core</artifactId>
			<version>4.10.0</version>
		</dependency>

_____________________________________________________________________________________________
spring-starter-web
 
 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
_____________________________________________________________________________________________________________        
spring-boot-starter-data-jpa        
		
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
_____________________________________________________________________________________________________________

