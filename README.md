# Vanhan kansan Twitter
This is a repository containing an Spring Boot application called Vanhan kansan Twitter.  
This project is created as an exercise for the course  _Web palvelinohjelmointi, fall 2019_, held in the University of Helsinki.

## Heroku-url
[Wepa-Twitter deployed on heroku](https://wepa-twitter.herokuapp.com/)

## Known bugs
  * Serving static content does not work at all.
  * Atleast locally under development SecurityConfiguration, with relaxed access permissions, the application will print long ugly error messages in various views when the user is not logged in. No checks implemented in the thymeleaf templates for user authentication, for example when listing all users under /users.  
  * The class FileObject.java located in the package projekti.model has the field private byte[] content. The annotation for this field needs to be set manually.  
   * If running in heroku use @Type(type = "org.hibernate.type.BinaryType") and when running locally with H2 use @Lob @Basic(fetch = FetchType.LAZY)  

## Maven commands
```
mvn spring-boot:run
```
