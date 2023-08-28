package com.micropineapplez.quotes.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.micropineapplez.quotes.model.Quote
import kotlinx.coroutines.tasks.await

class QuotesRepository(
    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference,
) {
    suspend fun getAllQuotes(): List<Quote> {
        val quotesRef: DatabaseReference = rootRef.child("quotes")
        val quotes = mutableListOf<Quote>()
        try {
            quotes.addAll(quotesRef.get().await().children.map { snapShot ->
                snapShot.getValue(Quote::class.java)!!
            })
        } catch (e: Exception) {
            quotes.addAll(emptyList())
        }
        return quotes
    }
}