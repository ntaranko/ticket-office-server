package com.taranko.ticketofice.server.requests;

import com.taranko.ticketofice.server.jsonreaderswriters.ReadJsonAvailableTickets;
import com.taranko.ticketofice.server.jsonreaderswriters.ReadJsonTrains;
import com.taranko.ticketofice.server.trains.*;
import com.taranko.ticketofice.server.utils.DateUtils;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RequestedTrains {

    public List<Train> search(JSONObject clientData) throws FileNotFoundException, ParseException {

        DateUtils dateUtils = new DateUtils();

        String requestDispatchStation = clientData.getString("requestDispatchStation");
        String requestArrivalStation = clientData.getString("requestArrivalStation");
        Date requestDispatchDate = dateUtils.buildDateFromString(clientData.getString("requestDispatchDate"));
        Date requestDispatchTime = dateUtils.buildTimeFromString(clientData.getString("requestDispatchTime"));
        int requestNumberTickets = clientData.getInt("requestNumberTickets");

        //search trains
        ReadJsonTrains readJsonTrains = new ReadJsonTrains();
        List<Train> trains = readJsonTrains.readTrains();
        List<Train> searchResultsTrains = new ArrayList<>();
        for (int i = 0; i < trains.size(); i++) {
            List<TrainStation> trainStations = trains.get(i).getTrainStations();
            int tmp = -1;
            for (int j = 0; j < trainStations.size(); j++) {
                if (requestDispatchStation.equals(trainStations.get(j).getStation())
                        && requestDispatchTime.before(trainStations.get(j).getStationDispatchTime())) {
                    tmp = j;
                    break;
                }
            }

            if (tmp >= 0) {
                for (int j = tmp; j < trainStations.size(); j++) {
                    if (requestArrivalStation.equals(trainStations.get(j).getStation())) {
                        searchResultsTrains.add(trains.get(i));
                        break;
                    }
                }
            }
        }

        //search available tickets
        ReadJsonAvailableTickets readJsonAvailableTickets = new ReadJsonAvailableTickets();
        List<AvailableTickets> availableTickets = readJsonAvailableTickets.readAvailableTickets();
        List<Train> searchResultsTrainsWithTickets = new ArrayList<>();

        for (int i = 0; i < searchResultsTrains.size(); i++) {
            for (int j = 0; j < availableTickets.size(); j++) {
                if (searchResultsTrains.get(i).getTrainNumber().equals(availableTickets.get(j).getTrainNumber())) {
                    List<TicketsForDate> ticketsForDates = availableTickets.get(j).getAllTicketsForDate();
                    for (int k = 0; k < ticketsForDates.size(); k++) {
                        if (ticketsForDates.get(k).getNumberOfTickets() >= requestNumberTickets
                                && requestDispatchDate.compareTo(ticketsForDates.get(k).getDate()) == 0) {
                            searchResultsTrainsWithTickets.add(searchResultsTrains.get(i));
                        }
                    }
                }
            }
        }

        if (searchResultsTrainsWithTickets.size() > 0) {
            for (int i = 0; i < searchResultsTrainsWithTickets.size(); i++) {
                System.out.println(searchResultsTrainsWithTickets.get(i).getTrainNumber());
            }
        } else {
            System.out.println("No tickets");
        }

        if (searchResultsTrainsWithTickets.size() > 0) {
            return searchResultsTrainsWithTickets;
        } else {
            return null;
        }


    }
}
