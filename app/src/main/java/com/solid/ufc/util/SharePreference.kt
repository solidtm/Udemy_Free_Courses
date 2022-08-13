package com.solid.ufc.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class SharePreference @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        private const val USERNAME = "USERNAME"
        private const val PASSWORD = "PASSWORD"
    }

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)


    @SuppressLint("NewApi")
    private fun encodeString(value: String): String {
        return Base64.getEncoder().encodeToString(value.toByteArray())
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
            apply()
        }
    }

    var username: String
        get() = sharedPreferences.getString(SharedPrefKeys.USERNAME, "") ?: ""
        set(value) {
            sharedPreferences.edit().putString(SharedPrefKeys.USERNAME, value).apply()
        }

    var password: String
        get() = sharedPreferences.getString(SharedPrefKeys.PASSWORD, "") ?: ""
        set(value) {
            sharedPreferences.edit().putString(SharedPrefKeys.PASSWORD, value).apply()
        }


    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit {
            putBoolean(key, value)
            apply()
        }
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit {
            putInt(key, value)
            apply()
        }
    }

    fun getString(key: String, defaultString: String? = null) =
        sharedPreferences.getString(key, defaultString) ?: ""

    fun getBoolean(key: String, defaultBoolean: Boolean = false) =
        sharedPreferences.getBoolean(key, defaultBoolean)

    fun getInt(key: String, defaultInt: Int = 0) = sharedPreferences.getInt(key, defaultInt)

    fun clear(key: String) {
        sharedPreferences.edit().remove(key).commit()
    }
}