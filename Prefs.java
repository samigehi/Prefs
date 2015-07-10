/**
 * @Author:  Sumeet Gehi
 *********************
 * Copyright (c) 2013
 * All Rights Reserved.
 *
 **********************/
package com.sgsoft.backup.restore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Prefs {

	private SharedPreferences preferences;
	public static final String PREFS_NAME = "mySharedPrefs";

	/**
	 * initialize default SharedPreferences
	 * */
	public Prefs(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * @name SharedPreferences name
	 * */
	public Prefs(Context context, String name) {
		preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	public SharedPreferences getPreferences() {
		if (preferences == null)
			throw new RuntimeException("Opps you have forget to intialize Prefs class.. Prefs prefs = new Prefs(context)");
		return preferences;
	}

	public SharedPreferences.Editor getEditor() {
		return getPreferences().edit();
	}

	public int get(String key, int defaultValue) {
		return getPreferences().getInt(key, defaultValue);
	}

	public long get(String key, long defaultValue) {
		return getPreferences().getLong(key, defaultValue);
	}

	public float get(String key, float defaultValue) {
		return getPreferences().getFloat(key, defaultValue);
	}

	public double get(String key, double defaultValue) {
		String number = get(key);

		try {
			return Double.parseDouble(number);

		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public String get(String key) {
		return getPreferences().getString(key, "");
	}

	public ArrayList<String> getList(String key) {
		return new ArrayList<String>(Arrays.asList(TextUtils.split(
				getPreferences().getString(key, ""), "‚‗‚")));
	}

	public boolean get(String key, boolean defaultValue) {
		return getPreferences().getBoolean(key, defaultValue);
	}

	public void set(String key, int value) {
		getEditor().putInt(key, value).apply();
	}

	public void set(String key, long value) {
		getEditor().putLong(key, value).apply();
	}

	public void set(String key, float value) {
		getEditor().putFloat(key, value).apply();
	}

	public void set(String key, double value) {
		set(key, String.valueOf(value));
	}

	public void set(String key, String value) {
		getEditor().putString(key, value).apply();
	}

	public void setList(String key, ArrayList<String> stringList) {
		String[] myStringList = stringList
				.toArray(new String[stringList.size()]);
		getEditor().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
	}

	public void set(String key, boolean value) {
		getEditor().putBoolean(key, value).apply();
	}

	public boolean contains(String key) {
		return getPreferences().contains(key);
	}

	public void remove(String key) {
		getEditor().remove(key).apply();
	}

	public void clear() {
		getEditor().clear().apply();
	}

	public Map<String, ?> getAll() {
		return getPreferences().getAll();
	}

	public void registerOnSharedPreferenceChangeListener(
			SharedPreferences.OnSharedPreferenceChangeListener listener) {

		getPreferences().registerOnSharedPreferenceChangeListener(listener);
	}

	public void unregisterOnSharedPreferenceChangeListener(
			SharedPreferences.OnSharedPreferenceChangeListener listener) {

		getPreferences().unregisterOnSharedPreferenceChangeListener(listener);
	}

}
