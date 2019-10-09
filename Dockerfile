FROM adoptopenjdk/openjdk8:latest

USER root

RUN apt-get update && apt-get install -y --no-install-recommends libfontconfig1 && rm -rf /var/lib/apt/lists/*

WORKDIR /home/oereb-search-service
COPY build/libs/*.jar /home/oereb-search-service/oereb-search-service.jar
RUN cd /home/oereb-search-service && \
    chown -R 1001:0 /home/oereb-search-service && \
    chmod -R g+rw /home/oereb-search-service && \
    ls -la /home/oereb-search-service

COPY indexpath /home/oereb-search-service/

USER 1001
EXPOSE 8080
CMD java -jar oereb-search-service.jar 
