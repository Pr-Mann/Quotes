package com.micropineapplez.quotes.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.micropineapplez.quotes.R
import com.micropineapplez.quotes.model.Quote

@Composable
fun DetailActivityView(quote: Quote?, colorIndex: Int) {

    val systemUiController = rememberSystemUiController()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val colors = listOf(
        colorResource(id = R.color.color1),
        colorResource(id = R.color.color2),
        colorResource(id = R.color.color3),
        colorResource(id = R.color.color4),
        colorResource(id = R.color.color5)
    )
    val color = colors[colorIndex % colors.size]

    LaunchedEffect(color) {
        systemUiController.setStatusBarColor(color)
        systemUiController.setNavigationBarColor(color)
    }
    quote?.let {
        Column(
            modifier = Modifier
                .background(color = color)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(50.dp)
                    .padding(all = 5.dp)
                    .clickable { backDispatcher?.onBackPressedDispatcher?.onBackPressed() },
                painter = painterResource(id = R.drawable.ic_back),
                colorFilter = ColorFilter.tint(color = Color.Black),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .align(alignment = Alignment.Start),
                    painter = painterResource(id = R.drawable.quote_open),
                    colorFilter = ColorFilter.tint(color = Color.Black),
                    contentDescription = ""
                )
                Text(
                    text = quote.quote,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = quote.author,
                    modifier = Modifier.padding(top = 4.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp
                )
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .align(alignment = Alignment.End),
                    painter = painterResource(id = R.drawable.quote_close),
                    colorFilter = ColorFilter.tint(color = Color.Black),
                    contentDescription = ""
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailActivityView() {
    DetailActivityView(
        quote = Quote("Quote goes here", "Author name"), colorIndex = 0
    )
}