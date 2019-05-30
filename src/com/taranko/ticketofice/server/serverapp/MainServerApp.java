package com.taranko.ticketofice.server.serverapp;

import java.io.*;
import java.text.ParseException;

public class MainServerApp {


    public static void main(String[] args) throws IOException, ParseException {
        Server server = new Server();

        server.run();

    }
}

