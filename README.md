# MarTrust Assessment Exam - FX Rates Widget API

## Requirements
1. Java Version of at least `JDK 11`
2. IntelliJ IDE or anything similar
3. Maven

## How to run
### From IntelliJ
1. Import/Project to IntelliJ
2. Make sure that the project SDK is running at least a `JDK 11`
3. Build project
4. Run with Maven bootrun
   `mvn spring-boot:run` or `mvnw spring-boot:run`


### As jar application
1. Build and package application with
   `mvn clean package` or `mvnw clean package`
2. Run JAR application from target directory with
   `java -jar <path to target/fxratesapi-0.0.1-SNAPSHOT.jar>`

## API Specifications
Swagger specifications can be found [here](https://kylecdrck.github.io/MartrustAssessmentFxRate/)

## Unit Tests
1. Build and run application with
   `mvn clean test` or `mvnw clean test`
