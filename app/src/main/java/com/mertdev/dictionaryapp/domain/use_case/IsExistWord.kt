package com.mertdev.dictionaryapp.domain.use_case

import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.repo.SavedWordsRepo

class IsExistWord(
    val repo: SavedWordsRepo
) {
    suspend operator fun invoke(
        word: String?, phonetic: String?, meanings: List<Meaning>?
    ): Boolean {
        return repo.isExistWord(word, phonetic, meanings)
    }
}