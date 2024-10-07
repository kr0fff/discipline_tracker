package com.example.disciplinetrainer.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface QuotesUiState {
    data class Success(val quotes: List<Quote>) : QuotesUiState
    object Error : QuotesUiState
    object Loading : QuotesUiState
}

class MainActivityViewModel : ViewModel() {
    var quotesUiState: QuotesUiState by mutableStateOf(QuotesUiState.Loading)
        private set
    init {
        getQuotesByQuery()
    }
    fun getQuotesByQuery() {
        viewModelScope.launch {
            quotesUiState = try {
                QuotesUiState.Success(AppContainer().getQuotes())
            } catch (e: IOException) {
                QuotesUiState.Error
            } catch (e: HttpException) {
                QuotesUiState.Error
            }
        }
    }
}