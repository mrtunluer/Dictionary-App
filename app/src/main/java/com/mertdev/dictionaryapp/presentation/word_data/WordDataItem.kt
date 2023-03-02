package com.mertdev.dictionaryapp.presentation.word_data

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mertdev.dictionaryapp.app.ui.theme.FontSizes
import com.mertdev.dictionaryapp.app.ui.theme.SpaceSize
import com.mertdev.dictionaryapp.domain.model.Definition
import com.mertdev.dictionaryapp.domain.model.Meaning
import com.mertdev.dictionaryapp.domain.model.WordData
import com.mertdev.dictionaryapp.utils.clickable_text.HyperlinkText

@Composable
fun WordDataItem(wordData: WordData, onBookmarkClicked: (WordData) -> Unit) {
    Column {
        DisplayWord(wordData, onBookmarkClicked)
        DisplayPhonetic(wordData.phonetic)
        DisplayMeanings(wordData.meanings)
        DisplaySourceUrls(wordData.sourceUrls)
    }
}

@Composable
private fun DisplayWord(wordData: WordData, onBookmarkClicked: (WordData) -> Unit) {
    var isSaved by remember { mutableStateOf(wordData.isSaved) }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = wordData.word, fontSize = FontSizes.ExtraLarge, fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.width(SpaceSize.Normal))
        Icon(imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    isSaved = !isSaved
                    wordData.isSaved = !wordData.isSaved
                    onBookmarkClicked(wordData)
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