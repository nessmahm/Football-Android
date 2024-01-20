package com.example.project.api;

import com.example.project.modals.TeamsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
interface FootballAPI {
    @GET("?action=get_leagues")
    fun getLeagues() : Call<TeamsResponse>
}
