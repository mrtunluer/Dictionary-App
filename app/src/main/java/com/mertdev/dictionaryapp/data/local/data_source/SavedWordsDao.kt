package com.mertdev.dictionaryapp.data.local.data_source

import androidx.room.*
import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedWordsDao {

    @Query("SELECT * FROM WordDataEntity")
    fun getSavedWords(): Flow<List<WordDataEntity>>

    @Query("SELECT EXISTS (SELECT * FROM WordDataEntity WHERE word=:word AND phonetic=:phonetic AND meanings=:meanings)")
    suspend fun isExistWord(word: String?, phonetic: String?, meanings: List<Meaning>?): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoSaved(wordDataEntity: WordDataEntity)

    @Query("DELETE FROM WordDataEntity WHERE word=:word AND phonetic=:phonetic AND meanings=:meanings")
    suspend fun deleteFromSaved(word: String?, phonetic: String?, meanings: List<Meaning>?)

}