package com.example.mynotes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplicationPreferences {
    static final String KEYNOTES = "MYNOTES";
    static final String KEYONBOARDING = "ONBOARDING";


    private  static SharedPreferences mSharedPref;

    private ApplicationPreferences() {}

    public static void init(Context context) {
        if(mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(KEYNOTES, Activity.MODE_PRIVATE);
        }
    }

    public static void saveOnBoarding(boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(KEYONBOARDING, value);
        prefsEditor.apply();
    }

    public static Boolean readOnBoarding() {
        return mSharedPref.getBoolean(KEYONBOARDING, false);
    }

    public static void saveNotes(ArrayList<NotesModel> notesList) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notesList);

        prefsEditor.putString(KEYNOTES, json);
        prefsEditor.apply();
        //prefsEditor.commit();
    }

    public static ArrayList<NotesModel> readNotes() {

        Gson gson = new Gson();
        String json = mSharedPref.getString(KEYNOTES, null);
        Type type = new TypeToken<ArrayList<NotesModel>>(){}.getType();
        return gson.fromJson(json, type);

    }


}
