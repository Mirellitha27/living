package com.iwebsapp.living.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ServicesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllService(services : List<Services>)

    @Query("SELECT * FROM Services")
    fun getServices() : LiveData<List<Services>>
}