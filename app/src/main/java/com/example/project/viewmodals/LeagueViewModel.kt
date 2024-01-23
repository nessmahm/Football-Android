package com.example.project.viewmodals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.modals.LeagueResponse
import com.example.project.modals.LeaguesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueViewModel {
    private val leaguesResponse = MutableLiveData<LeaguesResponse>()
    var leagues : LiveData<LeaguesResponse> = leaguesResponse

    private val leagueResponse = MutableLiveData<LeagueResponse>()
    var league : LiveData<LeagueResponse> = leagueResponse
    /*
    fun getTeams(leagueId: String){
        RetrofitHelper.retrofitService.getTeamsByLeague("get_teams", leagueId).enqueue(
            object : Callback<LeagueResponse> {
                override fun onResponse(
                    call: Call<LeagueResponse>,
                    response: Response<LeagueResponse>
                ) {
                    if(response.isSuccessful){
                        leagueResponse.value = response.body()
                        league = leagueResponse
                    }
                }

                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    Log.e("Retrofit", "API call failed: ${t.message}")

                }


            }
        )
    }
    */

}