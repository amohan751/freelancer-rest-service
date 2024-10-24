FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /freelancer
COPY freelancer/pom.xml ./
COPY freelancer/src ./src
RUN mvn clean package -DskipTests

# Use the official openjdk image to run the application
FROM openjdk:17-jdk
WORKDIR /freelancer
COPY --from=build /freelancer/target/freelancer-0.0.1-SNAPSHOT.jar freelancerApp.jar
ENTRYPOINT ["java", "-jar", "freelancerApp.jar"]