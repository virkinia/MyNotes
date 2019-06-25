package com.example.mynotes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplicationPreferences {
    static final String KEYNAME = "MYNOTES";


    private  static SharedPreferences mSharedPref;

    private ApplicationPreferences() {}

    public static void init(Context context) {
        if(mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(KEYNAME, Activity.MODE_PRIVATE);
        }
    }

    public static void saveString(String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();

        prefsEditor.putString(KEYNAME, value);
        prefsEditor.apply();
    }

    public static String readString(String defValue) {
        return mSharedPref.getString(KEYNAME, defValue);
    }

    public static void saveNotes(ArrayList<NotesModel> notesList) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notesList);

        prefsEditor.putString(KEYNAME, json);
        prefsEditor.apply();
        //prefsEditor.commit();
    }

    public static ArrayList<NotesModel> readNotes() {

        Gson gson = new Gson();
        String json = mSharedPref.getString(KEYNAME, null);
        Type type = new TypeToken<ArrayList<NotesModel>>(){}.getType();
        return gson.fromJson(json, type);

    }


}
