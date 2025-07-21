FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# copy the Gradle wrapper and make it executable
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

# copy build scripts and download dependencies
COPY build.gradle settings.gradle ./
RUN ./gradlew dependencies --no-daemon

# build the app
COPY src src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]