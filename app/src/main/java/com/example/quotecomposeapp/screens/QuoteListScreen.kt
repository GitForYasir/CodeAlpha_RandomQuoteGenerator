package com.example.quotecomposeapp.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quotecomposeapp.R
import com.example.quotecomposeapp.models.Quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteListScreen( data: Array<Quote>, onClick: (quote: Quote) -> Unit) {

    var isQuoteDisplay by remember {
        mutableStateOf(false)
    }

    var quoteIndex by remember {
        mutableIntStateOf(0)
    }

    val localContext = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = {
            Text(
                text = "Quotes App",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp, 24.dp)
                    .fillMaxWidth(1f),
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            )
        })


        Spacer(modifier = Modifier.height(82.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuoteListItem(
                quote = data[quoteIndex],
                onclick = onClick,
                modifier = Modifier.weight(2f)
            )

            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                isQuoteDisplay = true
                quoteIndex += 1
            }
            ) {
                Text(text = "Get new Quote")
            }

            Button(onClick = {
                shareQuote(localContext, data[quoteIndex].text, data[quoteIndex].author)
            }) {
                Text(text = "Share Quote")
            }
        }

    }

//        QuoteList(data = data, onClick)
}


fun shareQuote(context: Context, quote: String, author: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "$quote \n $author")
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share quote via"))
}
