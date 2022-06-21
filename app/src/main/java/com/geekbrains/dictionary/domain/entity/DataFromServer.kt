package com.geekbrains.dictionary.domain.entity

import com.google.gson.annotations.SerializedName

private const val VALUE_TEXT = "text"
private const val VALUE_MEANINGS = "meanings"
private const val VALUE_TRANSLATION = "translation"
private const val VALUE_NOTE = "note"
private const val VALUE_IMAGE_URL = "imageUrl"

data class DataFromServer(
    @SerializedName(VALUE_TEXT) val text: String?,
    @SerializedName(VALUE_MEANINGS) val meanings: List<Meanings>?
)

class Meanings(
    @SerializedName(VALUE_TRANSLATION) val translation: Translation?,
    @SerializedName(VALUE_IMAGE_URL) val imageUrl: String?
)

class Translation(
    @SerializedName(VALUE_TEXT) val text: String?,
    @SerializedName(VALUE_NOTE) val note: String?
)