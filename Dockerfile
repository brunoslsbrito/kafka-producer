FROM adoptopenjdk/openjdk11:latest
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} kafka-producer.jar
ENTRYPOINT ["java","-jar","/kafka-producer-1.0.0.jar"]