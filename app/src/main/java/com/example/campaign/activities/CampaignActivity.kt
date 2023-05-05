package com.example.campaign.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.campaign.R
import com.squareup.picasso.Picasso
import com.example.campaign.databinding.ActivityCampaignBinding
import com.example.campaign.helpers.showImagePicker
import com.example.campaign.main.MainApp
import com.example.campaign.models.CampaignModel
import com.example.campaign.models.Location
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.i

class CampaignActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCampaignBinding
    var campaign = CampaignModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    //var location = Location(52.245696, -7.139102, 15f)
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        edit = false

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
            binding.sessionLocation.setText(campaign.sessionLocation)
            binding.nextSession.setText(campaign.nextSession)
            // binding.players.setText(campaign.players.toString())
            binding.btnAdd.setText(R.string.save_campaign)
            Picasso.get()
                .load(campaign.image)
                .into(binding.campaignImage)
            if (campaign.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_campaign_image)
            }
        }
        binding.btnAdd.setOnClickListener() {
            campaign.title = binding.campaignTitle.text.toString()
            campaign.description = binding.description.text.toString()
            campaign.dmNotes = binding.dmNotes.text.toString()
            campaign.sessionLocation = binding.sessionLocation.text.toString()
            campaign.nextSession = binding.nextSession.text.toString()
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
        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher,this)
        }

        binding.campaignLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (campaign.zoom != 0f){
                location.lat = campaign.lat
                location.lng = campaign.lng
                location.zoom = campaign.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_campaign, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.item_delete -> {
                setResult(99)
                app.campaigns.delete(campaign)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            campaign.image = image

                            Picasso.get()
                                .load(campaign.image)
                                .into(binding.campaignImage)
                            binding.chooseImage.setText(R.string.change_campaign_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }


    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            campaign.lat = location.lat
                            campaign.lng = location.lng
                            campaign.zoom = location.zoom
                        }
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }



}
