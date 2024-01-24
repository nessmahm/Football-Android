package com.example.project.activities

import LeagueAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.api.LeaguesViewModel
import com.example.project.databinding.LeagueFragmentBinding
import com.example.project.modals.LeaguesResponseItem

class LeagueFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: LeagueFragmentBinding
    private lateinit var searchEditText: EditText
    private var leaguesViewModel: LeaguesViewModel = LeaguesViewModel()
    private var originalLeaguesList: List<LeaguesResponseItem> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LeagueFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView
        recyclerView = binding.leagueRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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
        leaguesViewModel.leagues.observe(viewLifecycleOwner) { leaguesResponse ->
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
        recyclerView.adapter = LeagueAdapter(leagues) { leagueId, leagueName ->
            navigateToTeamActivity(leagueId, leagueName)
        }
    }

    private fun navigateToTeamActivity(leagueId: String, leagueName: String) {
        val intent = Intent(requireContext(), TeamActivity::class.java)
        intent.putExtra("league_id", leagueId)
        intent.putExtra("league_name", leagueName)
        startActivity(intent)
    }
}
