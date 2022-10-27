package com.github.andrewhossam.udacitysession3.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shoe")
data class Shoe(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val name: String,
    val size: Int,
)