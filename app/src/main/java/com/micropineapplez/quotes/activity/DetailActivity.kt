package com.micropineapplez.quotes.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.micropineapplez.quotes.model.Quote
import com.micropineapplez.quotes.view.DetailActivityView

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quote = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("quote", Quote::class.java)
        } else {
            intent.getSerializableExtra("quote") as Quote
        }
        val colorIndex = intent.getIntExtra("index", 0)

        setContent {
            DetailActivityView(quote = quote, colorIndex = colorIndex)
        }
    }
}