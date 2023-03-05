package com.example.campaign.models

interface CampaignStore {
    fun findAll(): List<CampaignModel>
    fun create(campaign: CampaignModel)
    fun update(campaign : CampaignModel)
}