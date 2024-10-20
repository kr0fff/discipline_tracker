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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.disciplinetrainer.data.MainActivityViewModel
import com.example.disciplinetrainer.data.Quote
import com.example.disciplinetrainer.data.QuotesUiState
import com.example.disciplinetrainer.ui.theme.DisciplineTrainerTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisciplineTrainerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    RenderApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    DisciplineTrainerTheme {
        RenderApp()
    }
}

@Composable
fun RenderApp() {
    val viewModel = MainActivityViewModel()
    val quotes = viewModel.quotesUiState.collectAsState()
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding)
            ) {
                Greeting(
                    response = quotes.value,
                    onRefreshClicked = { viewModel.getQuotesByQuery() }
                )
            }
        }
    }
}

@Composable
fun Greeting(
    response: QuotesUiState,
    modifier: Modifier = Modifier,
    onRefreshClicked: () -> Unit
) {

    Log.d("UI_STATE_LOG", response.toString())
    when {
        response.error != null -> {
            Text(
                text = response.error,
                textAlign = TextAlign.Center,
                modifier = modifier
            )
        }

        response.isLoading -> {
            Text(
                text = "Loading!",
                textAlign = TextAlign.Center,
                modifier = modifier
            )
        }

        response.quotes.isNotEmpty() -> {
            val quote = response.quotes.first()
            Text(
                text = quote.quote,
                textAlign = TextAlign.Center,
                modifier = modifier
            )
        }

        else -> {
            Text(
                text = "No quotes found!",
                textAlign = TextAlign.Center,
                modifier = modifier
            )
        }
    }
    Button(onClick = { onRefreshClicked() }) {
        Text(
            text = "Click",
            modifier = Modifier.wrapContentWidth()
        )
    }

}
