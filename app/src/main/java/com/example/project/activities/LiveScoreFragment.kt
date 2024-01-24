package com.example.project.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.databinding.LivescoreFragmentBinding
import com.example.project.viewmodals.LiveScoreViewModel

class LiveScoreFragment : Fragment() {

    private lateinit var binding: LivescoreFragmentBinding
    private var liveScoreViewModel: LiveScoreViewModel = LiveScoreViewModel()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LivescoreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        liveScoreViewModel.liveScore.observe(viewLifecycleOwner) { liveScoreResponse ->
            liveScoreResponse?.let {
                if (it != null) {
                    Log.e("Retrofit livescore", ": ${it}")
                }
            }
        }
        startTimer()
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
