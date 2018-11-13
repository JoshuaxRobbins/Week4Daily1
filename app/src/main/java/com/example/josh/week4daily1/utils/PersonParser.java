package com.example.josh.week4daily1.utils;

import android.util.Log;

import com.example.josh.week4daily1.models.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonParser {

    private static Person person;

    public static List<Person> parseAll(String jsonString){
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            personList.add(parsePerson(jsonString,i));
        }
        return personList;
    }
    public static Person parsePerson(String jsonString,int place){
        person = new Person();
        JSONObject response = null;

        try {
            response = new JSONObject(jsonString);
            JSONArray results = response.getJSONArray("results");
            JSONObject user = results.getJSONObject(place);

            //Get user name
            JSONObject name = user.getJSONObject("name");
            person.setName(name.getString("title") + " "
            + name.getString("first") + " " + name.getString("last"));

            //Get user location
            JSONObject location = user.getJSONObject("location");
            person.setLocation(location.getString("city") + ", " + location.getString("state"));

            //Get user email
            person.setEmail(user.getString("email"));

            //Get user Age
            JSONObject age = user.getJSONObject("dob");
            person.setAge(age.getString("age"));

            //Get user Phone number
            person.setPhoneNumber(user.getString("cell"));

            person.setGender(user.getString("gender"));

        } catch (JSONException e) { e.printStackTrace();}

        return person;
    }


}
