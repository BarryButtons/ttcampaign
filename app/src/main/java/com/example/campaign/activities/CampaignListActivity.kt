package com.example.campaign.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.campaign.R
import com.example.campaign.main.MainApp

class CampaignListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_list)
        app = application as MainApp
    }
}