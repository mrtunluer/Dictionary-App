package com.mertdev.dictionaryapp.domain.model

data class Definition(
    val antonyms: List<String>?,
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
)