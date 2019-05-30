package com.taranko.ticketofice.server.trains;

import java.util.Date;

public class TrainStation {
    private String station;
    private Date stationDispatchTime;
    private int distance;

    public TrainStation(String station, Date stationDispatchTime, int distance) {
        this.station = station;
        this.stationDispatchTime = stationDispatchTime;
        this.distance = distance;
    }

    public String getStation() {
        return station;
    }

    public Date getStationDispatchTime() {
        return stationDispatchTime;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
