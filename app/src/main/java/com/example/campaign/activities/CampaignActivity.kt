package com.example.campaign.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.campaign.R
import com.example.campaign.databinding.ActivityCampaignBinding
import com.example.campaign.models.CampaignModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.i

class CampaignActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCampaignBinding
    var campaign = CampaignModel()
    val campaigns = ArrayList<CampaignModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCampaignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        i("Campaign Tracker has begun.....")

        binding.btnAdd.setOnClickListener() {
            campaign.title = binding.campaignTitle.text.toString()
            campaign.description = binding.description.text.toString()
            if (campaign.title.isNotEmpty()) {
                campaigns.add(campaign)
                i("add Button Pressed:${campaign}")
                for (i in campaigns.indices) {
                    i("Campaign[$i]:${this.campaigns[i]}")
                }
            }
                else  {
                    Snackbar.make(it, "Campaign needs a title", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
