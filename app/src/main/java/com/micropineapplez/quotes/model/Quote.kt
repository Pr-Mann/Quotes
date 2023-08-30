package com.micropineapplez.quotes.model

import java.io.Serializable

data class Quote(
    val quote: String,
    val author: String
) : Serializable