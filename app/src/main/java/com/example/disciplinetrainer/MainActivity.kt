package com.example.disciplinetrainer

import android.os.Bundle
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
                val quotes = viewModel.quotesUiState.collectAsState().value
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting(
                            response = quotes,
                            modifier = Modifier.padding(innerPadding)
                        )
                        Button(onClick = { }) {
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
    when (response) {
        QuotesUiState.Error -> {
            Text(
                text = "Error!",
                modifier = modifier
            )
        }

        QuotesUiState.Loading -> {
            Text(
                text = "Loading!",
                modifier = modifier
            )
        }

        is QuotesUiState.Success -> {
            val quote = response.quotes.proceedSingleQuote()
            if (quote != null) {
                Text(
                    text = "Hello ${quote.quote}!",
                    modifier = modifier
                )
            }

        }
    }

}

fun <T> List<T>.proceedSingleQuote(): Quote? {
    if (this.size == 1) {
        val quote = this.first() as Quote
        return quote
    } else {
        return null
    }
}

