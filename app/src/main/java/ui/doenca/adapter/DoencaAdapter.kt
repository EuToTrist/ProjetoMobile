package ui.doenca.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicease.model.Doenca
import com.example.networkconection.databinding.GithubPullListItemBinding
import ui.doenca.vh.DoencaVH
class DoencaAdapter (
    private val doenca: ArrayList<Doenca>
) : RecyclerView.Adapter<DoencaVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestVH {
        val view =
            DoencaListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullRequestVH(view)
    }

    override fun getItemCount(): Int = pullRequests.size

    override fun onBindViewHolder(holder: PullRequestVH, position: Int) {
        holder.bind(pullRequest = pullRequests[position])
    }
}