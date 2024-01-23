package com.example.project.activities

import LeagueAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.api.LeaguesViewModel
import com.example.project.databinding.LeagueActivityBinding

class LeagueActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: LeagueActivityBinding
    private val leaguesViewModel: LeaguesViewModel = LeaguesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LeagueActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        leaguesViewModel.getLeagues()

        leaguesViewModel.leagues.observe(this) { leaguesResponse ->
            leaguesResponse?.let {
                if (it.isNotEmpty()) {
                    binding.leagueRecyclerView.adapter = LeagueAdapter(it) { leagueId ->
                        navigateToTeamActivity(leagueId)
                    }
                }
            }
        }

        binding.leagueRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@LeagueActivity)
            adapter = LeagueAdapter(leaguesViewModel.leagues.value ) { leagueId ->
                navigateToTeamActivity(leagueId)
            }
        }
    }

    private fun navigateToTeamActivity(leagueId: String) {
        val intent = Intent(this, TeamActivity::class.java)
        intent.putExtra("league_id", leagueId)
        startActivity(intent)
    }
}
