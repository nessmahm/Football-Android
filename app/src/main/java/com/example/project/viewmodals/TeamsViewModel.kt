package com.example.project.viewmodals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.api.RetrofitHelper
import com.example.project.modals.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel {
        private val teamsResponse = MutableLiveData< TeamsResponse >()
        var teams : LiveData<TeamsResponse> = teamsResponse
        fun getTeams(leagueId: String){
                RetrofitHelper.retrofitService.getTeamsByLeague(leagueId).enqueue(
                        object : Callback<TeamsResponse> {
                                override fun onResponse(
                                        call: Call<TeamsResponse>,
                                        response: Response<TeamsResponse>
                                ) {
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
        fun getTeam(teamId:String){
                RetrofitHelper.retrofitService.getTeam(teamId).enqueue(
                        object : Callback<TeamsResponse> {
                                override fun onResponse(
                                        call: Call<TeamsResponse>,
                                        response: Response<TeamsResponse>
                                ) {
                                        Log.d("Retrofit teams", response.body().toString())
                                        if(response.isSuccessful ){
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