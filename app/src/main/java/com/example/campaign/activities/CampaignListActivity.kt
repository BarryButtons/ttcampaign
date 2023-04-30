package com.example.campaign.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campaign.R
import com.example.campaign.adapters.CampaignAdapter
import com.example.campaign.adapters.CampaignListener
import com.example.campaign.databinding.ActivityCampaignListBinding
import com.example.campaign.main.MainApp
import com.example.campaign.models.CampaignModel
import timber.log.Timber.i


class CampaignListActivity : AppCompatActivity(), CampaignListener {



    lateinit var app: MainApp
    private lateinit var binding: ActivityCampaignListBinding
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCampaignListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = CampaignAdapter(app.campaigns.findAll(),this)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, CampaignActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.campaigns.findAll().size)
            }
        }

    override fun onCampaignClick(campaign: CampaignModel, pos : Int) {
        val launcherIntent = Intent(this,CampaignActivity::class.java)
        launcherIntent.putExtra("campaign_edit",campaign)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode == Activity.RESULT_OK){
                (binding.recyclerView.adapter)?.
                        notifyItemRangeChanged(0,app.campaigns.findAll().size)
            }
            else // Deleting
                if (it.resultCode == 99)
                    (binding.recyclerView.adapter)?.notifyItemRemoved(position)
        }



}


