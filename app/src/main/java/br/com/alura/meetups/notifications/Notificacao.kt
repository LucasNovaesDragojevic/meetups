package br.com.alura.meetups.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import br.com.alura.meetups.R
import br.com.alura.meetups.ui.activity.MainActivity
import coil.imageLoader
import coil.request.ImageRequest
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextUInt

class Notificacao(
    private val context: Context
) {

    private val gerenciadorNotificacoes by lazy {
        context.getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun mostra(data: Map<String, String?>) {
        CoroutineScope(Dispatchers.IO).launch {
            val imagem = tentaBuscaImagem(data["imagem"])
            val estilo = criaEstilo(imagem, data)
            val notificacao = criaNotificacao(data, estilo)
            gerenciadorNotificacoes.notify(Random.nextInt(), notificacao)
        }
    }

    private fun criaEstilo(
        imagem: Bitmap?,
        data: Map<String, String?>,
    ) = imagem?.let {
        NotificationCompat.BigPictureStyle().bigPicture(it)
    } ?: NotificationCompat.BigTextStyle().bigText(data["descricao"])

    private fun criaNotificacao(
        data: Map<String, String?>,
        estilo: NotificationCompat.Style,
    ): Notification? {

        return NotificationCompat.Builder(context, ID_CANAL_PRINCIPAL)
            .setContentTitle(data["titulo"])
            .setContentText(data["descricao"])
            .setSmallIcon(R.drawable.ic_acao_lista_eventos)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(criaPedindIntent())
            .setAutoCancel(true)
            .setStyle(estilo)
            .build()
    }

    private fun criaPedindIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    private suspend fun tentaBuscaImagem(imagem: String?): Bitmap? {
        val request = ImageRequest.Builder(context)
            .data(imagem)
            .build()

        return context.imageLoader.execute(request).drawable?.toBitmap()
    }

}
