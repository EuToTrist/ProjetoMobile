package ui.doenca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clinicease.model.Doenca
import repository.DoencaRepository
class DoencaViewModel (
    private val repository:DoencaRepository
) : ViewModel() {

    private val _doenca: MutableLiveData<ArrayList<Doenca>> =
        MutableLiveData(arrayListOf())
    val Doenca: LiveData<ArrayList<Doenca>> = _doenca

    private val _feedBackMessage: MutableLiveData<String> =
        MutableLiveData(String())
    val feedBackMessage: LiveData<String> = _feedBackMessage


    fun getDoenca(
        nome: String,
        sintomas: String
    ) {
        repository.getDoenca(
            name = nome,
            symptoms = sintomas,
            onSuccess = {
                if (it.isEmpty()) {
                    _feedBackMessage.postValue("Esse repo não contém pull requests!!!")
                } else {
                    _doenca.postValue(it as ArrayList<Doenca>)
                }
            },
            onFailure = {
                _feedBackMessage.postValue("Deu ruim colega!!!")
            })
    }

}
