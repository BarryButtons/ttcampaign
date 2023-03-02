package com.example.campaign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.i

class CampaignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compaign)

        Timber.plant(Timber.DebugTree())

        i("Campaign Tracker has begun.....")
    }
}