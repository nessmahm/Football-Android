package com.example.project.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.project.activities.ui.theme.ProjectTheme
import com.example.project.api.TeamAdapter
import com.example.project.databinding.ActivityMainBinding
import com.example.project.databinding.TeamActivityBinding
import com.example.project.databinding.TeamDetailsActivityBinding
import com.example.project.modals.Venue
import com.example.project.viewmodals.TeamsViewModel

class TeamDetailsActivity : ComponentActivity() {
    private lateinit var binding : TeamDetailsActivityBinding
    var footballViewModel : TeamsViewModel = TeamsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize binding
        binding = TeamDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        footballViewModel.getTeam("2626")

        footballViewModel.teams.observe(this) { teamsResponse ->
            teamsResponse?.let {
                if (it != null && it.size>0) {
                    println("----daata ----"+it)
                    binding.teamCity.text=it[0].team_country
                    binding.teamName.text=it[0].team_name
                    binding.foundationTime.text=it[0].team_founded
                    if(it[0].venue is Venue)
                    {
                        val venueObject = it[0].venue as Venue
                        binding.venueAddress.text=venueObject.venue_address
                        binding.venueCapacity.text=venueObject.venue_capacity
                        binding.venueCity.text=venueObject.venue_city
                        binding.venueName.text=venueObject.venue_name
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

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ProjectTheme {
        Greeting4("Android")
    }
}