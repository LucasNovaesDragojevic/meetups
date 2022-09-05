package br.com.alura.meetups.repository

import android.util.Log
import br.com.alura.meetups.model.Dispositivo
import br.com.alura.meetups.webclient.DispositivoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "DispositivoRepository"

class DispositivoRepository(
    private val service: DispositivoService
) {

    fun salva(dispositivo: Dispositivo) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.salva(dispositivo)
                if (response.isSuccessful) {
                    Log.i(TAG,"token enviado ${dispositivo.token}")
                } else {
                    Log.e(TAG,"falha ao enviar o token")
                }
            } catch (e: Exception) {
                Log.e(TAG,"falha ao comunicar com o servidor", e)
            }
        }
    }
}