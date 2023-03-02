package com.mertdev.dictionaryapp.presentation.word_data

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mertdev.dictionaryapp.app.ui.theme.SpaceSize
import com.mertdev.dictionaryapp.domain.model.WordData

@Composable
fun WordDataScreen(viewModel: WordDataViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSize.ExtraLarge)
        ) {
            SearchBar(viewModel)
            Spacer(modifier = Modifier.height(SpaceSize.Large))
            WordDataList(viewModel, state.wordDataItems)
        }
        when {
            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            state.errorMessage != null -> Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp),
                text = state.errorMessage.toString(),
                textAlign = TextAlign.Center,
                color = Color.Red
            )
        }
    }
}

@Composable
fun SearchBar(viewModel: WordDataViewModel) = with(viewModel) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = wordQuery.value,
        onValueChange = { wordQuery ->
            fetchWordData(wordQuery)
        },
        maxLines = 1,
        placeholder = { Text("Type a word...") })
}

@Composable
fun WordDataList(viewModel: WordDataViewModel, wordDataItems: List<WordData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(wordDataItems.size) { i ->
            WordDataItem(wordDataItems[i], onBookmarkClicked = { clickedWord ->
                clickedWord.let { wordData ->
                    viewModel.saveOrRemoveWordData(wordData)
                }
            })
        }
    }
}