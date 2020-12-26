package de.hohl.backend.response

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

sealed class Status {
    object Online : Status()

    object CancelOnly : Status()

    object PostOnly : Status()

    object LimitOnly : Status()

    object Maintenance : Status()
}

class StatusAdapter {
    @FromJson
    fun read(name: String): Status {
        return when (name) {
            "online" -> Status.Online
            "cancel_only" -> Status.CancelOnly
            "limit_only" -> Status.LimitOnly
            "maintenance" -> Status.Maintenance
            "post_only" -> Status.PostOnly
            else -> throw IllegalArgumentException("Unknown status: $name")
        }
    }

    @ToJson
    fun write(status: Status): Status {
        throw UnsupportedOperationException()
    }
}
