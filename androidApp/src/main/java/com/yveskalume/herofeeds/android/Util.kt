package com.yveskalume.herofeeds.android

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun String.getFormattedDate(): String {

    val instant = Instant.parse(this)

    val zonedDateTime = ZonedDateTime.ofInstant(instant, java.time.ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")

    return try {
        formatter.format(zonedDateTime)
    } catch (e: Exception) {
        this
    }
}