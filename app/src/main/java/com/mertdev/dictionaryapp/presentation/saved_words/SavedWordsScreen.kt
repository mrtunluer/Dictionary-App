package com.mertdev.dictionaryapp.presentation.saved_words

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertdev.dictionaryapp.app.ui.theme.SpaceSize
import com.mertdev.dictionaryapp.domain.model.entity.WordDataEntity
import com.mertdev.dictionaryapp.domain.utils.WordOrder
import com.mertdev.dictionaryapp.presentation.saved_words.components.OrderRadioButtons

@Composable
fun SavedWordsScreen(viewModel: SavedWordsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    val wordQuery = remember {
        mutableStateOf("")
    }
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSize.ExtraLarge)
        ) {
            SearchBar(wordQuery, viewModel, state.wordOrder)
            Spacer(modifier = Modifier.height(SpaceSize.Normal))
            OrderRadioButtons(wordOrder = state.wordOrder, onOrderChange = { wordOrder ->
                viewModel.getWords(wordOrder, wordQuery.value)
            })
            SavedWordList(viewModel, state.filteredWordDataEntityItems)
        }
        when {
            state.isEmpty -> Text(
                text = "There is nothing here :(", modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SearchBar(
    wordQuery: MutableState<String>, viewModel: SavedWordsViewModel, wordOrder: WordOrder
) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = wordQuery.value,
        onValueChange = { text ->
            viewModel.getWords(wordOrder, text)
            wordQuery.value = text
        },
        maxLines = 1,
        placeholder = { Text("Search in saved words") })
}

@Composable
fun SavedWordList(viewModel: SavedWordsViewModel, wordDataEntityItems: List<WordDataEntity>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(wordDataEntityItems.size) { i ->
            SavedWordsItem(wordDataEntityItems[i], onBookmarkClicked = { clickedWord ->
                clickedWord.let { wordDataEntity ->
                    viewModel.removeWord(wordDataEntity)
                }
            })
        }
    }
}

