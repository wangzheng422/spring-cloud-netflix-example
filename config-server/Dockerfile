FROM java:8-jdk

ENV DOCKERIZE_VERSION v0.6.0
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app
WORKDIR /app
COPY build/libs/config-server.jar /app
ADD config /app/
#VOLUME /app/config
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://eureka-server:8761",  "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/config-server.jar", "--spring.profiles.active=native,docker"]
EXPOSE 8100