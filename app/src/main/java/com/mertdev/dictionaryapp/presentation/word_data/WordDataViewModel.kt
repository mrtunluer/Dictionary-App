package com.mertdev.dictionaryapp.presentation.word_data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertdev.dictionaryapp.domain.mapper.toWordDataEntity
import com.mertdev.dictionaryapp.domain.model.WordData
import com.mertdev.dictionaryapp.domain.use_case.AddIntoSaved
import com.mertdev.dictionaryapp.domain.use_case.GetWordData
import com.mertdev.dictionaryapp.domain.use_case.IsExistWord
import com.mertdev.dictionaryapp.domain.use_case.RemoveFromSaved
import com.mertdev.dictionaryapp.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class WordDataViewModel @Inject constructor(
    private val getWordData: GetWordData,
    private val addIntoSaved: AddIntoSaved,
    private val removeFromSaved: RemoveFromSaved,
    private val isExistWord: IsExistWord
) : ViewModel() {

    private val _wordQuery = mutableStateOf("")
    val wordQuery: State<String> = _wordQuery

    private val _state = MutableStateFlow(WordDataState())
    val state = _state.asStateFlow()

    private var job: Job? = null

    fun fetchWordData(wordQuery: String) {
        _wordQuery.value = wordQuery
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            getWordData(wordQuery)?.collectLatest { status ->
                _state.update {
                    when (status) {
                        is DataStatus.Success -> state.value.copy(
                            wordDataItems = status.data ?: emptyList(),
                            errorMessage = null,
                            isLoading = false
                        )
                        is DataStatus.Error -> state.value.copy(
                            wordDataItems = emptyList(),
                            errorMessage = status.message.toString(),
                            isLoading = false
                        )
                        is DataStatus.Loading -> state.value.copy(
                            wordDataItems = emptyList(), errorMessage = null, isLoading = true
                        )
                    }
                }
            } ?: _state.update {
                state.value.copy(
                    wordDataItems = emptyList(), errorMessage = null, isLoading = false
                )
            }
        }
    }

    fun saveOrRemoveWordData(wordData: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            with(wordData) {
                if (isExistWord(word, phonetic, meanings)) removeFromSaved(word, phonetic, meanings)
                else addIntoSaved(toWordDataEntity())
            }
        }
    }

}

