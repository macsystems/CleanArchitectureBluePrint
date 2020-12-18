package de.hohl.example

import android.app.Application
import com.squareup.moshi.Moshi
import de.hohl.cleanarchitecturebase.job.CoroutineContextProvider
import de.hohl.cleanarchitecturebase.job.DefaultContextProvider
import de.hohl.example.rest.BackendApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
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
    fun setup(applicationContext: Application, testModuleOverrides: List<Module> = emptyList()) {
        startKoin {
            androidContext(applicationContext)
            if (BuildConfig.DEBUG) {
                logger(TimberLogger(DEBUG))
            } else {
                logger(EmptyLogger())
            }
            modules(
                coroutinesModule(),
                networkModule(),
                viewModelModule()
            )
            if (testModuleOverrides.isNotEmpty()) {
                loadKoinModules(testModuleOverrides)
            }
        }
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { MainViewModel(backendApi = get(Qualifiers.Api)) }
        }
    }

    private fun coroutinesModule(): Module {
        return module {
            single<CoroutineContextProvider> { DefaultContextProvider }
        }
    }
}


private fun networkModule(): Module {
    return module {
        single(Qualifiers.OkHttpClient) {
            OkHttpClient.Builder()
                .addInterceptor(httpLogger()).build()
        }
        single(Qualifiers.Moshi) { Moshi.Builder().build() }
        single(Qualifiers.Api) {
            setupRetrofit(client = get(Qualifiers.OkHttpClient), moshi = get(Qualifiers.Moshi))
        }
    }
}

private fun httpLogger(): Interceptor = when (BuildConfig.DEBUG) {
    true -> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    false -> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}.exhaustive

private fun setupRetrofit(client: OkHttpClient, moshi: Moshi): BackendApi {
    return Retrofit.Builder().baseUrl(baseUrl).client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        .create(BackendApi::class.java)
}

/**
 * Koin Logger using Timber
 */
internal class TimberLogger(level: Level) : Logger(level) {
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


