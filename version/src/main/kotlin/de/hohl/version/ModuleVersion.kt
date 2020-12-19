package de.hohl.version

data class ModuleVersion(
    val isDebug: Boolean,
    val applicationId: String,
    val buildType: String,
    val versionCode: Long,
    val versionName: String,
    val timestamp: String,
    val sdkInt: Int
)
