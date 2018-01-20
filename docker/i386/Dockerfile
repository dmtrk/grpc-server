#FROM cortinico/java8-32bit
FROM busybox-jre8
# docker build -t grpc-server -f docker/i386/Dockerfile .

ENV APP_NAME=grpc-server
ARG JAVA_OPTS=""

ADD  target/${APP_NAME}.tar /opt/
COPY docker/docker-entrypoint.sh /docker-entrypoint.sh
COPY docker/i386/tini-static-i386 /opt/tini

RUN mv /opt/lib/${APP_NAME}.jar /opt/lib/app.jar \
    && chown -R 99:99 /opt \
    && chmod +x /docker-entrypoint.sh && chmod +x /opt/tini

WORKDIR /opt
EXPOSE 8080
USER 99
ENTRYPOINT ["/opt/tini", "--", "/docker-entrypoint.sh"]