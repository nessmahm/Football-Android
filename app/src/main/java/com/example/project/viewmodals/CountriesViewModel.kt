package com.example.project.viewmodals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.api.RetrofitHelper
import com.example.project.modals.CountryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesViewModel {
    private val countriesResponse = MutableLiveData< CountryResponse >()
    var countries : LiveData<CountryResponse> = countriesResponse
    fun getCountries(){
        RetrofitHelper.retrofitService.getCountries("get_countries").enqueue(
            object : Callback<CountryResponse> {
                override fun onResponse(
                    call: Call<CountryResponse>,
                    response: Response<CountryResponse>
                ) {
                    if(response.isSuccessful){
                        Log.e("Retrofit Countries", response.body().toString())

                        countriesResponse.value = response.body()
                        countries = countriesResponse
                    }
                }

                override fun onFailure(call: Call<CountryResponse>, t: Throwable) {
                    Log.e("Retrofit", "API call failed: ${t.message}")

                }
            }
        )
    }
}