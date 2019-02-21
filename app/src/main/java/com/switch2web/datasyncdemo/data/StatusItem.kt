package com.switch2web.datasyncdemo.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class StatusItem :ViewModel(){
    lateinit var status: MutableLiveData<String>
}