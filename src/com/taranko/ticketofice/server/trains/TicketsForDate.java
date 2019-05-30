package com.taranko.ticketofice.server.trains;

import java.util.Date;

public class TicketsForDate {
    private Date date;
    private int numberOfTickets;

    public TicketsForDate(Date date, int numberOfTickets) {
        this.date = date;
        this.numberOfTickets = numberOfTickets;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

}
