# everyday-recipe-springboot
Generate recipes for cooking meals without repetition, built with SpringBoot in Java.

## Database

```shell
docker run --name recipe-db -p 5432:5432 -e POSTGRES_DB=recipe -e POSTGRES_USER=sa -e POSTGRES_PASSWORD=abc123 -d postgres
```

https://www.baeldung.com/java-password-hashing

https://reflectoring.io/spring-security-password-handling/

https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

https://www.baeldung.com/registration-with-spring-mvc-and-spring-security

https://www.bezkoder.com/spring-boot-jwt-authentication/