# Stage 1: Build the Angular front-end
FROM node:16 as node-builder

# Set working directory for the Angular app
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY src/main/UI/package*.json ./

# Install Node dependencies
RUN npm install

# Copy the Angular source code
COPY src/main/UI/ .

# Build the Angular application
RUN npm run build --prod

# Stage 2: Build the Spring Boot application
FROM maven:3.8.1-openjdk-17 as maven-builder

# Set working directory for the Spring Boot app
WORKDIR /backend

# Copy the Maven project files
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the Spring Boot app
COPY . .

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Stage 3: Combine Angular frontend and Spring Boot backend in one container
FROM openjdk:17-jdk-slim

# Set working directory in the final container
WORKDIR /app

# Copy the Spring Boot JAR from the maven-builder stage
COPY --from=maven-builder /backend/target/D387_sample_code-0.0.2-SNAPSHOT.jar /app/app.jar

# Copy the Angular build output from the node-builder stage
COPY --from=node-builder /app/dist/* /app/static/

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
