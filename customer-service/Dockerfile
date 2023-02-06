FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.18.0/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

ENTRYPOINT ["java","-javaagent:/opentelemetry-javaagent.jar", "-Dotel.service.name=tracing-grafana-customer", "-Dotel.traces.exporter=otlp", "-Dotel.metrics.exporter=none", "-Dotel.logs.exporter=none", "-Dotel.exporter.otlp.endpoint=http://collector:4317", "-Dotel.traces.sampler=always_on", "-jar","/application.jar"]