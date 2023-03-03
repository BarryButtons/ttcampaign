package com.example.campaign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.campaign.databinding.ActivityCampaignBinding
import timber.log.Timber
import timber.log.Timber.i

class CampaignActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCampaignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCampaignBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_campaign)

        Timber.plant(Timber.DebugTree())

        i("Campaign Tracker has begun.....")

       binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }
    }
}