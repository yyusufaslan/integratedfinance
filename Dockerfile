FROM openjdk:8-jdk-alpine
MAINTAINER yusufaslan
VOLUME /tmp
EXPOSE 8080
ADD target/integratedfinance-0.0.1-SNAPSHOT.jar integratedfinance.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/integratedfinance.jar"]
