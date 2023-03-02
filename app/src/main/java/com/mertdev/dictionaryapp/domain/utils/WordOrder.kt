package com.mertdev.dictionaryapp.domain.utils

sealed class WordOrder {
    object Ascending : WordOrder()
    object Descending : WordOrder()
}