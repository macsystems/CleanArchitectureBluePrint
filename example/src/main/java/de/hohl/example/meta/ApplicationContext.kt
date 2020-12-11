package de.hohl.example.meta

import android.content.Context
import org.koin.core.qualifier.TypeQualifier


object Qualifiers {
    val ApplicationContext = TypeQualifier(Context::class)
}
