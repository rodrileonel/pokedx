package com.rodrigo.crashcourse.app.poke

import android.R.attr.enabled
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun PokesScreen(
    pokesState: PokeState,
    onBack: () -> Unit,
    onNavigateToDetail:(String) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(inner)
        ) {
            Column {
                Text("¡Estás en la lista de pokemones")
                Button(onClick = onBack) {
                    Text("Volver")
                }
                when(pokesState){
                    is PokeState.Idle -> CircularProgressIndicator()
                    is PokeState.Loading -> CircularProgressIndicator()
                    is PokeState.Error -> Text("Error")
                    is PokeState.Success -> LazyColumn {
                        items(pokesState.pokes) { current->
                            Text(
                                modifier = Modifier.clickable(enabled = true){
                                    onNavigateToDetail(current.name)
                                },
                                text = current.name
                            )
                        }
                    }
                }
            }

        }
    }
}