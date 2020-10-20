package com.example.backgroundtaskproject.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.backgroundtaskproject.R

class CountDownService : Service() {

    companion object{
        val countdownBr = "com.example.backgroundtaskproject.utils.countdown_br"
    }


    var intent = Intent(countdownBr)
    lateinit var countDownTimer : CountDownTimer

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()

        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
        else
        {
            ""
        }

        val builder = NotificationCompat.Builder(this, channelId)
        val notification = builder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("App is running in background")
                .build()
        startForeground(101,notification)

        countDownTimer = object :CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                intent.putExtra("countdown",p0)
                sendBroadcast(intent)
            }

            override fun onFinish() {
                stopSelf()
            }
        }
        countDownTimer.start()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() : String{
        val channelId ="countDownTimerService"
        val channelName = "Count Down Timer Channel"
        val channel =  NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }
}