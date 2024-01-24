package com.example.project.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.modals.Coache

class CoachAdapter(private val coaches: List<Coache>) : RecyclerView.Adapter<CoachAdapter.CoachViewHolder>() {

    inner class CoachViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coachName: TextView = itemView.findViewById(R.id.coachNameTextView)
        val coachAge: TextView = itemView.findViewById(R.id.coachAgeTextView)
        val coachCountry: TextView = itemView.findViewById(R.id.coachCountryTextView)
        val coachNameLabel : TextView = itemView.findViewById(R.id.coachNameLabel)
        val coachAgeLabel : TextView = itemView.findViewById(R.id.coachAgeLabel)
        val coachCountryLabel : TextView = itemView.findViewById(R.id.coachCountryLabel)

        fun bind(coach: Coache) {
            coachName.text = coach.coach_name
            coachAge.text = coach.coach_age
            coachCountry.text = coach.coach_country
            if (coach.coach_name == "") {
                coachName.visibility = View.GONE
                coachNameLabel.visibility = View.GONE
            }
            if (coach.coach_age == "" ) {
                coachAge.visibility = View.GONE
                coachAgeLabel.visibility = View.GONE
            }
            if (coach.coach_country == "") {
                coachCountry.visibility = View.GONE
                coachCountryLabel.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoachViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_coach, parent, false)
        return CoachViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoachViewHolder, position: Int) {
        val coach = coaches[position]
        holder.bind(coach)
    }

    override fun getItemCount(): Int {
        return coaches.size
    }
}
