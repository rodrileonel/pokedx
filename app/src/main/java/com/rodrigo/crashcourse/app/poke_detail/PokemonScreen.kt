package com.rodrigo.crashcourse.app.poke_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rodrigo.crashcourse.utils.PokemonTestTags


@Composable
fun PokemonScreen(pokemonState: PokemonState, onBack: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { inner ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(inner)
        ) {
            Column {
                Text("¡Estás en el detalle de el pokemon!")
                Button(onClick = onBack, modifier = Modifier.testTag(PokemonTestTags.BACK)) {
                    Text("Volver")
                }
                when(pokemonState){
                    is PokemonState.Idle -> Text("Waiting...", modifier = Modifier.testTag(PokemonTestTags.IDLE))
                    is PokemonState.Loading -> CircularProgressIndicator(modifier = Modifier.testTag(PokemonTestTags.LOADING))
                    is PokemonState.Error -> Text(pokemonState.message, modifier = Modifier.testTag(
                        PokemonTestTags.ERROR))
                    is PokemonState.Success -> {
                        Column (
                            modifier = Modifier.padding(),

                        ){
                            AsyncImage(
                                model = pokemonState.poke.sprites.front_default,
                                contentDescription = "Pokemon image",
                                modifier = Modifier.size(250.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text(pokemonState.poke.name, modifier = Modifier.testTag(PokemonTestTags.NAME))
                        }
                    }
                }
            }

        }
    }
}