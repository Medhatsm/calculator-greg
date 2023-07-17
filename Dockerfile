FROM openjdk:8-alpine
RUN apk update && apk add maven
RUN mvn -version
WORKDIR /app 
COPY . .
