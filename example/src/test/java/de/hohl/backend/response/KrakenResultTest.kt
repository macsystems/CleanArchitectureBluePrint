package de.hohl.backend.response

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.Test

class KrakenResultTest {

    @Test
    fun hasError() {
        val result = KrakenResult<String>(emptyList())
        assertThat(result.hasError()).isFalse()
    }

    @Test
    fun hasResult() {
        val result = KrakenResult<String>(emptyList(), "Some Value")
        assertThat(result.hasError()).isFalse()
        assertThat(result.hasResult()).isTrue()
    }

    @Test
    fun getError() {
        val result = KrakenResult<String>(listOf("error1", "error2"), "Some Value")
        assertThat(result.error.size).isEqualTo(2)
        assertThat(result.error).containsAll("error1", "error2")
    }

    @Test
    fun getResult() {
        val result = KrakenResult<String>(listOf("error1", "error2"), "Some Value")
        assertThat(result.result).isEqualTo("Some Value")
    }


}