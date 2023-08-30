package com.micropineapplez.quotes.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.micropineapplez.quotes.model.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class QuotesPagingSource(
    private val database: FirebaseDatabase
) : PagingSource<Int, Quote>() {
    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return withContext(Dispatchers.IO) {
            val startAt = params.key ?: 0
            val endAt = startAt + params.loadSize
            val quotes = mutableListOf<Quote>()

            val dataSnapshot = fetchData(startAt, endAt)
            for (snapShot in dataSnapshot.children) {
                val quote = snapShot.child("quote").getValue(String::class.java) ?: ""
                val author = snapShot.child("author").getValue(String::class.java) ?: ""
                quotes.add(Quote(quote = quote, author = author))
            }

            val nextKey = if (quotes.size < params.loadSize) null else startAt + params.loadSize

            LoadResult.Page(data = quotes, prevKey = null, nextKey = nextKey)
        }
    }

    private suspend fun fetchData(startAt: Int, endAt: Int): DataSnapshot {
        return database.reference.child("quotes")
            .orderByKey()
            .startAt(startAt.toString())
            .endAt(endAt.toString())
            .get()
            .await()
    }
}