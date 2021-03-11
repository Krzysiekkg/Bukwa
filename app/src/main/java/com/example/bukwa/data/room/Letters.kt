package com.example.bukwa.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Letters(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val polish: String?,
    val russian: String?,
    val forUrl: String?
)
