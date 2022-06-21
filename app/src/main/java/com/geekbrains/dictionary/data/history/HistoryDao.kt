package com.geekbrains.dictionary.data.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HistoryEntity)

    @Query("select * from history_table")
    suspend fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history_table WHERE text LIKE :word")
    suspend fun searchByWord(word: String?): HistoryEntity
}