package com.taranko.ticketofice.server.jsonprocessors;

import com.taranko.ticketofice.server.requests.RequestedTrains;
import com.taranko.ticketofice.server.trains.Train;
import com.taranko.ticketofice.server.trains.TrainStation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class TrainRequestProcessor implements Processor {

    public static final double ONE_KM_COST = 3.85;

    @Override
    public JSONObject processRequest(JSONObject clientData) {

        String requestDispatchStation = clientData.getString("requestDispatchStation");
        String requestArrivalStation = clientData.getString("requestArrivalStation");
        int requestNumberTickets = clientData.getInt("requestNumberTickets");

        JSONObject response = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        RequestedTrains requestedTrains = new RequestedTrains();

        try {
            List<Train> trainsSearchResults = requestedTrains.search(clientData);
            if (trainsSearchResults != null) {

                String trainNumber;
                Date trainDispatchTime = new Date();
                Date trainArrivalTime = new Date();
                int stationDispatchDistance = 0;
                int stationArrivalDistance = 0;

                for (int i = 0; i < trainsSearchResults.size(); i++) {
                    trainNumber = trainsSearchResults.get(i).getTrainNumber();

                    String trainStartStation = trainsSearchResults.get(i).getTrainStations().get(0).getStation();
                    int lastStationIndex = trainsSearchResults.get(i).getTrainStations().size() - 1;
                    String trainLastStation = trainsSearchResults.get(i).getTrainStations().get(lastStationIndex).getStation();
                    StringBuilder stringBuilder = new StringBuilder();
                    String trainName = stringBuilder.append(trainStartStation).append(" - ").append(trainLastStation).toString();

                    List<TrainStation> stationsList = trainsSearchResults.get(i).getTrainStations();

                    for (int j = 0; j < stationsList.size(); j++) {
                        if (stationsList.get(j).getStation().equals(requestDispatchStation)) {
                            trainDispatchTime = stationsList.get(j).getStationDispatchTime();
                            stationDispatchDistance = stationsList.get(j).getDistance();
                        }
                        if (stationsList.get(j).getStation().equals(requestArrivalStation)) {
                            trainArrivalTime = stationsList.get(j).getStationDispatchTime();
                            stationArrivalDistance = stationsList.get(j).getDistance();
                            break;
                        }
                    }

                    int travelDistance = stationArrivalDistance - stationDispatchDistance;
                    double oneTicketCost = travelDistance * ONE_KM_COST;
                    double fullCost = oneTicketCost * requestNumberTickets;

                    JSONObject oneTrainInfo = new JSONObject();

                    oneTrainInfo.put("trainNumber", trainNumber);
                    oneTrainInfo.put("trainName", trainName);
                    oneTrainInfo.put("trainDispatchTime", trainDispatchTime);
                    oneTrainInfo.put("trainArrivalTime", trainArrivalTime);
                    oneTrainInfo.put("requestNumberTickets", requestNumberTickets);
                    oneTrainInfo.put("fullCost", fullCost);

                    jsonArray.put(oneTrainInfo);
                }

                response.put("response", jsonArray);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (response.isEmpty()) {
            return null;
        } else {
            return response;
        }
    }
}

