package com.mertdev.dictionaryapp.presentation.word_data

import com.mertdev.dictionaryapp.domain.model.WordData

data class WordDataState(
    val wordDataItems: List<WordData> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)