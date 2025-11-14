# Stage 1: Build
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Créer utilisateur non-root
RUN addgroup -S spring && adduser -S spring -G spring

# Copier seulement le JAR depuis le stage de build
COPY --from=builder /app/target/*.jar app.jar

RUN chown spring:spring app.jar
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+UseContainerSupport", \
    "-jar", \
    "app.jar"]

