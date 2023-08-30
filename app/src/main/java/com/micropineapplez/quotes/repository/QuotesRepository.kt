package com.micropineapplez.quotes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.firebase.database.FirebaseDatabase
import com.micropineapplez.quotes.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class QuotesRepository(
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
) {
    fun getAllQuotes(): Flow<PagingData<Quote>> {
        val pagingConfig = PagingConfig(pageSize = 10, enablePlaceholders = false)

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { QuotesPagingSource(database) }
        ).flow.flowOn(Dispatchers.IO)
    }
}