package com.taranko.ticketofice.server.jsonreaderswriters;

import com.taranko.ticketofice.server.jsonreaderswriters.ReadJsonAvailableTickets;
import com.taranko.ticketofice.server.jsonreaderswriters.ReadJsonTrains;
import com.taranko.ticketofice.server.jsonreaderswriters.WriteJsonAvailableTickets;
import com.taranko.ticketofice.server.jsonreaderswriters.WriteJsonTrains;
import com.taranko.ticketofice.server.trains.AvailableTickets;
import com.taranko.ticketofice.server.trains.Train;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TESTJsonTrainWriter {

    public static void main(String[] args) throws IOException, ParseException {

        ReadJsonTrains readJsonTrains = new ReadJsonTrains();

        List<Train> trains = readJsonTrains.readTrains();
        WriteJsonTrains writeJsonTrains = new WriteJsonTrains();

        writeJsonTrains.writeJsonTrains(trains);

        ReadJsonAvailableTickets readJsonAvailableTickets = new ReadJsonAvailableTickets();
        List<AvailableTickets> availableTickets = readJsonAvailableTickets.readAvailableTickets();
        WriteJsonAvailableTickets writeJsonAvailableTickets = new WriteJsonAvailableTickets();
        writeJsonAvailableTickets.writeAvailableTickets(availableTickets);


    }
}
