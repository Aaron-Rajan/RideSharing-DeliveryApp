package com.aaonrajan.RideSharingDeliveryApp.model;

public class DistanceDto {
    private String from;
    private String to;
    private long distanceMetres;
    private long durationSeconds;

    public DistanceDto() {
        super();
    }

    public DistanceDto(String from, String to, long distanceMetres, long durationSeconds) {
        super();
        this.from = from;
        this.to = to;
        this.distanceMetres = distanceMetres;
        this.durationSeconds = durationSeconds;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getDistanceMetres() {
        return distanceMetres;
    }

    public void setDistanceMetres(long distanceMetres) {
        this.distanceMetres = distanceMetres;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(long durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}

