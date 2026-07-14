# --- Etapa 1: build ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos primero solo el pom.xml para aprovechar el cache de capas de Docker:
# si no cambian las dependencias, no se vuelven a descargar en cada build.
COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests package

# --- Etapa 2: runtime ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Usuario no root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/target/requerimientos-proformas-service.jar app.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
