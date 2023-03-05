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

        var edit = false

        binding = ActivityCampaignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        if (intent.hasExtra("campaign_edit")) {
            edit = true
            campaign = intent.extras?.getParcelable("campaign_edit")!!
            binding.campaignTitle.setText(campaign.title)
            binding.description.setText(campaign.description)
            binding.dmNotes.setText(campaign.dmNotes)
           // binding.players.setText(campaign.players.toString())
            binding.btnAdd.setText(R.string.save_campaign)
        }

        binding.btnAdd.setOnClickListener() {
            campaign.title = binding.campaignTitle.text.toString()
            campaign.description = binding.description.text.toString()
            campaign.dmNotes = binding.dmNotes.text.toString()
           // campaign.players = binding.players.text.toString().toIntOrNull() ?:0
            if (campaign.title.isNotEmpty()) {
                if (edit) {
                    app.campaigns.update(campaign.copy())
                } else {
                    app.campaigns.create(campaign.copy())
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, R.string.enter_campaign_title, Snackbar.LENGTH_LONG).show()
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
