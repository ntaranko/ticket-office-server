package com.taranko.ticketofice.server.trains;

import java.util.List;

public class AvailableTickets {
    private String trainNumber;
    private List<TicketsForDate> allTicketsForDate;

    public AvailableTickets(String trainNumber, List<TicketsForDate> allTicketsForDate) {
        this.trainNumber = trainNumber;
        this.allTicketsForDate = allTicketsForDate;
    }

    public String getTrainNumber() {
        return trainNumber;
    }


    public List<TicketsForDate> getAllTicketsForDate() {
        return allTicketsForDate;
    }

}
