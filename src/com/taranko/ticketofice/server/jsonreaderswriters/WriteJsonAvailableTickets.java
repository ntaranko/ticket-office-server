package com.taranko.ticketofice.server.jsonreaderswriters;

import com.taranko.ticketofice.server.trains.AvailableTickets;
import com.taranko.ticketofice.server.trains.TicketsForDate;
import com.taranko.ticketofice.server.utils.DateUtils;
import org.json.JSONWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteJsonAvailableTickets {


    public void writeAvailableTickets(List<AvailableTickets> availableTickets) throws IOException {

        DateUtils du = new DateUtils();
        FileWriter writer = new FileWriter("trains-write-avTickets.json");
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.array();

        for (int i = 0; i < availableTickets.size(); i++) {
            String trainNumber = availableTickets.get(i).getTrainNumber();
            jsonWriter.object().key("trainNumber").value(trainNumber);

            jsonWriter.key("availableTickets").array();
            List<TicketsForDate> ticketsForDates = availableTickets.get(i).getAllTicketsForDate();

            for (int j = 0; j < ticketsForDates.size(); j++) {
                String dateStr = du.buildStringFromDate(ticketsForDates.get(j).getDate());
                jsonWriter.object();
                jsonWriter.key("date").value(dateStr);
                jsonWriter.key("numberOfTickets").value(ticketsForDates.get(j).getNumberOfTickets());
                jsonWriter.endObject();
            }
            jsonWriter.endArray().endObject();

        }
        jsonWriter.endArray();

        writer.close();
    }
}
