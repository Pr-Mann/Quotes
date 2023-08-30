package com.micropineapplez.quotes.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.micropineapplez.quotes.R

@Composable
fun TopBarView(title: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = title,
            color = colorResource(id = R.color.colorPrimary),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Divider(
            modifier = Modifier.padding(start = 10.dp, top = 5.dp, end = 10.dp),
            color = colorResource(id = R.color.colorPrimary)
        )
    }
}

@Preview
@Composable
fun PreviewTopBarView() {
    TopBarView(title = "Title")
}