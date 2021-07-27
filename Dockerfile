FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR application
ARG JAR_FILE=build/libs/mybookshelf-1.0.0.jar
COPY ${JAR_FILE} mybookshelf-1.0.0.jar
RUN java -Djarmode=layertools -jar mybookshelf-1.0.0.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/spring-boot-loader ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
