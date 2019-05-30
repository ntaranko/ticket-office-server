package com.taranko.ticketofice.server.serverapp;

import com.taranko.ticketofice.server.jsonprocessors.LoginProcessor;
import com.taranko.ticketofice.server.jsonprocessors.Processor;
import com.taranko.ticketofice.server.jsonprocessors.TrainRequestProcessor;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static final int SERVER_PORT = 8849;

    private Map<String, Processor> allJsonProcessors;

    private void initProcessors() {
        allJsonProcessors = new HashMap<>();

        allJsonProcessors.put("login", new LoginProcessor());
        allJsonProcessors.put("trainRequest", new TrainRequestProcessor());
    }

    public Server() {
        initProcessors();
    }

    public void run() throws IOException, ParseException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        while (true) {

            Socket clientSocket = serverSocket.accept();

            InputStream inputStream = clientSocket.getInputStream();
            JSONTokener tokener = new JSONTokener(inputStream);

            JSONObject clientJson = (JSONObject) tokener.nextValue();
            JSONObject metaData = clientJson.getJSONObject("meta-data");

            String command = metaData.getString("command");
            Processor processor = allJsonProcessors.get(command);

            JSONObject clientData = clientJson.getJSONObject("data");

            OutputStream clientOS = clientSocket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientOS);
            JSONWriter clientResponse = new JSONWriter(outputStreamWriter);

            JSONObject response;

            JSONObject responseData = new JSONObject();


            response = processor.processRequest(clientData);

            if (response != null) {
                responseData.put("response-code", 200);
                responseData.put("response-message", "OK");
            } else {
                response = new JSONObject();
                responseData.put("response-code", 405);
                responseData.put("response-message", "Bad Request");
            }

            clientResponse.object();
            clientResponse.key("response-data").value(responseData);
            clientResponse.key("response").value(response);
            clientResponse.endObject();

            outputStreamWriter.flush();
        }
    }

}
