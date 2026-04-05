FROM eclipse-temurin:17-jdk

WORKDIR /app

# Correct jar name use kar
COPY target/my-first-project-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000

ENV MONGO_URI=mongodb+srv://username:password@cluster...

CMD ["java", "-jar", "app.jar"]