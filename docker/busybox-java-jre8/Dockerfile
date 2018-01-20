#FROM progrium/busybox
FROM cato1971/buildpack-deps-32bit:jessie-curl
# docker build -t busybox-jre8 .

# Set environment
ENV JAVA_HOME /opt/java
ENV PATH ${PATH}:${JAVA_HOME}/bin

ADD jre-8u161-linux-i586.tar.gz /opt/

RUN ln -s /opt/jre1.8.0_161 /opt/java

RUN  ls -l /opt/java/bin/
# Define default command.
CMD ["/opt/java/bin/java","-version"]