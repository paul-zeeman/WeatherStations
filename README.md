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

## Running Unit Tests

Tests are not being detected from the ```mvn``` command line.

All Tests can be run from an IDE.

## Possible improvements
- When navigating back to the main station list from the station details, the filter is lost
- From an architecture and maintenance pov, the in-line js functions in templates/stations.html could be refactored
  into their own files
- More unit tests can be added the backend to follow possible error paths
- A front-end unit testing framework such as Jest could be introduced to verify the
 functions executed in the browser
