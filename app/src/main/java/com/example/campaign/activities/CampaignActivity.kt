package com.example.campaign.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.campaign.R
import com.example.campaign.databinding.ActivityCampaignBinding
import com.example.campaign.main.MainApp
import com.example.campaign.models.CampaignModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.i

class CampaignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampaignBinding
    var campaign = CampaignModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCampaignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Activity Started")
        binding.btnAdd.setOnClickListener() {
            campaign.title = binding.campaignTitle.text.toString()
            campaign.description = binding.description.text.toString()
            if (campaign.title.isNotEmpty()) {
                app.campaigns.create(campaign.copy())
                setResult(RESULT_OK)
                finish()
            }
                else  {
                    Snackbar.make(it, "Campaign needs a title", Snackbar.LENGTH_LONG).show()
                }
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_campaign,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    }
