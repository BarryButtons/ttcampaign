package com.example.campaign.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campaign.R
import com.example.campaign.databinding.ActivityCampaignListBinding
import com.example.campaign.databinding.CardCampaignBinding
import com.example.campaign.main.MainApp
import com.example.campaign.models.CampaignModel

class CampaignListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityCampaignListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCampaignListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = CampaignAdapter(app.campaigns)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

class CampaignAdapter constructor(private var campaigns: List<CampaignModel>) :
        RecyclerView.Adapter<CampaignAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCampaignBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val campaign = campaigns[holder.adapterPosition]
        holder.bind(campaign)
    }

    override fun getItemCount(): Int = campaigns.size

    class MainHolder(private val binding : CardCampaignBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(campaign: CampaignModel) {
                    binding.campaignTitle.text = campaign.title
                    binding.description.text = campaign.description
                }
            }
}
