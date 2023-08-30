package com.micropineapplez.quotes.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.micropineapplez.quotes.R
import com.micropineapplez.quotes.viewmodel.QuotesViewModel

@Composable
fun QuotesActivityView(viewModel: QuotesViewModel = viewModel()) {

    val quotes = viewModel.quotes.collectAsLazyPagingItems()
    if (quotes.itemCount != 0) {
        viewModel.loadingIsComplete()
    }

    val colors = listOf(
        colorResource(id = R.color.color1),
        colorResource(id = R.color.color2),
        colorResource(id = R.color.color3),
        colorResource(id = R.color.color4),
        colorResource(id = R.color.color5)
    )

    Column(modifier = Modifier.background(color = Color.White)) {
        TopBarView(title = "Quotes App")
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp, top = 5.dp, end = 5.dp),
            content = {
                items(quotes.itemCount) { index ->
                    quotes[index]?.let {
                        ItemQuote(
                            quote = it,
                            onClick = { },
                            color = colors[index % colors.size]
                        )
                    }
                }
            })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuotesActivityView() {
    QuotesActivityView()
}