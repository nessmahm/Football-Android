package com.example.project
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.api.TeamsViewModel
import com.example.project.databinding.ActivityMainBinding
import com.example.project.ui.theme.ProjectTheme
import java.io.InputStream
import java.net.URL

class TeamDetailsActivity : ComponentActivity() {
    private lateinit var binding : ActivityMainBinding
    var footballViewModel : TeamsViewModel = TeamsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        footballViewModel.getTeam()
        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUrl = "https://apiv3.apifootball.com/badges/76_real-madrid.jpg"
        footballViewModel.teams.observe(this) { teamsResponse ->
            teamsResponse?.let {
                println("daaaata" + it[0].team_badge)
                binding.text.text = it[0].team_badge
            }
            val inStream: InputStream = URL(imageUrl).openStream()
            val bmp: Bitmap = BitmapFactory.decodeStream(inStream)

            val imgResultsExperience: ImageView = findViewById(R.id.team_logo)
            imgResultsExperience.setImageBitmap(bmp)

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