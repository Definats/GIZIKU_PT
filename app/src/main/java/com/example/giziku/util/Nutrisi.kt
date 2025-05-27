package com.example.giziku.util

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable


@Serializable
@Entity(
    tableName = "nutrisi",
    foreignKeys = [
        ForeignKey(
            entity = Nutrisi::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class Nutrisi(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val kalori: Int,
    val protein: Int,
    val karbohidrat: Int,
    val lemak: Int,
    val serat: Int,
    val gula: Int,
    val natrium: Int,
    val kolesterol: Int,
    val vitamin: Int,
    var userId: Long = 0L,
    var createdAt: Instant = Clock.System.now()
)

object InstantConverter {

    @TypeConverter
    fun fromInstant(instant: Instant?): Long? {
        return instant?.toEpochMilliseconds()
    }

    @TypeConverter
    fun toInstant(epochMillis: Long?): Instant? {
        return epochMillis?.let { Instant.fromEpochMilliseconds(it) }
    }
}