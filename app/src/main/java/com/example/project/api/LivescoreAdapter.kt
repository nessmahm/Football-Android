package com.example.project.api

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.project.R
import com.example.project.modals.LiveScoreResponse
import com.example.project.modals.LiveScoreResponseItem

class LivescoreAdapter (
    private val livescores: LiveScoreResponse,
) : RecyclerView.Adapter<LivescoreAdapter.LiveScoreViewHolder>() {

    inner class LiveScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val leagueName: TextView = itemView.findViewById(R.id.leagueName)
        val match_country: TextView = itemView.findViewById(R.id.countryName)
        val match_time: TextView = itemView.findViewById(R.id.matchTime)
        val team_1_name: TextView = itemView.findViewById(R.id.team_1)
        val team_1_logo: ImageView = itemView.findViewById(R.id.team_1_logo)
        val team_1_score: TextView = itemView.findViewById(R.id.team_1_score)
        val team_2_name: TextView = itemView.findViewById(R.id.team_2)
        val team_2_logo: ImageView = itemView.findViewById(R.id.team_2_logo)
        val team_2_score: TextView = itemView.findViewById(R.id.team_2_score)




        fun bind(liveScore: LiveScoreResponseItem) {
            Glide.with(itemView.context)
                .load(liveScore.team_home_badge) // Assuming country_logo is the image URL
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: caching strategy
                .into(team_1_logo)
            Glide.with(itemView.context)
                .load(liveScore.team_away_badge) // Assuming country_logo is the image URL
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: caching strategy
                .into(team_2_logo)

            match_time.text = liveScore.match_time
            match_country.text = liveScore.country_name
            team_1_name.text = liveScore.match_hometeam_name
            team_1_score.text = liveScore.match_hometeam_score
            team_2_name.text = liveScore.match_awayteam_name
            team_2_score.text = liveScore.match_awayteam_score



           leagueName.text = liveScore.league_name


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveScoreViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.livescore_item, parent, false)
        return LiveScoreViewHolder(view)

    }

    override fun onBindViewHolder(holder: LiveScoreViewHolder, position: Int) {
        if (livescores== null) {
            return
        }
        val liveScore = livescores.get(position)
        holder.bind(liveScore)



    }

    override fun getItemCount(): Int {
        return livescores?.size ?: 0
    }
}