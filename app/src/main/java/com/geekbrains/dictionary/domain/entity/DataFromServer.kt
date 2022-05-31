package com.geekbrains.dictionary.domain.entity

import com.google.gson.annotations.SerializedName

data class DataFromServer(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)

class Meanings(
    @field:SerializedName("translation") val translation: Translation?
)

class Translation(
    @field:SerializedName("text") val translation: String?
)
