package com.taranko.ticketofice.server.jsonprocessors;

import com.taranko.ticketofice.server.account.Authorization;
import org.json.JSONObject;

public class LoginProcessor implements Processor {

    @Override
    public JSONObject processRequest(JSONObject clientData) {
        Authorization authorization = new Authorization();

        JSONObject response = new JSONObject();

        try {
            if (authorization.authorize(clientData) != null) {
                response.put("firstName", authorization.authorize(clientData).getFirstName());
                response.put("lastName", authorization.authorize(clientData).getLastName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (response.isEmpty()) {
            return null;
        } else{
            return response;
        }

    }
}

