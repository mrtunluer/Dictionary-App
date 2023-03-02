package com.mertdev.dictionaryapp.domain.use_case

import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.repo.SavedWordsRepo

class RemoveFromSaved(
    val repo: SavedWordsRepo
) {
    suspend operator fun invoke(word: String?, phonetic: String?, meanings: List<Meaning>?) {
        repo.deleteFromSaved(word, phonetic, meanings)
    }
}