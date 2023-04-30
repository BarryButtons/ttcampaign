package com.example.campaign.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.example.campaign.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "campaign.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<CampaignModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class CampaignJSONStore(private val context: Context) : CampaignStore {

    var campaigns = mutableListOf<CampaignModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<CampaignModel> {
        logAll()
        return campaigns
    }

    override fun create(campaign: CampaignModel) {
        campaign.id = generateRandomId()
        campaigns.add(campaign)
        serialize()
    }


    override fun update(campaign: CampaignModel) {
        val campaignsList = findAll() as ArrayList<CampaignModel>
        var foundCampaign: CampaignModel? = campaignsList.find { p -> p.id == campaign.id }
        if (foundCampaign != null) {
            foundCampaign.title = campaign.title
            foundCampaign.description = campaign.description
            foundCampaign.image = campaign.image
            foundCampaign.lat = campaign.lat
            foundCampaign.lng = campaign.lng
            foundCampaign.zoom = campaign.zoom
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(campaigns, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        campaigns = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        campaigns.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}
