package com.example.project.viewmodals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.api.RetrofitHelper
import com.example.project.modals.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaguesViewModel {
    private val leagueResponse = MutableLiveData<LeagueResponse>()
    var teams : LiveData<LeagueResponse> = leagueResponse
    fun getTeams(){
        RetrofitHelper.retrofitService.getLeagues("get_leagues").enqueue(
            object : Callback<LeagueResponse> {
                override fun onResponse(
                    call: Call<LeagueResponse>,
                    response: Response<LeagueResponse>
                ) {
                    Log.d("Retrofit", response.body().toString())
                    if(response.isSuccessful){
                        leagueResponse.value = response.body()
                        teams = leagueResponse
                    }
                }

                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    Log.e("Retrofit", "API call failed: ${t.message}")

                }
            }
        )
    }
}