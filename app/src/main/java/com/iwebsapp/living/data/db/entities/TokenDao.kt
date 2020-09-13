package com.iwebsapp.living.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TokenDao{

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(data: String) : Long*/

    /*@Query("SELECT * FROM data WHERE uid = $CURRENT_TOKEN_ID")
    fun getToken() : LiveData<Token>*/
}