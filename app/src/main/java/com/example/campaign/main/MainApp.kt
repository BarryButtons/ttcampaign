package com.example.campaign.main

import android.app.Application
import com.example.campaign.models.CampaignMemStore
import com.example.campaign.models.CampaignModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp: Application() {

   //val campaigns = ArrayList<CampaignModel>()
    val campaigns = CampaignMemStore()
    override fun onCreate(){
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Campaign Add Selected")
       // campaigns.add(CampaignModel("Forgotten Realms", "A good level one campaign for beginners"))

    }
}