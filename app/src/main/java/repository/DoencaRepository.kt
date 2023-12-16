package repository


import com.example.clinicease.model.Doenca


interface DoencaRepository {
    fun getDoenca(
        name: String,
        symptoms: String,
        onSuccess: (List<Doenca>) -> Unit,
        onFailure: (t: Throwable) -> Unit

    )
}