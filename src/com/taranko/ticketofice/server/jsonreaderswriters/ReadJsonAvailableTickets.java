package com.taranko.ticketofice.server.jsonreaderswriters;

import com.taranko.ticketofice.server.trains.AvailableTickets;
import com.taranko.ticketofice.server.trains.TicketsForDate;
import com.taranko.ticketofice.server.utils.DateUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadJsonAvailableTickets {

    public List<AvailableTickets> readAvailableTickets() throws FileNotFoundException, ParseException {
        List<AvailableTickets> availableTickets = new ArrayList<>();
        FileReader reader = new FileReader("availableTickets.json");
        JSONTokener tokener = new JSONTokener(reader);
        JSONArray availableTicketsJsonArray = (JSONArray) tokener.nextValue();

        for (int i = 0; i < availableTicketsJsonArray.length(); i++) {
            JSONObject ticketsOnOneTrainJson = (JSONObject) availableTicketsJsonArray.get(i);
            String trainNumber = ticketsOnOneTrainJson.getString("trainNumber");

            List<TicketsForDate> oneTrainTicketsForDates = new ArrayList<>();
            JSONArray oneTrainTicketsForDateJsonArray = ticketsOnOneTrainJson.getJSONArray("availableTickets");

            for (int j = 0; j < oneTrainTicketsForDateJsonArray.length(); j++) {
                JSONObject oneDateJsonTickets = (JSONObject) oneTrainTicketsForDateJsonArray.get(j);

                DateUtils dateUtils = new DateUtils();
                String dateString = oneDateJsonTickets.getString("date");
                Date date = dateUtils.buildDateFromString(dateString);
                int numberOfTickets = oneDateJsonTickets.getInt("numberOfTickets");
                oneTrainTicketsForDates.add(new TicketsForDate(date, numberOfTickets));
            }
            availableTickets.add(new AvailableTickets(trainNumber, oneTrainTicketsForDates));
        }
        return availableTickets;
    }
}


