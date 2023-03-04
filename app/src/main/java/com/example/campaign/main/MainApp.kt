package com.example.campaign.main

import android.app.Application
import com.example.campaign.models.CampaignModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {

   val campaigns = ArrayList<CampaignModel>()

    override fun onCreate(){
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Application Started")
    }
}