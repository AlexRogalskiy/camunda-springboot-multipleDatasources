# How to connect to Postgres

### Use the following snippet for your application.yml in a Springboot application
```
spring.datasource:
   type: org.postgresql.ds.PGSimpleDataSource
   username: postgres
   password: postgres
   url: jdbc:postgresql://localhost:5432/postgres
```
### And add this dependency to your pom.xml
```
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
		</dependency>
```
