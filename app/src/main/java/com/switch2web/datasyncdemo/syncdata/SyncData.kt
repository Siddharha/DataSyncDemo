package com.ivdisplays.tsrds.syncdata

import android.util.Log
import androidx.work.Worker
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent





class SyncData : Worker() {
    override fun doWork(): WorkerResult = try {
        Log.e("sid","Running!")
        val intent = Intent("filter_string")
        intent.putExtra("key", "Internet Connected!")
        // put your all data using put extra

        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        WorkerResult.SUCCESS
    } catch (e: Throwable) {
        WorkerResult.FAILURE
    }

}