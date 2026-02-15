# Etapa 1: Construcción con Maven y Eclipse Temurin
FROM maven:3.8.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivo de dependencias y descargarlas (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests
# Etapa 2: Imagen final
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Pasar variables de entorno explícitamente
ENTRYPOINT ["java", \
    "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", \
    "-DDATABASE_URL=${DATABASE_URL}", \
    "-DDATABASE_USER=${DATABASE_USER}", \
    "-DDATABASE_PASSWORD=${DATABASE_PASSWORD}", \
    "-jar", "app.jar"]