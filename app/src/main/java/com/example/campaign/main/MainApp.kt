package com.example.campaign.main

import android.app.Application
import com.example.campaign.models.CampaignJSONStore
import com.example.campaign.models.CampaignStore
//import com.example.campaign.models.CampaignMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {

        lateinit var campaigns: CampaignStore

        override fun onCreate() {
            super.onCreate()
            Timber.plant(Timber.DebugTree())
            //campaigns = CampaignMemStore()
            campaigns = CampaignJSONStore(applicationContext)
            i("Campaign Add Selected")
        }
    }


