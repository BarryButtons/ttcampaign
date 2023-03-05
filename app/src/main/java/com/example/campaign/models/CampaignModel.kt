package com.example.campaign.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CampaignModel(   var id: Long = 0,
                            var title: String ="",
                            var description: String = "",
                            var dmNotes: String = ""

                            //var players: Int = 0
                                                    ) : Parcelable
