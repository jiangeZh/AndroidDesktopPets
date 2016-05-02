package com.luoxl.androidDesktopPets;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;

public class Setting extends PreferenceActivity implements OnSharedPreferenceChangeListener {

    private EditTextPreference name;
    private ListPreference sex;
    private EditTextPreference year;
    private ListPreference character;
    private CheckBoxPreference start;
    private CheckBoxPreference notice;
    private CheckBoxPreference music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        initPreferences();
    }

    private void initPreferences() {
        name = (EditTextPreference)findPreference(Consts.NAME_KEY);
        sex = (ListPreference)findPreference((Consts.SEX_KEY));
        year = (EditTextPreference)findPreference((Consts.YEAR_KEY));
        character = (ListPreference)findPreference(Consts.CHARACTER_KEY);
        start = (CheckBoxPreference)findPreference((Consts.START_KEY));
        notice = (CheckBoxPreference)findPreference(Consts.NOTICE_KEY);
        music = (CheckBoxPreference)findPreference(Consts.MUSIC_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Setup the initial values
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
//        mListPreference.setSummary(sharedPreferences.getString(Consts.LIST_KEY, ""));
//        mEtPreference.setSummary(sharedPreferences.getString(Consts.EDIT_KEY, "linc"));

        // Set up a listener whenever a key changes
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Consts.NAME_KEY)) {
            name.setSummary(sharedPreferences.getString(key, "发哥"));
        } else if(key.equals(Consts.SEX_KEY)) {
            sex.setSummary(sharedPreferences.getString(key, ""));
        } else if(key.equals(Consts.YEAR_KEY)) {
            year.setSummary(sharedPreferences.getString(key, ""));
        } else if(key.equals(Consts.CHARACTER_KEY)) {
            character.setSummary(sharedPreferences.getString(key, ""));
        } else if(key.equals(Consts.START_KEY)) {
//            start.setSummary(sharedPreferences.getString(key, ""));
        } else if(key.equals(Consts.NOTICE_KEY)) {
//            notice.setSummary(sharedPreferences.getString(key, ""));
        } else if(key.equals(Consts.MUSIC_KEY)) {
//            music.setSummary(sharedPreferences.getString(key,""));
        }
    }
}
