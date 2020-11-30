package de.hohl.example

import android.app.Application
import com.squareup.moshi.Moshi
import de.hohl.example.rest.BackendApi
import okhttp3.OkHttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


internal object Qualifiers {
    val Retrofit = named("retrofit")
    val OkHttpClient = named("OkHttpClient")
    val Moshi = named("moshi")
    val Api = named("backendApi")
}

object KoinSetup {
    fun setup(applicationContext: Application) {
        startKoin {
            printLogger()
            applicationContextModule(applicationContext)
            networkModule()
        }
    }

    private fun networkModule(): Module {
        return module {
            single(Qualifiers.OkHttpClient) { OkHttpClient() }
            single(Qualifiers.Moshi) { Moshi.Builder() }
            single(Qualifiers.Retrofit) {
                Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(get()))
            }
        }
    }

    private fun applicationContextModule(applicationContext: Application): Module {
        return module {
            single { applicationContext }
        }
    }


    private fun buildApi(retrofit: Retrofit) {
        retrofit.create(BackendApi::class.java)
    }
}