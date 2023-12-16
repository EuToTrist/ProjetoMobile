package di

import network.ApiService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repository.DoencaRepository
import repository.DoencaRepositorylmpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.doenca.DoencaViewModel

val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("doencas")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(ApiService::class.java)
    }

}

val repositoryModules = module {


    factory<DoencaRepository> {
        DoencaRepositorylmpl(get())
    }

}
val viewModelModules = module {
    viewModel {
        DoencaViewModel(get())
    }
}