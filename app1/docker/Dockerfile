FROM anapsix/alpine-java:8_jdk_unlimited
VOLUME /tmp
COPY app1-0.0.1-SNAPSHOT.jar /app.jar
ENV JAVA_OPTS "-Xms56m -Xmx128m -Xss256k -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["java"]
CMD ["-jar", "/app.jar"]
EXPOSE 8080