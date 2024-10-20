package com.example.disciplinetrainer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.disciplinetrainer.data.MainActivityViewModel
import com.example.disciplinetrainer.data.Quote
import com.example.disciplinetrainer.data.QuotesUiState
import com.example.disciplinetrainer.ui.theme.DisciplineTrainerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisciplineTrainerTheme {
                val viewModel = MainActivityViewModel()
                val quotes = viewModel.quotesUiState.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Greeting(
                            response = quotes.value,
                            modifier = Modifier.padding(innerPadding)
                        )
                        Button(onClick = { viewModel.getQuotesByQuery() }) {
                            Text("Click")
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(response: QuotesUiState, modifier: Modifier = Modifier) {

    Log.d("UI_STATE_LOG", response.toString())
    when {
        response.error != null -> {
            Text(
                text = response.error,
                modifier = modifier
            )
        }

        response.isLoading -> {
            Text(
                text = "Loading!",
                modifier = modifier
            )
        }

        response.quotes.isNotEmpty() -> {
            val quote = response.quotes.first()
            Text(
                text = "Hello ${quote.quote}!",
                modifier = modifier
            )
        }

        else -> {
            Text(
                text = "No quotes found!",
                modifier = modifier
            )
        }
    }

}
