package ui.doenca.vh

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import databinding.GithubPullListItemBinding
import com.example.clinicease.model.Doenca

class DoencaVH (private val binding:DoencaItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Doenca: Doenca) {
        binding.doencaTitle.name = Doenca.name
        binding.btnGoToWeb.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Doenca.htmlUrl))
            binding.root.context.startActivity(browserIntent)
        }
        binding.repoNome.text = Doenca.base.repo.owner.login
    }
}