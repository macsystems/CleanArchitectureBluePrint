package de.hohl.example

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import de.hohl.example.rest.BackendApi
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Level
import org.koin.core.logger.Level.*
import org.koin.core.logger.Logger
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

internal const val baseUrl = "https://api.coingecko.com/api/v3/"

internal object Qualifiers {
    val BaseUrl = named("backend url")
    val Retrofit = named("retrofit")
    val OkHttpClient = named("OkHttpClient")
    val Moshi = named("moshi")
    val Api = named("backendApi")
}

object KoinSetup {
    fun setup(applicationContext: Application) {
        startKoin {
            if (BuildConfig.DEBUG) {
                logger(TimberLogger(DEBUG))
            } else {
                logger(EmptyLogger())
            }
            modules(
                applicationContextModule(applicationContext),
                networkModule(),
                viewModelModule(applicationContext)
            )
        }
    }

    private fun viewModelModule(applicationContext: Context): Module {
        return module {
            viewModel { MainViewModel(backendApi = get(Qualifiers.Api)) }
        }
    }

    private fun networkModule(): Module {
        return module {

            single(Qualifiers.OkHttpClient) { OkHttpClient() }
            single(Qualifiers.Moshi) { Moshi.Builder().build() }
            single(Qualifiers.Api) {
                setupRetrofit(client = get(Qualifiers.OkHttpClient), moshi = get(Qualifiers.Moshi))
            }

        }
    }

    private fun setupRetrofit(client: OkHttpClient, moshi: Moshi): BackendApi {
        return Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(BackendApi::class.java)
    }

    private fun applicationContextModule(applicationContext: Application): Module {
        return module {
            single { applicationContext }
        }
    }
}

/**
 * Koin Logger using Timber
 */
internal class TimberLogger(level: Level) : Logger(level) {

    private val timber = Timber.plant(Timber.DebugTree())

    override fun log(level: Level, msg: String) {
        when (level) {
            INFO -> Timber.i(msg)
            DEBUG -> Timber.d(msg)
            ERROR -> Timber.e(msg)
            NONE -> {
            }
        }.exhaustive
    }
}