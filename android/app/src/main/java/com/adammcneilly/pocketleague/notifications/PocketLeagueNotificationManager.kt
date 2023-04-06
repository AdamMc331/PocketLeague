package com.adammcneilly.pocketleague.notifications

import android.Manifest
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.adammcneilly.pocketleague.R
import com.adammcneilly.pocketleague.core.models.Match

object PocketLeagueNotificationManager {

    const val MATCH_STARTED_CHANNEL_ID = "MATCH_STARTED"

    /**
     * Given some [match], build a [Notification] to be posted to the user that lets them know this match
     * has begun.
     */
    fun buildMatchStartedNotification(
        match: Match,
        context: Context,
    ): Notification {
        val title = "${match.blueTeam.team.name} vs ${match.orangeTeam.team.name} Starting Now"

        return NotificationCompat.Builder(context, MATCH_STARTED_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground_monochrome)
            .setContentTitle(title)
            .setContentText("Click here to watch live!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    fun requestPermissionOrSendNotification(
        context: Context,
        notification: Notification,
        notificationId: Int,
    ) {
        val permissionResult = ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)

        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
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
