package com.micropineapplez.quotes.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micropineapplez.quotes.R
import com.micropineapplez.quotes.model.Quote

@Composable
fun ItemQuote(quote: Quote, onClick: ((quote: Quote) -> Unit)?, color: Color) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                if (onClick != null) {
                    onClick(quote)
                }
            }
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                modifier = Modifier.size(50.dp),
                alignment = Alignment.TopStart,
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = quote.quote,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 16.sp
                )
                Box(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.5f))
                        .fillMaxWidth(.4f)
                        .height(1.dp)
                )
                Text(
                    text = quote.author,
                    modifier = Modifier.padding(top = 4.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemQuote() {
    ItemQuote(
        quote = Quote("Quote goes here", "Author name"),
        onClick = null,
        color = Color.LightGray
    )
}