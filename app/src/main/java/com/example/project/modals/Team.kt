package com.example.project.modals

data class Team(
    val coaches: List<Coache>,
    val players: List<Player>,
    val team_badge: String,
    val team_country: String,
    val team_founded: String,
    val team_key: String,
    val team_name: String,
    val venue: Venue
)