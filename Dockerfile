# Use a Java 22-compatible base image
FROM eclipse-temurin:22-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build output (JAR file) into the container
COPY target/SDAT_Sprint1_Sprint2_Server-1.0-SNAPSHOT.jar app.jar

# Expose the application’s port (8080)
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]