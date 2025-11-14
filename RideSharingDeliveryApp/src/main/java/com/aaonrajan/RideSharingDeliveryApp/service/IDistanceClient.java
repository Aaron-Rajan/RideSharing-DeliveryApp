package com.aaonrajan.RideSharingDeliveryApp.service;

import com.aaonrajan.RideSharingDeliveryApp.model.DistanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "distance-service",          // MUST match DistanceService's spring.application.name
        path = "/api/distances"
)
public interface IDistanceClient {
    @GetMapping("/getDistance")
    DistanceDto getDistance(
            @RequestParam("from") String from,
            @RequestParam("to") String to
    );
}
