package com.mertdev.dictionaryapp.presentation.saved_words

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mertdev.dictionaryapp.app.ui.theme.FontSizes
import com.mertdev.dictionaryapp.app.ui.theme.SpaceSize
import com.mertdev.dictionaryapp.domain.model.Definition
import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import com.mertdev.dictionaryapp.utils.clickable_text.HyperlinkText

@Composable
fun SavedWordsItem(wordDataEntity: WordDataEntity, onBookmarkClicked: (WordDataEntity) -> Unit) {
    Column {
        DisplayWord(wordDataEntity, onBookmarkClicked)
        DisplayPhonetic(wordDataEntity.phonetic)
        DisplayMeanings(wordDataEntity.meanings)
        DisplaySourceUrls(wordDataEntity.sourceUrls)
    }
}

@Composable
private fun DisplayWord(
    wordDataEntity: WordDataEntity, onBookmarkClicked: (WordDataEntity) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = wordDataEntity.word,
            fontSize = FontSizes.ExtraLarge,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.width(SpaceSize.Normal))
        Icon(imageVector = Icons.Default.Bookmark,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    onBookmarkClicked(wordDataEntity)
                })
    }
}

@Composable
private fun DisplayPhonetic(phonetic: String?) {
    Text(text = phonetic ?: "")
    Spacer(modifier = Modifier.height(SpaceSize.Small))
}

@Composable
private fun DisplayMeanings(meanings: List<Meaning>?) {
    meanings?.forEach { meaning ->
        DisplayPartOfSpeech(meaning.partOfSpeech)
        DisplayDefinitions(meaning.definitions)
    }
}

@Composable
private fun DisplayPartOfSpeech(partOfSpeech: String?) {
    Text(text = partOfSpeech ?: "", fontSize = FontSizes.Large, fontWeight = FontWeight.Bold)
}

@Composable
private fun DisplayDefinitions(definitions: List<Definition>?) {
    definitions?.forEachIndexed { index, definition ->
        DisplayDefinition(index, definition)
    }
}

@Composable
private fun DisplayDefinition(index: Int, definition: Definition?) {
    Text(text = "${index + 1}. ${definition?.definition ?: ""}")
    Spacer(modifier = Modifier.height(SpaceSize.Small))
    Text(text = "Example: ${definition?.example ?: ""}")
    Spacer(modifier = Modifier.height(SpaceSize.Normal))
}

@Composable
private fun DisplaySourceUrls(sourceUrls: List<String>?) {
    Text(text = "Source:", fontSize = FontSizes.Small)
    sourceUrls?.forEach { sourceUrl ->
        HyperlinkText(
            fullText = sourceUrl, hyperLinks = mapOf(
                sourceUrl to sourceUrl
            ), fontSize = FontSizes.Small
        )
    }
    Spacer(modifier = Modifier.height(SpaceSize.Large))
}