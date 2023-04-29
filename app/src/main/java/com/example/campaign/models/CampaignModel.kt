package com.example.campaign.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CampaignModel(   var id: Long = 0,
                            var title: String ="",
                            var description: String = "",
                            var dmNotes: String = "",
                            var image: Uri = Uri.EMPTY,
                            var lat : Double = 0.0,
                            var lng: Double = 0.0,
                            var zoom: Float = 0f) : Parcelable

                            //var players: Int = 0


@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
