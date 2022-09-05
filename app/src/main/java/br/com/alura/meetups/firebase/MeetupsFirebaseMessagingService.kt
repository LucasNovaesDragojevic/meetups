package br.com.alura.meetups.firebase

import android.util.Log
import br.com.alura.meetups.model.Dispositivo
import br.com.alura.meetups.preferences.FirebaseTokenPreferences
import br.com.alura.meetups.repository.DispositivoRepository
import com.google.firebase.messaging.FirebaseMessagingService
import org.koin.android.ext.android.inject

private const val TAG = "Messaging token"

class MeetupsFirebaseMessagingService : FirebaseMessagingService() {

    private val dispositivoRepository: DispositivoRepository by inject()
    private val preferences: FirebaseTokenPreferences by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        preferences.tokenNovo()
        dispositivoRepository.salva(Dispositivo(token = token))
    }
}