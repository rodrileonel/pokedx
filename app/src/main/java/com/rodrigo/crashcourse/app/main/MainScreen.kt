package com.rodrigo.crashcourse.app.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigo.crashcourse.R
import com.rodrigo.crashcourse.ui.theme.CrashCourseTheme


@Composable
fun MainScreen(
    name: String = "Rodrigo!!",
    onNavigateToDetail: () -> Unit,
    count:Int,
    text:String,
    texts:List<String>,
    onTextChanged:(String) -> Unit,
    addText:() -> Unit
) {
    Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color.Gray)
                    .size(50.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (name.lowercase().contains("rodrigo"))
                //for(i in 1..10)
                    items(count) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
            }
            Text(
                text = "Hello $name!",
                color = Color.Red,
                fontSize = 25.sp,
                //textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
            Text(
                text = "Hello !",
                color = Color.Blue,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
                //modifier = modifier
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 70.dp, vertical = 40.dp)
            ) {
                Row {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = text,
                        onValueChange = onTextChanged

                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(
                        onClick = addText
                    ) { Text("Agregar") }

                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyColumn {
                    items(texts) { current ->
                        Text(current)
                    }
                }
            }
            Button(
                onClick = onNavigateToDetail
            ) { Text("Pokes") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrashCourseTheme {
        MainScreen(
            "Rodrigo!!", onNavigateToDetail = {},
            count = 2,
            text = "hi",
            texts = listOf("hola", "Rodrigo"),
            onTextChanged = {  },
            addText = {  },
        )
    }
}