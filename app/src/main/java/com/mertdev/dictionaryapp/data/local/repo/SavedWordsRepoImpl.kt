package com.mertdev.dictionaryapp.data.local.repo

import com.mertdev.dictionaryapp.data.local.data_source.SavedWordsDao
import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import com.mertdev.dictionaryapp.domain.repo.SavedWordsRepo
import kotlinx.coroutines.flow.Flow

class SavedWordsRepoImpl(private val dao: SavedWordsDao) : SavedWordsRepo {

    override fun getSavedWords(): Flow<List<WordDataEntity>> {
        return dao.getSavedWords()
    }

    override suspend fun insertIntoSaved(wordDataEntity: WordDataEntity) {
        return dao.insertIntoSaved(wordDataEntity)
    }

    override suspend fun deleteFromSaved(
        word: String?, phonetic: String?, meanings: List<Meaning>?
    ) {
        return dao.deleteFromSaved(word, phonetic, meanings)
    }

    override suspend fun isExistWord(
        word: String?, phonetic: String?, meanings: List<Meaning>?
    ): Boolean {
        return dao.isExistWord(word, phonetic, meanings)
    }

}