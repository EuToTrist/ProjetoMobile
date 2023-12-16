package repository

import com.example.clinicease.model.Doenca
import network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoencaRepositorylmpl(
    private val service: ApiService
): DoencaRepository {

    override fun getDoenca(
        name: String,
        symptoms: String,
        onSuccess: (List<Doenca>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        val response = service.listDoenca(name,symptoms)
        response.enqueue(
            object : Callback<List<Doenca>> {
                override fun onResponse(
                    call: Call<List<Doenca>>,
                    response: Response<List<Doenca>>
                ) {
                    response.body()?.let{
                        onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<List<Doenca>>, t: Throwable) {
                    onFailure(t)
                }

            }

        )
    }
}



