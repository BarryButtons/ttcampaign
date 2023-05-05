package com.example.campaign.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long{
    return lastId ++
}

class CampaignMemStore : CampaignStore{

    private val campaigns = ArrayList<CampaignModel>()

    override fun findAll(): List<CampaignModel>{
        return campaigns
    }

    override fun create(campaign: CampaignModel){
        campaign.id = getId()
        campaigns.add(campaign)
        logAll()
    }

    override fun update(campaign: CampaignModel){
        var foundCampaign: CampaignModel? = campaigns.find{p -> p.id == campaign.id}

        if (foundCampaign !=null){
            foundCampaign.title = campaign.title
            foundCampaign.description = campaign.description
            foundCampaign.dmNotes = campaign.dmNotes
            foundCampaign.nextSession = campaign.nextSession
            foundCampaign.sessionLocation = campaign.sessionLocation
            foundCampaign.image = campaign.image
            foundCampaign.lat = campaign.lat
            foundCampaign.lng = campaign.lng
            foundCampaign.zoom = campaign.zoom
            logAll()
        }
    }
    private fun logAll(){
        campaigns.forEach{ i("$it")}
    }
    override fun delete(campaign: CampaignModel) {
       campaigns.remove(campaign)
    }

}