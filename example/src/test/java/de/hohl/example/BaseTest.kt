package de.hohl.example;

import android.app.Application
import io.mockk.mockk
import org.koin.test.AutoCloseKoinTest

open class BaseTest : AutoCloseKoinTest() {
    init {
        val application = mockk<Application>()
        KoinSetup.setup(application)

    }

}
