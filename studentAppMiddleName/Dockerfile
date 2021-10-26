FROM gradle:7.2-jdk11 AS build
ARG project_path=.
RUN mkdir /apps
COPY --chown=gradle:gradle $project_path /apps
WORKDIR /apps

RUN gradle clean build 

FROM openjdk:11-jre
ARG project_path=.
COPY --from=build /apps/build/libs/studentAppMiddleName-0.0.1-SNAPSHOT.jar . 
EXPOSE 8082
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "studentAppMiddleName-0.0.1-SNAPSHOT.jar"]
