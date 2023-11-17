package com.yveskalume.herofeeds.android

import android.icu.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

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