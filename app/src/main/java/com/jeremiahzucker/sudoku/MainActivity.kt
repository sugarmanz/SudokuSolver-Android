package com.jeremiahzucker.sudoku

import android.annotation.TargetApi
import android.app.NotificationManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.app.NotificationChannel
import android.graphics.Color
import android.os.Build


class MainActivity : AppCompatActivity() {

    private val notificationManager get() =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val notificationBuilder by lazy {
        NotificationCompat.Builder(this, notificationChannel)
                .setSmallIcon(R.drawable.abc_ic_star_black_36dp)
    }
    private fun updateNotificationBuilder() =
            notificationBuilder.setContentTitle(notificationTitle).setContentText(notificationContent)
    private val notificationChannel = "SampleChannel"
    private val notificationTitle get() = notification_title.text.toString()
    private val notificationContent get() = content.text.toString()
    private val notificationID get() = id.text.toString().toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        @TargetApi(Build.VERSION_CODES.O)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(notificationChannel, notificationChannel, NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = "Sample channel"
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mChannel.setShowBadge(false)
            notificationManager.createNotificationChannel(mChannel)
        }

        notification_button.setOnClickListener {
            notificationManager.notify(notificationID, updateNotificationBuilder().build())
        }
    }

}
