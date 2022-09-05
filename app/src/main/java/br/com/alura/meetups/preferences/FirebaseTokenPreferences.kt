package br.com.alura.meetups.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREFERENCES_NAME = "br.com.alura.meetups.preferences.FirebaseTokenPreferences"
private const val KEY_SENT = "enviado"

class FirebaseTokenPreferences(
    context: Context
){
    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    val enviado get() = preferences.getBoolean(KEY_SENT,false)

    fun tokenNovo() {
        preferences.edit {
            putBoolean(KEY_SENT, false)
        }
    }

    fun tokenEnviado() {
        preferences.edit {
            putBoolean(KEY_SENT, true)
        }
    }
}