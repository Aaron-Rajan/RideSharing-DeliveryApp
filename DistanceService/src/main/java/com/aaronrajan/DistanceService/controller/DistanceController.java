package com.aaronrajan.DistanceService.controller;

import com.aaronrajan.DistanceService.model.DistanceDto;
import com.aaronrajan.DistanceService.service.DistanceMatrixClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/distances")
public class DistanceController {
    private final DistanceMatrixClient service;

    public DistanceController(DistanceMatrixClient service) {
        super();
        this.service = service;
    }

    @GetMapping("/getDistance")
    public ResponseEntity<DistanceDto> getDistance(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(service.getDistance(from, to));
    }
}
