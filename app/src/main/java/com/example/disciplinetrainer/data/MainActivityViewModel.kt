package com.example.disciplinetrainer.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface QuotesUiState {
    data class Success(val quotes: List<Quote>) : QuotesUiState
    data object Error : QuotesUiState
    data object Loading : QuotesUiState
}

class MainActivityViewModel : ViewModel() {
    var quotesUiState: MutableStateFlow<QuotesUiState> = MutableStateFlow(QuotesUiState.Loading)
    init {
        getQuotesByQuery()
    }
    fun getQuotesByQuery() {
        viewModelScope.launch {
            quotesUiState = try {
                MutableStateFlow(QuotesUiState.Success(AppContainer().getQuotes()))
            } catch (e: IOException) {
                MutableStateFlow(QuotesUiState.Error)
            } catch (e: HttpException) {
                MutableStateFlow(QuotesUiState.Error)
            }
        }
    }
}