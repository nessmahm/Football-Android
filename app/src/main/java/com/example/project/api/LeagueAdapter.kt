
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.project.R
import com.example.project.modals.LeaguesResponseItem

class LeagueAdapter(
    private val leagues: List<LeaguesResponseItem>,
    private val onLeagueClick: (String, String) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeaguesViewHolder>() {

    inner class LeaguesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leagueLogoImageView: ImageView = itemView.findViewById(R.id.leagueLogoImageView)
        val leagueNameTextView: TextView = itemView.findViewById(R.id.leagueNameTextView)
        val countryNameTextView: TextView = itemView.findViewById(R.id.countryNameTextView)

        fun bind(league: LeaguesResponseItem) {
            Glide.with(itemView.context)
                .load(league.country_logo) // Assuming country_logo is the image URL
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: caching strategy
                .into(leagueLogoImageView)

            leagueNameTextView.text = league.league_name
            countryNameTextView.text = league.country_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return LeaguesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaguesViewHolder, position: Int) {
        val league = leagues?.get(position)
        if (league != null) {
            holder.bind(league)

            holder.itemView.setOnClickListener {
                onLeagueClick.invoke(league.league_id, league.league_name)
            }
        }
    }

    override fun getItemCount(): Int {
        return leagues?.size ?: 0
    }
}
