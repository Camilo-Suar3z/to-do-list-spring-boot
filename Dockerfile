# Etapa 1: Construcción de la aplicación
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copiar archivo de dependencias y descargarlas (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final más liviana
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la app con perfil de producción
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]