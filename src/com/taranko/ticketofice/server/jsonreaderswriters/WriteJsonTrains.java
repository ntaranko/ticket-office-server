package com.taranko.ticketofice.server.jsonreaderswriters;

import com.taranko.ticketofice.server.trains.Train;
import com.taranko.ticketofice.server.trains.TrainStation;
import com.taranko.ticketofice.server.utils.DateUtils;
import org.json.JSONWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteJsonTrains {


    public void writeJsonTrains(List<Train> trains) throws IOException {

        DateUtils du = new DateUtils();
        FileWriter writer = new FileWriter("trains-write.json");
        JSONWriter jsonWriter = new JSONWriter(writer);

        jsonWriter.array();

        for (int i = 0; i < trains.size(); i++) {
            String trainNumber = trains.get(i).getTrainNumber();
            jsonWriter.object().key("trainNumber").value(trainNumber);

            jsonWriter.key("trainRoute").array();
            List<TrainStation> trainRoute = trains.get(i).getTrainStations();
            for (int j = 0; j < trainRoute.size(); j++) {

                String timeStr = du.buildStringFromTime(trainRoute.get(j).getStationDispatchTime());

                jsonWriter.object();
                jsonWriter.key("station").value(trainRoute.get(j).getStation());
                jsonWriter.key("distance").value(trainRoute.get(j).getDistance());
                jsonWriter.key("stationDispatchTime").value(timeStr);
                jsonWriter.endObject();
            }
            jsonWriter.endArray().endObject();

        }
        jsonWriter.endArray();

        writer.close();
    }
}
