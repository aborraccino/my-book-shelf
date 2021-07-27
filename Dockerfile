FROM gradle:7.1.1-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk:11-jre-hotspot as extractor
WORKDIR application
ARG JAR_FILE=/home/gradle/src/build/libs/mybookshelf-1.0.0.jar
COPY --from=build ${JAR_FILE} mybookshelf-1.0.0.jar
RUN java -Djarmode=layertools -jar mybookshelf-1.0.0.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
COPY --from=extractor application/dependencies/ ./
COPY --from=extractor application/snapshot-dependencies/ ./
COPY --from=extractor application/spring-boot-loader ./
COPY --from=extractor application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
