package de.hohl.example;

import android.app.Application
import io.mockk.mockk

open class BaseTest {
    init {
        val application = mockk<Application>()
        KoinSetup.setup(application)

    }

}
