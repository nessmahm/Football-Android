package com.example.project.api;

import com.example.project.modals.LeagueResponse
import com.example.project.modals.TeamsResponse
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
interface FootballAPI {
    @GET("/ap")
    fun getLeagues(@Query("action") action: String) : Call<LeagueResponse>
    @GET("/")
    fun getTeam(@Query("action") action: String) : Call<TeamsResponse>
}
