package com.moralabs.hacooknew.di

import androidx.room.Room
import com.moralabs.hacooknew.data.home.HomeRepository
import com.moralabs.hacooknew.data.home.local.HomeDatabase
import com.moralabs.hacooknew.data.home.remote.api.HomeApi
import com.moralabs.hacooknew.data.home.repository.HomeRepositoryImpl
import com.moralabs.hacooknew.domain.usecase.HomeUseCase
import com.moralabs.hacooknew.presentation.collection.CollectionViewModel
import com.moralabs.hacooknew.presentation.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeDI {
    companion object{
        val module = module {
            viewModel { HomeViewModel(get()) }
            viewModel { CollectionViewModel(get())}

            single<HomeApi> {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit =Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofit.create(HomeApi::class.java)
            }

            single<HomeRepository> { HomeRepositoryImpl(get(), get()) }

            single {HomeUseCase(get())}

            single {
                Room.databaseBuilder(
                    androidContext(),
                    HomeDatabase::class.java, "HomeDatabase"
                ).allowMainThreadQueries().build()
            }
        }
    }
}