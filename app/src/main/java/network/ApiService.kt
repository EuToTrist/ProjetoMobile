package network

import com.example.clinicease.model.Doenca
import com.example.clinicease.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface ApiService {
    @GET("https://clinicease-d5bq34i2.b4a.run/doencas")
    fun listRepos(@Path("nome") nome : String?): Call<List<Repository>>

    @GET("/repos/{owner}/{repo}/pulls")
    fun listDoenca(@Path("nome") name: String, @Path("sintomas") symptoms: String): Call<List<Doenca>>
}