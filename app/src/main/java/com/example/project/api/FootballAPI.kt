package com.example.project.api;

import com.example.project.modals.CountryResponse
import com.example.project.modals.LeaguesResponse
import com.example.project.modals.LiveScoreResponse
import com.example.project.modals.TeamsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballAPI {
    @GET("/")
    fun getLeagues(@Query("action") action: String = "get_leagues" ) : Call<LeaguesResponse>

    @GET("/")
    fun getTeamsByLeague( @Query("league_id") leagueId: String,@Query("action") action: String = "get_teams"): Call<TeamsResponse>
    @GET("/")
    fun getTeam(@Query("team_id") teamId: String,@Query("action") action: String="get_teams") : Call<TeamsResponse>
    @GET("/")
    fun getCountries(@Query("action") action: String="get_countries") : Call<CountryResponse>
    @GET("/")
    fun getLiveScore(@Query("match_live") matchId: String,@Query("action") action: String = "get_events") : Call<LiveScoreResponse>
}
