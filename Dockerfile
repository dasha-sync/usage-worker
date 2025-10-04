FROM eclipse-temurin:17-jdk

WORKDIR /api

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package -DskipTests

CMD ["java", "-jar", "target/usage-worker-1.0-SNAPSHOT.jar"]
