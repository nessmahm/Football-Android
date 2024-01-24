package com.example.project.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.project.R
import com.example.project.modals.Player

class PlayerAdapter(private val players: List<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.playerNameTextView)
        val playerAge: TextView = itemView.findViewById(R.id.playerAgeTextView)
        val playerAgeLabel: TextView = itemView.findViewById(R.id.playerAgeLabel)
        val playerCountry: TextView = itemView.findViewById(R.id.playerCountryTextView)
        val playerCountryLabel: TextView = itemView.findViewById(R.id.playerCountryLabel)

        val playerAssists: TextView = itemView.findViewById(R.id.playerAssistsTextView)
        val playerAssistsLabel: TextView = itemView.findViewById(R.id.playerAssistsLabel)
        val playerGoals: TextView = itemView.findViewById(R.id.playerGoalsTextView)
        val playerGoalsLabel: TextView = itemView.findViewById(R.id.playerGoalsLabel)
        val playerRedCards: TextView = itemView.findViewById(R.id.playerRedCardsTextView)
        val playerRedCardsLabel: TextView = itemView.findViewById(R.id.playerRedCardsLabel)
        val playerYellowCards: TextView = itemView.findViewById(R.id.playerYellowCardsTextView)
        val playerYellowCardsLabel: TextView = itemView.findViewById(R.id.playerYellowCardsLabel)
        val playerImage: ImageView = itemView.findViewById(R.id.playerImage)

        fun bind(player: Player) {
            Glide.with(itemView.context)
                .load(player.player_image) // Assuming country_logo is the image URL
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: caching strategy
                .into(playerImage)
            playerName.text = player.player_name
            playerAge.text = player.player_age
            playerCountry.text = player.player_country
            playerAssists.text = player.player_assists
            playerGoals.text = player.player_goals
            playerRedCards.text = player.player_red_cards
            playerYellowCards.text = player.player_yellow_cards
            if (player.player_age == "" ) {
                playerAge.visibility = View.GONE
                playerAgeLabel.visibility = View.GONE
            }
            if (player.player_country == "") {
                playerCountry.visibility = View.GONE
                playerCountryLabel.visibility = View.GONE
            }
            if (player.player_assists == "") {
                playerAssists.visibility = View.GONE
                playerAssistsLabel.visibility = View.GONE
            }
            if (player.player_goals == "") {
                playerGoals.visibility = View.GONE
                playerGoalsLabel.visibility = View.GONE
            }
            if (player.player_red_cards == "") {
                playerRedCards.visibility = View.GONE
                playerRedCardsLabel.visibility = View.GONE
            }
            if (player.player_yellow_cards == "") {
                playerYellowCards.visibility = View.GONE
                playerYellowCardsLabel.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int {
        return players.size
    }
}
