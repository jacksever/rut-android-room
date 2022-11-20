package com.rut.roomexample.database

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

object TypeConverter {
    @TypeConverter
    fun fromEpochMillis(epochMillies: Long?): Instant? {
        return epochMillies?.let { Instant.fromEpochMilliseconds(epochMillies) }
    }

    @TypeConverter
    fun toEpochMillies(instant: Instant?): Long? {
        return instant?.let { instant.toEpochMilliseconds() }
    }
}