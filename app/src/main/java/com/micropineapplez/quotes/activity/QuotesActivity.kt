package com.micropineapplez.quotes.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.micropineapplez.quotes.view.QuotesActivityView
import com.micropineapplez.quotes.viewmodel.QuotesViewModel

class QuotesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: QuotesViewModel by viewModels()

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                return@setKeepOnScreenCondition viewModel.isLoading.value
            }
        }

        setContent {
            QuotesActivityView()
        }
    }
}