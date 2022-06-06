FROM gradle:7.4.1-jdk17

COPY src/ /project/src
COPY gradle/ /project/gradle
COPY build.gradle /project/
COPY gradlew /project/
COPY settings.gradle /project/

EXPOSE 8080

WORKDIR /project

RUN /bin/bash -c './gradlew build'

CMD ["java", "-jar", "/project/build/libs/demo-0.0.1-SNAPSHOT.jar"]