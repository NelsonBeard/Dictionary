package com.geekbrains.dictionary.data.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val TABLE_NAME = "history_table"
const val COLUMN_NAME_TEXT = "text"
const val COLUMN_NAME_TRANSLATION = "translation"
const val COLUMN_NAME_NOTE = "note"
const val COLUMN_NAME_PICTURE_URL = "picture_url"

@Entity(tableName = TABLE_NAME, indices = [Index(COLUMN_NAME_TEXT, unique = true)])
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = COLUMN_NAME_TEXT) val text: String,
    @ColumnInfo(name = COLUMN_NAME_TRANSLATION) val translation: String,
    @ColumnInfo(name = COLUMN_NAME_NOTE) val note: String,
    @ColumnInfo(name = COLUMN_NAME_PICTURE_URL) val pictureUrl: String,
)