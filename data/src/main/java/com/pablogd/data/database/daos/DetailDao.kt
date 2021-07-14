package com.pablogd.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pablogd.data.database.entities.DetailEntity

@Dao
interface DetailDao {

    @Query("SELECT * FROM detail WHERE id == 1")
    suspend fun getDetail(): DetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detail: DetailEntity)

}