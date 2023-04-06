package com.adammcneilly.pocketleague.notifications

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.adammcneilly.pocketleague.R

/**
 * A set of helper functions and variables for managing notifications to be sent
 * in the Pocket League app.
 */
object NotificationHelper {

    const val MATCH_STARTED_CHANNEL_ID = "MATCH_STARTED"

    /**
     * Given the basic pieces of information to render a notification, make sure we have notification permissions
     * and if so, send the notification. Otherwise, we will request permission.
     */
    fun buildAndSendNotification(
        title: String,
        text: String,
        channelId: String,
        notificationId: Int,
        context: Context,
    ) {
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground_monochrome)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val permissionResult = ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)

        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            // If the permission is not granted, we need to request the permission, and then have the activity handle
            // the permission requested result.
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notification)
        }
    }
}
