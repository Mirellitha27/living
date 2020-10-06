package com.iwebsapp.living.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Services (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val image: String
    )