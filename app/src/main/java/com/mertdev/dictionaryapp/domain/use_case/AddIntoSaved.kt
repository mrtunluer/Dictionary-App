package com.mertdev.dictionaryapp.domain.use_case

import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import com.mertdev.dictionaryapp.domain.repo.SavedWordsRepo

class AddIntoSaved(
    val repo: SavedWordsRepo
) {
    suspend operator fun invoke(wordDataEntity: WordDataEntity) {
        repo.insertIntoSaved(wordDataEntity)
    }
}