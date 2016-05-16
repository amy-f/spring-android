package com.example.manchotstudios.com.spring;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by christian on 2016-05-16.
 */
public class Preferences extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceStat){
        super.onCreate(savedInstanceStat);
        addPreferencesFromResource(R.xml.preferences);
    }
}
