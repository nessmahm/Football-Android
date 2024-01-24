package com.example.project.activities

import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.api.LivescoreAdapter
import com.example.project.databinding.LivescoreFragmentBinding
import com.example.project.modals.LiveScoreResponse
import com.example.project.modals.LiveScoreResponseItem
import com.example.project.viewmodals.LiveScoreViewModel


class LiveScoreFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: LivescoreFragmentBinding
    private var liveScoreViewModel: LiveScoreViewModel = LiveScoreViewModel()
    private val handler = Handler(Looper.getMainLooper())
    private var liveScoreList: List<LiveScoreResponseItem> = emptyList()
    private val LOADER_CONTACTS = 100
    private val PERMISSION_CONTACTS = 101
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LivescoreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.liveScoreTable
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        liveScoreViewModel.liveScore.observe(viewLifecycleOwner) { liveScoreResponse ->
            liveScoreResponse?.let {
                if (it.isNotEmpty()) {
                    liveScoreList=it
                    updateRecyclerView(it)

                }
            }
        }
        startTimer()
    }
    private fun updateRecyclerView(livescore: LiveScoreResponse) {
        recyclerView.adapter = LivescoreAdapter(livescore)
    }

    private fun startTimer() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                // Code to execute every minute
                liveScoreViewModel.getLiveScores()

                // Schedule the next run after 1 minute
                handler.postDelayed(this, 60 * 1000)
            }
        }, 0)
    }

}
