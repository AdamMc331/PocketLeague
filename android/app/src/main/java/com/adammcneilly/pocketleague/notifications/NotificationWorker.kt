package com.adammcneilly.pocketleague.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * A [Worker] implementation that is used to send a notification. All notification
 * information is passed in the input data.
 */
class NotificationWorker(
    private val context: Context,
    params: WorkerParameters,
) : Worker(context, params) {
    override fun doWork(): Result {
        NotificationHelper.buildAndSendNotification(
            title = inputData.getString("title").toString(),
            text = inputData.getString("text").toString(),
            channelId = inputData.getString("channelId").toString(),
            notificationId = inputData.getString("notificationId")?.toIntOrNull() ?: 0,
            context = context,
        )

        return Result.success()
    }
}
