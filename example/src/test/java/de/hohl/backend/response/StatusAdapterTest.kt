package de.hohl.backend.response

import assertk.assertThat
import assertk.assertions.isSameAs
import org.junit.Test

class StatusAdapterTest {

    @Test
    fun `check that all status can be read by adapter`() {
        with(StatusAdapter()) {

            kotlin.run {
                val status = read("online")
                check(status, Status.Online)
            }
            kotlin.run {
                val status = read("online")
                check(status, Status.Online)
            }
            kotlin.run {
                val status = read("post_only")
                check(status, Status.PostOnly)
            }
            kotlin.run {
                val status = read("cancel_only")
                check(status, Status.CancelOnly)
            }
            kotlin.run {
                val status = read("limit_only")
                check(status, Status.LimitOnly)
            }
            kotlin.run {
                val status = read("maintenance")
                check(status, Status.Maintenance)
            }
        }
    }

    private fun check(read: Status, expected: Status) {
        assertThat(read::class.java).isSameAs(expected::class.java)
    }

}