package com.example.testnotification2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.testnotification2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val CHANNEL_ID = "channel_id_example_01"
    private val CHANNEL_ID2 = "channel_id_example_02"
    private val notificationId = 101
    private val notificationId2 = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        createNotificationChannel2()
        binding.button.setOnClickListener {
            sendNotification()
        }
        binding.button2.setOnClickListener {
            sendNotification2()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification title"
            val descriptionText = "Notification Description"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel =
                NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun createNotificationChannel2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification title 2"
            val descriptionText = "Notification Description 2"
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel =
                NotificationChannel(CHANNEL_ID2, name, importance).apply {
                    description = descriptionText
                }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

        private fun sendNotification() {
            val intent = Intent(this,MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(this , 0, intent ,0)

            val bitMap = BitmapFactory.decodeResource(applicationContext.resources,R.mipmap.ic_photo)
//            val bitMap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.icon_small_1)
            val bitMapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources,R.mipmap.ic_launcher)
            val bitMapLargeIcon2 = Bitmap.createBitmap(100, 100,
                Bitmap.Config.ARGB_8888)
            val bitMapLargeIcon3 = BitmapFactory.decodeResource(applicationContext.resources,R.mipmap.ic_launcher)





        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.icon_small_1)
            .setContentTitle("Example Title")
            .setContentText("Example Description")
            .setLargeIcon(bitMapLargeIcon)
//            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitMap))
            .setStyle(NotificationCompat.BigTextStyle().bigText("Example DescriptionExample DescriptionExample DescriptionExample DescriptionExample DescriptionExample DescriptionExample Description"))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

            with(NotificationManagerCompat.from(this)){
                notify(notificationId,builder.build())
            }
        }
    private fun sendNotification2() {
        val builder = NotificationCompat.Builder(this,CHANNEL_ID2)
            .setSmallIcon(R.drawable.icon_small_2)
            .setContentTitle("Example Title 2")
            .setContentText("Example Description 2")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId2,builder.build())
        }
    }
}

