package com.taranko.ticketofice.server.account;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadJsonAccounts {

    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void readAccounts() throws FileNotFoundException {
        FileReader reader = new FileReader("accounts.json");
        JSONTokener tokener = new JSONTokener(reader);

        JSONArray accountsArray = (JSONArray) tokener.nextValue();
        int arrayLength = accountsArray.length();

        for (int i = 0; i < arrayLength; i++) {
            JSONObject oneAccount = (JSONObject) accountsArray.get(i);
            String login = oneAccount.getString("login");
            String password = oneAccount.getString("password");
            String firstName = oneAccount.getString("firstName");
            String lastName = oneAccount.getString("lastName");

            Account account = new Account();

            account.setLogin(login);
            account.setPassword(password);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            accounts.add(account);
        }
    }
}
