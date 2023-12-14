package com.example.viacep.presenter.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viacep.R
import com.example.viacep.databinding.FragmentSearchAddressBinding
import com.example.viacep.domain.model.Address
import com.example.viacep.util.StateView
import com.example.viacep.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchAddressBinding? = null
    private val binding get() = _binding!!

    private var address: Address? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners(){
        binding.editCep.addTextChangedListener {
            val text = it?.toString() ?: ""
            if (text.isNotEmpty()){
                if(text.length == 8){
                    hideKeyboard()
                    getAddress(cep = text)

                }

            }
        }
    }

    private fun getAddress(cep: String) {
        viewModel.getAddress(cep).observe(viewLifecycleOwner){stateView ->
            when(stateView){
                is StateView.Loading -> {
                    binding.btnSave.isEnabled = false
                    binding.itemAddress.viewFlipper.displayedChild = 2
                }
                is StateView.Success -> {
                    if (stateView.data?.cep != null){
                        address = stateView.data

                        binding.btnSave.isEnabled = true
                        binding.itemAddress.viewFlipper.displayedChild = 1
                        binding.itemAddress.textAddress.text = stateView.data.getFullAddress()
                    } else{
                        stateError ()
                    }
                }
                is StateView.Error -> {
                    stateError ()

                }
            }

        }

    }

    private fun stateError (){
        address = null
        binding.btnSave.isEnabled = false
        binding.itemAddress.viewFlipper.displayedChild = 0
        binding.itemAddress.textEmptyAddress.text =
            getString(R.string.label_address_empty_search_address_fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}