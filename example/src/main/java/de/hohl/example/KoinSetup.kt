package de.hohl.example

import android.app.Application
import com.squareup.moshi.Moshi
import de.hohl.example.rest.BackendApi
import okhttp3.OkHttpClient
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


internal object Qualifiers {
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
            single(Qualifiers.Api) { buildApi(get()) }

        }
    }

    private fun applicationContextModule(applicationContext: Application): Module {
        return module {
            single { applicationContext }
        }
    }


    private fun buildApi(retrofit: Retrofit): BackendApi {
        return retrofit.create(BackendApi::class.java)
    }
}

/**
 * Koin Logger using Timber
 */
class TimberLogger(level: Level) : Logger(level) {

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