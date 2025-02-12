FROM gradle:8.12-jdk21 as builder

WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew settings.gradle.kts build.gradle.kts /app/
COPY gradle/wrapper/ gradle/wrapper/

# Copy source code
COPY src /app/src/

# Ensure the Gradle wrapper is executable
RUN chmod +x gradlew

# Build the application (using bootJar) and move the JAR file
RUN ./gradlew clean bootJar -x test --no-daemon && \
    mv $(find build/libs -type f -name "*.jar") build/app.jar

# Use a slim production image with Java 21 from Eclipse Temurin
FROM eclipse-temurin:21-jre-jammy AS final

WORKDIR /app

COPY --from=builder /app/build/app.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]