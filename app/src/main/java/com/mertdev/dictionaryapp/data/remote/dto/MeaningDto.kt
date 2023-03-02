package com.mertdev.dictionaryapp.data.remote.dto

data class MeaningDto(
    val antonyms: List<String>?,
    val definitions: List<DefinitionDto>?,
    val partOfSpeech: String?,
    val synonyms: List<String>?
)