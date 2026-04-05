# Use stable Eclipse Temurin JDK
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . /app

# Expose port (Render default)
EXPOSE 10000

# Environment variable (Mongo URI)
ENV MONGO_URI=mongodb+srv://username:password@cluster...

# Command to run your app
CMD ["java", "-jar", "target/journal-app.jar"]