package com.geekbrains.dictionary.data.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "translation") val translation: String,
    @ColumnInfo(name = "note") val note: String,
    @ColumnInfo(name = "picture_url") val pictureUrl: String,
)