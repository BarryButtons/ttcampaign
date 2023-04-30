package com.example.campaign.main

import android.app.Application
import com.example.campaign.models.CampaignMemStore
import com.example.campaign.models.CampaignModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {

        lateinit var campaigns: CampaignMemStore

        override fun onCreate() {
            super.onCreate()
            Timber.plant(Timber.DebugTree())
            campaigns = CampaignMemStore()
            i("Campaign Add Selected")
        }
    }


