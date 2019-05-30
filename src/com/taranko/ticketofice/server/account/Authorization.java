package com.taranko.ticketofice.server.account;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Authorization {

    public Account authorize(JSONObject clientData) throws IOException {
        List<Account> accounts;
        ReadJsonAccounts readJsonAccounts = new ReadJsonAccounts();
        readJsonAccounts.readAccounts();
        accounts = readJsonAccounts.getAccounts();

        String login = clientData.getString("login");
        String password = clientData.getString("password");
        Account userAccount = new Account();
        boolean authorized = false;

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getLogin().equals(login) && accounts.get(i).getPassword().equals(password)) {
                userAccount = accounts.get(i);
                authorized = true;
            }
        }

        if (authorized){
            return userAccount;
        } else {
            return  null;
        }


    }
}
