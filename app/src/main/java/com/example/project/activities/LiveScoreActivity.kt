package com.example.project.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import com.example.project.activities.ui.theme.ProjectTheme
import com.example.project.api.TeamAdapter
import com.example.project.databinding.LivescoreActivityBinding
import com.example.project.databinding.TeamActivityBinding
import com.example.project.viewmodals.LiveScoreViewModel
import com.example.project.viewmodals.TeamsViewModel
import com.google.firebase.perf.util.Timer
import java.util.TimerTask

class LiveScoreActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding : LivescoreActivityBinding
    var liveScoreViewModel : LiveScoreViewModel = LiveScoreViewModel()
    val timer = Timer()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LivescoreActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveScoreViewModel.liveScore.observe(this) { liveScoreResponse ->
            liveScoreResponse?.let {
                if (it != null) {
                    Log.e("Retrofit livescore", ": ${it}")
                }
            }
        }
        startTimer()

    }
    fun startTimer() {
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

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    ProjectTheme {
        Greeting5("Android")
    }
}