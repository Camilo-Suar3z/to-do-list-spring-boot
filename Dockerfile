# Etapa 1: Construcción de la aplicación
FROM maven:3.8.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivo de dependencias y descargarlas (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final más liviana
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la app - VERSIÓN SIMPLIFICADA
ENTRYPOINT java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar app.jar