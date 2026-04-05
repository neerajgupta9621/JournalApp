# ===== Build Stage =====
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy project files
COPY . /app

# Build the jar (Maven)
RUN ./mvnw clean package -DskipTests

# ===== Runtime Stage =====
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render default)
EXPOSE 10000

# Environment variable
ENV MONGO_URI=mongodb+srv://username:password@cluster...

# Command to run the app
CMD ["java", "-jar", "app.jar"]