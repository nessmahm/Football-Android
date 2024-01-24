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
import com.example.project.modals.Team
import com.example.project.modals.TeamsResponse

class TeamAdapter(private val teams: TeamsResponse?, private val onTeamClick: (String) -> Unit) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamName: TextView = itemView.findViewById(R.id.teamNameTextView)
        val teamCountry: TextView = itemView.findViewById(R.id.teamCountryTextView)
        val teamBadge: ImageView = itemView.findViewById(R.id.teamBadgeImageView)
        fun bind(team: Team) {
            teamName.text = team.team_name
            teamCountry.text = team.team_country

            // Load and display team badge image using Glide
            Glide.with(itemView.context)
                .load(team.team_badge)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: caching strategy
                .into(teamBadge)

            // Bind other data as needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.teams_list_item, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        if (teams == null) {
            return
        }
        val team = teams[position]
        holder.bind(team)
        holder.itemView.setOnClickListener{
            onTeamClick.invoke(team.team_key)
        }
    }

    override fun getItemCount(): Int {
        if (teams == null) {
            return 0
        }
        return teams.size
    }
}
