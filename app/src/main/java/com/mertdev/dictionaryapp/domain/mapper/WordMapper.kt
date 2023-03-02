package com.mertdev.dictionaryapp.domain.mapper

import com.mertdev.dictionaryapp.data.remote.dto.DefinitionDto
import com.mertdev.dictionaryapp.data.remote.dto.MeaningDto
import com.mertdev.dictionaryapp.data.remote.dto.WordDataDto
import com.mertdev.dictionaryapp.domain.model.Definition
import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.model.WordData
import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import java.util.*

fun DefinitionDto.toDefinition(): Definition {
    return Definition(
        antonyms = antonyms, definition = definition, example = example, synonyms = synonyms
    )
}

fun MeaningDto.toMeaning(): Meaning {
    return Meaning(
        definitions = definitions?.map {
            it.toDefinition()
        }, partOfSpeech = partOfSpeech
    )
}

fun WordDataDto.toWordData(): WordData {
    return WordData(word = word,
        phonetic = phonetics?.mapNotNull {
            it.text
        }?.toSet()?.joinToString(", ")?.replace("/", ""),
        sourceUrls = sourceUrls,
        meanings = meanings?.map { it.toMeaning() })
}

fun WordData.toWordDataEntity(): WordDataEntity {
    return WordDataEntity(
        word = word,
        phonetic = phonetic,
        meanings = meanings,
        sourceUrls = sourceUrls,
        date = Date()
    )
}
