package com.example.project.activities

import com.example.project.api.TeamAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.viewmodals.TeamsViewModel
import com.example.project.databinding.TeamActivityBinding
import com.example.project.ui.theme.ProjectTheme

class TeamActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding : TeamActivityBinding
    var footballViewModel : TeamsViewModel = TeamsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize binding

        binding = TeamActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        footballViewModel.getTeams("2")

        footballViewModel.teams.observe(this) { teamsResponse ->
            teamsResponse?.let {
                if (it != null) {
                    binding.recyclerView.adapter = TeamAdapter(footballViewModel.teams.value)

                }
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TeamActivity)
            adapter = TeamAdapter(footballViewModel.teams.value)
        }
    }

}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ProjectTheme {
        Greeting3("Android")
    }
}