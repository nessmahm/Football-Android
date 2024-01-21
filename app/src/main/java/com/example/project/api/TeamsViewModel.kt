package com.example.project.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.modals.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel {
        private val teamsResponse = MutableLiveData< TeamsResponse >()
        var teams : LiveData<TeamsResponse> = teamsResponse
        /*fun getTeams(){
                RetrofitHelper.retrofitService.getLeagues().enqueue(
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
        }*/


}