FROM openjdk:11
ADD target/smart-car-api.jar smart-car-api.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "smart-car-api.jar"]
