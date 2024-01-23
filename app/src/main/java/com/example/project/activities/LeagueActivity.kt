package com.example.project.activities

import LeagueAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.api.LeaguesViewModel
import com.example.project.databinding.LeagueActivityBinding
import com.example.project.modals.LeaguesResponseItem

class LeagueActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: LeagueActivityBinding
    private lateinit var searchEditText: EditText
    private val leaguesViewModel: LeaguesViewModel = LeaguesViewModel()
    private var originalLeaguesList: List<LeaguesResponseItem> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LeagueActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        recyclerView = binding.leagueRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Search EditText
        searchEditText = binding.searchEditText
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterLeagues(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Fetch leagues from API
        leaguesViewModel.getLeagues()

        // Observe the LiveData for leagues
        leaguesViewModel.leagues.observe(this) { leaguesResponse ->
            leaguesResponse?.let {
                if (it.isNotEmpty()) {
                    originalLeaguesList = it
                    updateRecyclerView(it)
                }
            }
        }
    }

    private fun filterLeagues(query: String) {
        val filteredLeagues = originalLeaguesList.filter { league ->
            league.league_name.contains(query, true) || league.country_name.contains(query, true)
        }
        updateRecyclerView(filteredLeagues)
    }

    private fun updateRecyclerView(leagues: List<LeaguesResponseItem>) {
        recyclerView.adapter = LeagueAdapter(leagues) { leagueId ->
            navigateToTeamActivity(leagueId)
        }
    }

    private fun navigateToTeamActivity(leagueId: String) {
        val intent = Intent(this, TeamActivity::class.java)
        intent.putExtra("league_id", leagueId)
        startActivity(intent)
    }
}
