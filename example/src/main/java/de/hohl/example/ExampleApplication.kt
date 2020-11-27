package de.hohl.example

import android.app.Application

class ExampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinSetup.setup(this)
    }
}