package com.example.project.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project.api.LeaguesViewModel
import com.example.project.databinding.ActivityMainBinding
import com.example.project.ui.theme.ProjectTheme
import com.example.project.viewmodals.CountriesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var footballViewModel : LeaguesViewModel = LeaguesViewModel()
    var countriesViewModel : CountriesViewModel = CountriesViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val spinner:AutoCompleteTextView  = binding.spinner
        setContentView(binding.root)
        countriesViewModel.countries.observe(this) { countries ->
            val countryNames = countries.map { it.country_name }
            spinner.setAdapter(ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,countryNames))

        }
        countriesViewModel.getCountries()

        spinner.setOnItemClickListener { parent, view, position, id ->
            // Retrieve the selected CountryResponseItem and access its ID
            val selectedCountry = countriesViewModel.countries.value?.get(position)
            val selectedCountryId = selectedCountry?.country_id
            Log.e("Retrofit countryy", ": ${selectedCountryId}")
        }

        binding.testbutton2.setOnClickListener {
                _ ->  val intent = Intent(this, TeamDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.testbutton1.setOnClickListener {
                _ ->  val intent = Intent(this, LeagueActivity::class.java)
            startActivity(intent)
        }
        binding.testbutton3.setOnClickListener {
                _ ->  val intent = Intent(this, LiveScoreActivity::class.java)
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