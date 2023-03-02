package com.mertdev.dictionaryapp.data.remote.dto

data class DefinitionDto(
    val antonyms: List<String>?,
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
)