package com.mertdev.dictionaryapp.domain.repo

import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import kotlinx.coroutines.flow.Flow

interface SavedWordsRepo {
    fun getSavedWords(): Flow<List<WordDataEntity>>

    suspend fun insertIntoSaved(wordDataEntity: WordDataEntity)

    suspend fun deleteFromSaved(word: String?, phonetic: String?, meanings: List<Meaning>?)

    suspend fun isExistWord(word: String?, phonetic: String?, meanings: List<Meaning>?): Boolean
}