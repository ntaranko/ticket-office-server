package com.taranko.ticketofice.server.trains;

import java.util.List;

public class Train {
    private String trainNumber;
    private List<TrainStation> trainStations;

    public Train(String trainNumber, List<TrainStation> trainStations) {
        this.trainNumber = trainNumber;
        this.trainStations = trainStations;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public List<TrainStation> getTrainStations() {
        return trainStations;
    }

    public void setTrainStations(List<TrainStation> trainStations) {
        this.trainStations = trainStations;
    }

    @Override
    public boolean equals(Object obj) {

        Train train = (Train) obj;
        return (train.getTrainNumber().equals(((Train) obj).getTrainNumber()));
    }


}
