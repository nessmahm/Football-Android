package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.api.TeamsViewModel
import com.example.project.databinding.ActivityMainBinding
import com.example.project.modals.TeamsResponse
import com.example.project.ui.theme.ProjectTheme

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var footballViewModel : TeamsViewModel = TeamsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        footballViewModel.teams.observe(this) {
            if (it as TeamsResponse != null)
                println("daaaata"+it[0].team_badge)
            binding.text.text=it[0].team_badge
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjectTheme {
        Greeting("Android")
    }
}