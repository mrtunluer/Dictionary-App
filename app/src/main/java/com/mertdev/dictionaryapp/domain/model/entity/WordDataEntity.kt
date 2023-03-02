package com.mertdev.dictionaryapp.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mertdev.dictionaryapp.domain.model.Meaning
import java.util.*

@Entity
data class WordDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>?,
    val sourceUrls: List<String>?,
    val date: Date?
)