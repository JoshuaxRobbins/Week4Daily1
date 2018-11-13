package com.example.josh.week4daily1.utils;

import android.util.Log;

import com.example.josh.week4daily1.models.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkhttpHelper {
    OkHttpClient client;
    List<Person> personList;
    public OkhttpHelper() {
        client = new OkHttpClient();
    }

    public List<Person> execute(String restrictions){
        final Request request = new Request.Builder()
                .url(NetworkHelper.RANDOM_USER + "&" + restrictions)
                .build();

        try {
            String response = client.newCall(request).execute().body().string();
            personList = PersonParser.parseAll(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
