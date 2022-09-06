package br.com.alura.meetups.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import br.com.alura.meetups.R

const val ID_CANAL_PRINCIPAL = "7593"

class CanalPrincipal(
    private val context: Context,
    private val gerenciadorNotificacoes: NotificationManager
) {

    fun criaCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nomeCanal = context.getString(R.string.canal_principal_nome)
            val descricaoCanal = context.getString(R.string.canal_principal_descricao)
            val importanciaCanal = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(ID_CANAL_PRINCIPAL, nomeCanal, importanciaCanal)
            canal.description = descricaoCanal
            gerenciadorNotificacoes.createNotificationChannel(canal)
        }
    }

}