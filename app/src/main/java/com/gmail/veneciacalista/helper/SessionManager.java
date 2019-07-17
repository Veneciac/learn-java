package com.gmail.veneciacalista.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {
    private SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void putString(Context context, String key, String value) {
        getPref(context).edit().putString(key, value).apply();
    }

    public String getString(Context context, String key) {
        return getPref(context).getString(key, "");
    }

    public void clearAll(Context context) {
        getPref(context).edit().clear().apply();
    }

    public void clear(Context context, String key) {
        getPref(context).edit().remove(key).apply();
    }
}
