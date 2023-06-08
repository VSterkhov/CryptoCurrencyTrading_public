FROM openjdk:8-jre-alpine
COPY ./build/libs/aggregator_kafka_consumer-all.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "aggregator_kafka_consumer-all.jar"]