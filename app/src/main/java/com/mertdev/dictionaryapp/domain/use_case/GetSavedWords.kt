package com.mertdev.dictionaryapp.domain.use_case

import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import com.mertdev.dictionaryapp.domain.repo.SavedWordsRepo
import com.mertdev.dictionaryapp.domain.utils.WordOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSavedWords(
    val repo: SavedWordsRepo
) {
    operator fun invoke(
        wordOrder: WordOrder = WordOrder.Descending
    ): Flow<List<WordDataEntity>> {
        return repo.getSavedWords().map { wordEntities ->
            when (wordOrder) {
                is WordOrder.Ascending -> wordEntities.sortedBy { it.date }
                is WordOrder.Descending -> wordEntities.sortedByDescending { it.date }
            }
        }
    }
}