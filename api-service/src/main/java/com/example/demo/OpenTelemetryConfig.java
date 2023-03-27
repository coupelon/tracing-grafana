package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;

/**
 * Required for the moment : https://stackoverflow.com/questions/75293292/spring-micrometer-opentelemetry-exporter-otlp
 */
@Configuration
public class OpenTelemetryConfig {

    @Value("${otel.exporter.otlp.traces.endpoint:http://collector:4317}")
    private String tracesEndpoint;

    @Bean
    public SpanExporter spanExporter() {
        return OtlpGrpcSpanExporter.builder().setEndpoint(tracesEndpoint).build();
    }

}