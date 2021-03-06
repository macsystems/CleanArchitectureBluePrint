package de.hohl.example

import android.app.Application
import assertk.assertThat
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import de.hohl.example.rest.BackendApi
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinSetupTest : BaseTest() {
    @Test
    fun `check setup works`() {
        with(BackendApiTarget()) {
            assertThat(this.target).isNotNull()
            assertThat(this.target).isInstanceOf(BackendApi::class.java)
        }
        with(ApplicationTarget()) {
            assertThat(this.target).isNotNull()
        }
    }
}

abstract class KoinTarget : KoinComponent {
    abstract val target: Any
}

class BackendApiTarget : KoinTarget() {
    override val target: BackendApi by inject(Qualifiers.Api)
}

class ApplicationTarget : KoinTarget() {
    override val target: Application by inject()
}