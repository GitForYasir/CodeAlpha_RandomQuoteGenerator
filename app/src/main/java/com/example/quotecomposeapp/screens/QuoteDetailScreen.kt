package com.example.quotecomposeapp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.quotecomposeapp.DataManager
import com.example.quotecomposeapp.R
import com.example.quotecomposeapp.models.Quote

@Composable
fun QuoteDetail(quote: Quote) {
    var content by remember {
        mutableStateOf(quote.text)
    }
    var author by remember {
        mutableStateOf(quote.author)
    }

    BackHandler {
        DataManager.switchPages(null)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(
                Brush.sweepGradient(
                    colors = listOf(
                        Color(0xFFffffff),
                        Color(0xFFE3E3E3)
                    )
                )
            )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier.padding(32.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier

                    .padding(16.dp, 24.dp)

            ) {
                Image(
                    imageVector = Icons.Filled.FormatQuote,
                    contentDescription = "Quote",
                    modifier = Modifier
                        .size(80.dp)
                        .rotate(180F)
                )

//                Text(
//                    text = quote.text,
//                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
//                    style = MaterialTheme.typography.headlineSmall
//                )
//                Spacer(Modifier.height(16.dp))
//                Text(
//                    text = quote.author,
//                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
//                    style = MaterialTheme.typography.titleMedium
//                )

                TextField(
                    value = content,
                    onValueChange = { content = it }
                )
                Spacer(Modifier.height(16.dp))
                TextField(
                    value = author,
                    onValueChange = { author = it }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = {
                        DataManager.updateQuote(quote, content, author)
                        DataManager.switchPages(null)
                    },
                        colors = ButtonDefaults.buttonColors(Color.White)
                    ) {
                        Text(text = "Save", color = Color.Black)
                    }
                }
            }
        }
    }
}