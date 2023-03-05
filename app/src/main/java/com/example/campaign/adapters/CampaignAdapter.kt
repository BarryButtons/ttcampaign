package com.example.campaign.adapters

import android.text.InputType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campaign.models.CampaignModel
import com.example.campaign.databinding.CardCampaignBinding

interface CampaignListener{
    fun onCampaignClick(campaign:CampaignModel)
}
class CampaignAdapter constructor(private var campaigns: List<CampaignModel>,
                                private val listener: CampaignListener):
    RecyclerView.Adapter<CampaignAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCampaignBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val campaign = campaigns[holder.adapterPosition]
        holder.bind(campaign, listener)
    }

    override fun getItemCount(): Int = campaigns.size

    class MainHolder(private val binding : CardCampaignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(campaign: CampaignModel, listener: CampaignListener) {
            binding.campaignTitle.text = campaign.title
            binding.description.text = campaign.description
            binding.dmNotes.text = campaign.dmNotes
            //binding.players.inputType = InputType.TYPE_CLASS_NUMBER
            binding.root.setOnClickListener{listener.onCampaignClick(campaign)}

        }
    }
}
