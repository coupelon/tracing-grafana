FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.18.0/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

EXPOSE 8081
# This line would create a new trace for the receive part of the call, which is redundant.
# More info here : https://opentelemetry.io/docs/instrumentation/java/automatic/agent-config/#capturing-consumer-message-receive-telemetry-in-messaging-instrumentations
#  "-Dotel.instrumentation.messaging.experimental.receive-telemetry.enabled=true",
# Sampling documentation and parameters : https://opentelemetry-python.readthedocs.io/en/latest/sdk/trace.sampling.html
ENTRYPOINT ["java","-javaagent:/opentelemetry-javaagent.jar", "-Dotel.service.name=tracing-grafana-api", "-Dotel.traces.exporter=otlp", "-Dotel.metrics.exporter=none", "-Dotel.logs.exporter=none", "-Dotel.exporter.otlp.endpoint=http://collector:4317", "-Dotel.traces.sampler=always_on" ,"-jar","/application.jar"]