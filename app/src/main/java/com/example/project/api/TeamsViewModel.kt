package com.example.project.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.modals.LeagueResponse
import com.example.project.modals.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel {
        private val teamsResponse = MutableLiveData< TeamsResponse >()
        var teams : LiveData<TeamsResponse> = teamsResponse
        fun getTeam(){
                RetrofitHelper.retrofitService.getTeam("get_teams&league_id=302").enqueue(
                        object : Callback<TeamsResponse> {
                                override fun onResponse(
                                        call: Call<TeamsResponse>,
                                        response: Response<TeamsResponse>
                                ) {
                                        Log.d("Retrofit teams", response.body().toString())
                                        if(response.isSuccessful){
                                                teamsResponse.value = response.body()
                                                teams = teamsResponse
                                        }
                                }

                                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                                        Log.e("Retrofit", "API call failed: ${t.message}")

                                }
                        }
                )
        }


}