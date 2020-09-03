# WeatherStations

A basic app implemented in [Spring Boot](http://projects.spring.io/spring-boot/), [Spring Batch](https://spring.io/projects/spring-batch) and [Thymeleaf](https://www.thymeleaf.org).

The app will use Spring Batch to read rows of information from a csv and populate an in-memory H2 database.
An API has been provided to list all the stations in the csv. ``/stations``.  From this page, the list can be filtered by date range, and details about each station can be retrieved to be shown on a new page.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.pzeeman.weatherstations.WeatherStationsApp` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

After the application has launched, it can be accessed by navigating to ``localhost:8080/stations``

