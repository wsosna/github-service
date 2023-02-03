# Github service
Simple service which will retrieve rows from public github repository API with custom filter applied.

[Read more in github doc](https://docs.github.com/en/rest/search?apiVersion=2022-11-28#search-repositories)
## Requirements
- Java 17
- Maven
- Internet connection

## Run locally
To build application run command below, this command will also run tests. Keep in mind that execution of those can take some time,
as It calls real service.
```shell
mvn install
```

To run application
```shell
mvn spring-boot:run
```

## API
Example of call:
```http request
GET http://localhost:8080/api/github/repositories?limit=TOP50&date=2019-10-01&language=java
```