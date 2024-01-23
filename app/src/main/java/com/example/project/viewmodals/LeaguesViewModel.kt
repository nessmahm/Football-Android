package com.example.project.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.modals.LeagueResponse
import com.example.project.modals.LeaguesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguesViewModel {
    private val leagueResponse = MutableLiveData<LeaguesResponse>()
    var leagues: LiveData<LeaguesResponse> = leagueResponse

    fun getLeagues() {
        RetrofitHelper.retrofitService.getLeagues("get_leagues").enqueue(
            object : Callback<LeaguesResponse> {
                override fun onResponse(
                    call: Call<LeaguesResponse>,
                    response: Response<LeaguesResponse>
                ) {
                    Log.d("Retrofit", response.body().toString())
                    if (response.isSuccessful) {
                        leagueResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<LeaguesResponse>, t: Throwable) {
                    Log.e("Retrofit", "API call failed: ${t.message}")
                }
            }
        )
    }
}
