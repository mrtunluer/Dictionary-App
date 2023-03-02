package com.mertdev.dictionaryapp.data.repo

import com.mertdev.dictionaryapp.data.local.data_source.SavedWordsDao
import com.mertdev.dictionaryapp.data.remote.DictionaryApiService
import com.mertdev.dictionaryapp.domain.mapper.toWordData
import com.mertdev.dictionaryapp.domain.model.WordData
import com.mertdev.dictionaryapp.domain.repo.WordDataRepo
import com.mertdev.dictionaryapp.utils.DataStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WordDataRepoImpl(
    private val dictionaryApi: DictionaryApiService, private val dao: SavedWordsDao
) : WordDataRepo {
    override fun getWordData(word: String): Flow<DataStatus<List<WordData>>> = flow {
        emit(DataStatus.Loading())
        try {
            val wordDatas = dictionaryApi.getWordData(word).map { it.toWordData() }

            wordDatas.forEach { wordDataItem ->
                wordDataItem.isSaved =
                    dao.isExistWord(wordDataItem.word, wordDataItem.phonetic, wordDataItem.meanings)
            }

            emit(DataStatus.Success(wordDatas))
        } catch (e: Exception) {
            emit(DataStatus.Error(e.message))
        }
    }
}