package com.aaronrajan.DistanceService.service;

import com.aaronrajan.DistanceService.exceptions.NoRouteFoundException;
import com.aaronrajan.DistanceService.model.DistanceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class DistanceMatrixClient {
    private final WebClient webClient;
    private final String apiKey;

    public DistanceMatrixClient(WebClient webClient, @Value("${distance.key}") String apiKey) {
        super();
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public DistanceDto getDistance(String from, String to) {
        Map<String, Object> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/maps/api/distancematrix/json")
                        .queryParam("origins", from)
                        .queryParam("destinations", to)
                        .queryParam("key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        Map<String, Object> firstElement = extractFirstElement(response, from, to);

        Map<String, Object> distance = (Map<String, Object>) firstElement.get("distance");
        Map<String, Object> duration = (Map<String, Object>) firstElement.get("duration");

        long distanceMeters = ((Number) distance.get("value")).longValue();
        long durationSeconds = ((Number) duration.get("value")).longValue();

        return new DistanceDto(from, to, distanceMeters, durationSeconds);
    }

    private Map<String, Object> extractFirstElement(Map<String, Object> response, String from, String to) {
        List<?> rows = (List<?>) response.get("rows");
        if (rows == null || rows.isEmpty()) {
            throw new IllegalStateException("No rows returned from DistanceMatrix API");
        }

        Map<String, Object> firstRow = (Map<String, Object>) rows.get(0);
        List<?> elements = (List<?>) firstRow.get("elements");
        if (elements == null || elements.isEmpty()) {
            throw new IllegalStateException("No elements returned from DistanceMatrix API");
        }

        Map<String, Object> firstElement = (Map<String, Object>) elements.get(0);
        String status = (String) firstElement.get("status");
        if ("ZERO_RESULTS".equals(status)) {
            throw new NoRouteFoundException(from, to);
        }

        if (!"OK".equals(status)) {
            throw new IllegalStateException("DistanceMatrix element status not OK: " + status);
        }

        return firstElement;
    }
}
