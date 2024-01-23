package com.example.project.modals

data class Home(
    val coach: List<Coach>,
    val missing_players: List<Any>,
    val starting_lineups: List<Any>,
    val substitutes: List<Any>
)