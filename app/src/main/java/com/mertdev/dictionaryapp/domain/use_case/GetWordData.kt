package com.mertdev.dictionaryapp.domain.use_case

import com.mertdev.dictionaryapp.domain.model.WordData
import com.mertdev.dictionaryapp.domain.repo.WordDataRepo
import com.mertdev.dictionaryapp.utils.DataStatus
import kotlinx.coroutines.flow.Flow

class GetWordData(
    private val repo: WordDataRepo
) {
    operator fun invoke(word: String): Flow<DataStatus<List<WordData>>>? {
        if (word.isNotBlank()) return repo.getWordData(word.trim())
        return null
    }
}