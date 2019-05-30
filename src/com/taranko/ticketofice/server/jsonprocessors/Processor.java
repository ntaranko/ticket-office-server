package com.taranko.ticketofice.server.jsonprocessors;

import org.json.JSONObject;

import java.text.ParseException;

public interface Processor {

    JSONObject processRequest(JSONObject clientData) throws ParseException;


}
