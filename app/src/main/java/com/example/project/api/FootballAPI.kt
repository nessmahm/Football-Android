package com.example.project.api;

import com.example.project.modals.LeagueResponse
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
interface FootballAPI {
    @GET("/")
    fun getLeagues(@Query("action") action: String) : Call<LeagueResponse>

}
