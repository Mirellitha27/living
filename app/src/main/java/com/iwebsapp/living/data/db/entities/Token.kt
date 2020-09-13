package com.iwebsapp.living.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_TOKEN_ID = 0

@Entity
data class Token(
    var data: String? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_TOKEN_ID
}