package ui.doenca

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clinicease.model.Doenca
import org.koin.android.ext.android.inject
import ui.doenca.adapter.DoencaAdapter

class DoencaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoencaBinding
    private val viewModel: DoencaViewModel by inject()
    private lateinit var pullRequests: ArrayList<Doenca>
    private lateinit var adapter: DoencaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoencaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()
        setUpObservables()
        makeRequest()
    }

    private fun setUpObservables(){
        viewModel.Doenca.observe(this) {
            Doenca.clear()
            Doenca.addAll(it)
            adapter.notifyItemRangeChanged(0, it.size)

            binding.rvPullRequest.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.feedbackMessage.visibility = View.GONE
        }

        viewModel.feedBackMessage.observe(this) {
            binding.rvPullRequest.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.feedbackMessage.visibility = View.VISIBLE
            binding.feedbackMessage.text = it
        }
    }

    private fun makeRequest() {
        val owner = intent.getStringExtra(nome) ?: "gripe"
        val repo = intent.getStringExtra(symptoms) ?: "gripe"
        viewModel.getDoenca(
            nome, symptoms
        )
    }


    private fun setUpList() {
        Doenca = arrayListOf()
        adapter = DoencaAdapter(

        )
        binding.rvDoenca.layoutManager = LinearLayoutManager(this)
        binding.rvDoenca.adapter = adapter
    }

    companion object {
        const val nome = "name"
        const val symptoms = "sintomas"
    }

}
