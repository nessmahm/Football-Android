package com.example.project.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.project.api.CoachAdapter
import com.example.project.api.PlayerAdapter
import com.example.project.databinding.TeamDetailsActivityBinding
import com.example.project.viewmodals.TeamsViewModel

class TeamDetailsActivity : ComponentActivity() {
    private lateinit var binding : TeamDetailsActivityBinding
    var footballViewModel : TeamsViewModel = TeamsViewModel()
    private lateinit var coachAdapter: CoachAdapter
    private lateinit var playerAdapter: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize binding

        title = intent.getStringExtra("team_name").toString()
        binding = TeamDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up RecyclerViews
        binding.coachRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.playerRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch team details and observe coach and player lists
        val teamId: String = intent.getStringExtra("team_id").toString()
        footballViewModel.getTeam(teamId)

        footballViewModel.teams.observe(this) { teamsResponse ->
            teamsResponse?.let {
                if (it.size>0) {
                    val team = it[0]
                    binding.teamCountry.text=team.team_country
                    binding.teamName.text=team.team_name
                    binding.teamFoundationTime.text=team.team_founded
                    val venueObject = team.venue
                    binding.teamVenueAddress.text=venueObject.venue_address
                    binding.teamVenueCapacity.text=venueObject.venue_capacity
                    binding.teamVenueCity.text=venueObject.venue_city
                    binding.teamVenueName.text=venueObject.venue_name

                    if (binding.teamVenueAddress.text == "") {
                        binding.teamVenueAddress.visibility = android.view.View.GONE
                        binding.teamVenueAddressLabel.visibility = android.view.View.GONE
                    }
                    if (binding.teamVenueCapacity.text == "" ) {
                        binding.teamVenueCapacity.visibility = android.view.View.GONE
                        binding.teamVenueCapacityLabel.visibility = android.view.View.GONE
                    }
                    if (binding.teamVenueCity.text == "") {
                        binding.teamVenueCity.visibility = android.view.View.GONE
                        binding.teamVenueCityLabel.visibility = android.view.View.GONE
                    }
                    if (binding.teamVenueName.text == "") {
                        binding.teamVenueName.visibility = android.view.View.GONE
                        binding.teamVenueNameLabel.visibility = android.view.View.GONE
                    }
                    if (binding.teamCountry.text == "") {
                        binding.teamCountry.visibility = android.view.View.GONE
                        binding.teamCountryLabel.visibility = android.view.View.GONE
                    }
                    if (binding.teamFoundationTime.text == "") {
                        binding.teamFoundationTime.visibility = android.view.View.GONE
                        binding.teamFoundationTimeLabel.visibility = android.view.View.GONE
                    }

                    if (it[0].coaches.isNotEmpty()) {
                        binding.coachRecyclerView.adapter = CoachAdapter(it[0].coaches)
                    }

                    if (it[0].players.isNotEmpty()) {
                        binding.playerRecyclerView.adapter = PlayerAdapter(it[0].players)
                    }

                    Glide.with(this)
                        .load(it[0].team_badge)
                        .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: caching strategy
                        .into(binding.teamLogo)
                }

            }
        }

    }
}
