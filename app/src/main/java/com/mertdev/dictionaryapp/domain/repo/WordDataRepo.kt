package com.mertdev.dictionaryapp.domain.repo

import com.mertdev.dictionaryapp.domain.model.WordData
import com.mertdev.dictionaryapp.utils.DataStatus
import kotlinx.coroutines.flow.Flow

interface WordDataRepo {

    fun getWordData(word: String): Flow<DataStatus<List<WordData>>>

}