package com.example.project

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.api.LeaguesViewModel
import com.example.project.databinding.ActivityMainBinding
import com.example.project.ui.theme.ProjectTheme

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var footballViewModel : LeaguesViewModel = LeaguesViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        footballViewModel.getTeams()
        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        footballViewModel.teams.observe(this) { teamsResponse ->
            teamsResponse?.let {
                println("daaaata" + it[0].league_name)
                binding.text.text = it[0].league_name
            }
        }
        binding.testbutton.setOnClickListener {
            _ ->  val intent = Intent(this, TeamActivity::class.java)
            startActivity(intent)
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