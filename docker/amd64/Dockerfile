FROM openjdk:8u151-jre-alpine3.7
#FROM busybox-java:jre8
# docker build -t traiana/demo-adp -f docker/Dockerfile .

ENV APP_NAME=grpc-server
ARG JAVA_OPTIONS=""

ADD  target/${APP_NAME}.tar /opt/
COPY docker/amd64/tini-static /opt/tini

RUN mv /opt/lib/${APP_NAME}.jar /opt/lib/app.jar \
    && chmod +x /opt/tini && chown -R 99:99 /opt && ls -al /opt/

WORKDIR /opt
EXPOSE 8080
USER nobody
ENTRYPOINT ["/opt/tini", "--"]
CMD ["java", "-jar", "/opt/lib/app.jar"]