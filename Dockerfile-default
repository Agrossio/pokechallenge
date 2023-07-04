FROM openjdk:17-alpine
WORKDIR /app
COPY target/pokechallenge-0.0.1-SNAPSHOT.war pokechallenge.war
EXPOSE 8080
CMD ["java", "-jar", "pokechallenge.war"]