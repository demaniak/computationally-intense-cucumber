FROM azul/zulu-openjdk-alpine:8
MAINTAINER HS Coetzee <hs.coetzee+cic@gmail.com>
ARG JAR_FILE
COPY ./target/${JAR_FILE} /app/app.jar
EXPOSE 8080
RUN apk --update add curl
RUN  adduser -D hsc
HEALTHCHECK --start-period=120s CMD curl --fail http://localhost:8080/health || exit 1
USER hsc
ENTRYPOINT ["java","-server","-Djava.security.egd=file:/dev/./urandom","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app/app.jar"]
