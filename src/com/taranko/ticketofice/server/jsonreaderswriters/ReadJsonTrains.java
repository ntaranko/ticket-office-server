package com.taranko.ticketofice.server.jsonreaderswriters;

import com.taranko.ticketofice.server.trains.Train;
import com.taranko.ticketofice.server.trains.TrainStation;
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

public class ReadJsonTrains {

    public List<Train> readTrains() throws FileNotFoundException, ParseException {
        List<Train> trains = new ArrayList<>();
        FileReader reader = new FileReader("trains.json");
        JSONTokener tokener = new JSONTokener(reader);
        JSONArray trainsJsonArray = (JSONArray) tokener.nextValue();

        for (int i = 0; i < trainsJsonArray.length(); i++) {
            JSONObject oneJsonTrain = (JSONObject) trainsJsonArray.get(i);
            String trainNumber = oneJsonTrain.getString("trainNumber");

            List<TrainStation> trainStations = new ArrayList<>();
            JSONArray stationsJsonArray = oneJsonTrain.getJSONArray("trainRoute");

            for (int j = 0; j < stationsJsonArray.length(); j++) {
                JSONObject oneJsonStation = (JSONObject) stationsJsonArray.get(j);
                String station = oneJsonStation.getString("station");
                int distance = oneJsonStation.getInt("distance");

                DateUtils dateUtils = new DateUtils();
                String stationDispatchTimeString = oneJsonStation.getString("stationDispatchTime");
                Date stationDispatchTime = dateUtils.buildTimeFromString(stationDispatchTimeString);
                trainStations.add(new TrainStation(station, stationDispatchTime, distance));
            }

            trains.add(new Train(trainNumber, trainStations));
        }

        return trains;
    }
}


