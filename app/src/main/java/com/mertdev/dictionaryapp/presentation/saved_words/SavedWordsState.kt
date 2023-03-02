package com.mertdev.dictionaryapp.presentation.saved_words

import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import com.mertdev.dictionaryapp.domain.utils.WordOrder

data class SavedWordsState(
    var wordDataEntityItems: List<WordDataEntity> = emptyList(),
    val filteredWordDataEntityItems: List<WordDataEntity> = emptyList(),
    val isEmpty: Boolean = false,
    val wordOrder: WordOrder = WordOrder.Descending
)