package com.example.project.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.api.TeamAdapter
import com.example.project.databinding.TeamActivityBinding
import com.example.project.viewmodals.TeamsViewModel

class TeamActivity : AppCompatActivity() {
    private lateinit var binding : TeamActivityBinding
    var footballViewModel : TeamsViewModel = TeamsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = intent.getStringExtra("league_name").toString()
        // Initialize binding
        val league : String = intent.getStringExtra("league_id").toString()
        binding = TeamActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        footballViewModel.getTeams(league)

        footballViewModel.teams.observe(this) { teamsResponse ->
            teamsResponse?.let {
                binding.recyclerView.adapter = TeamAdapter(footballViewModel.teams.value ) { team, teamName ->
                    onTeamClicked(team, teamName)
                }
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TeamActivity)
            adapter = TeamAdapter(footballViewModel.teams.value) { team, teamName ->
                onTeamClicked(team, teamName)
            }
        }
    }

    private fun onTeamClicked(teamId: String, teamName: String) {
        val intent = Intent(this, TeamDetailsActivity::class.java)
        intent.putExtra("team_id", teamId)
        intent.putExtra("team_name", teamName)
        startActivity(intent)
    }


}