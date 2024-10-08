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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.disciplinetrainer.data.MainActivityViewModel
import com.example.disciplinetrainer.data.QuotesUiState
import com.example.disciplinetrainer.ui.theme.DisciplineTrainerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DisciplineTrainerTheme {
                val viewModel = MainActivityViewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting(
                            name = viewModel.quotesUiState,
                            modifier = Modifier.padding(innerPadding)
                        )
                        Button(onClick = {  }) {
                            Text("Click")
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: QuotesUiState, modifier: Modifier = Modifier) {
    when (name) {
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
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
        }
    }

}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DisciplineTrainerTheme {
        Greeting("Android")
    }
}*/
