package com.example.campaign.models

import timber.log.Timber.i

class CampaignMemStore : CampaignStore{
    val campaigns = ArrayList<CampaignModel>()

    override fun findAll(): List<CampaignModel>{
        return campaigns
    }

    override fun create(campaign: CampaignModel){
        campaigns.add(campaign)
        logAll()
    }
    fun logAll(){
        campaigns.forEach{ i("${it}")}
    }
}