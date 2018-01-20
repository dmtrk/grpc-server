FROM openjdk:8u151-jre-alpine3.7
#FROM busybox-java:jre8
# docker build -t traiana/demo-adp -f docker/Dockerfile .

ENV APP_NAME=grpc-server
ARG JAVA_OPTS=""

ADD  target/${APP_NAME}.tar /opt/
COPY docker/docker-entrypoint.sh /docker-entrypoint.sh
COPY docker/amd64/tini-static /opt/tini

RUN mv /opt/lib/${APP_NAME}.jar /opt/lib/app.jar \
    && chown -R 99:99 /opt \
    && chmod +x /docker-entrypoint.sh && chmod +x /opt/tini

WORKDIR /opt
EXPOSE 8080
USER 99
ENTRYPOINT ["/opt/tini", "--", "/docker-entrypoint.sh"]