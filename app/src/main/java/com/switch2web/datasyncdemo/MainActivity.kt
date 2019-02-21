package com.switch2web.datasyncdemo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.ivdisplays.tsrds.syncdata.SyncData
import com.switch2web.datasyncdemo.data.StatusItem
import kotlinx.android.synthetic.main.activity_main.*
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent





class MainActivity : AppCompatActivity() {
    var statusItem: StatusItem?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        onActionPerform()

    }

    private fun onActionPerform() {

    }

    private fun initialize() {
        val lbm = LocalBroadcastManager.getInstance(this)
        lbm.registerReceiver(receiver, IntentFilter("filter_string"))
        statusItem = ViewModelProviders.of(this).get(StatusItem::class.java)
        statusItem!!.status = MutableLiveData()
        val syncData = OneTimeWorkRequest.Builder(SyncData::class.java)
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(
                    NetworkType.CONNECTED).build()).build()
        val workManager = WorkManager.getInstance()
        workManager.beginWith(syncData)
            .enqueue()


        statusItem?.status?.observe(this, Observer { s->
            tvStatus.text = s
        })

    }


    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                val str = intent.getStringExtra("key")
                statusItem?.status?.value = str
            }
        }
    }
}
