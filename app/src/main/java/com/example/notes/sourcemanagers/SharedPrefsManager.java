package com.example.notes.sourcemanagers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.notes.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class SharedPrefsManager {
    public static void writeNotesToSharedPrefs (Context context, String content){
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PREF_SOURCE_NOTES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> savedSet = sharedPref.getStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, null);
        Set<String> newSet = new HashSet<>();
        if(savedSet != null) {
            newSet.addAll(savedSet);
        }
        newSet.add(content);
        editor.putStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, newSet);

        editor.apply();
    }

    public static Set<String> readNotesFromSharedPrefs (Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PREF_SOURCE_NOTES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, null);
    }
}