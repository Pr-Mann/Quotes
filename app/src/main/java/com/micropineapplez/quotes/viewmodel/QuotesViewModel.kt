package com.micropineapplez.quotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.micropineapplez.quotes.model.Quote
import com.micropineapplez.quotes.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuotesViewModel(
    private val repository: QuotesRepository = QuotesRepository()
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    fun loadingIsComplete() {
        _isLoading.value = false
    }

    val quotes: Flow<PagingData<Quote>> by lazy {
        repository.getAllQuotes().cachedIn(viewModelScope)
    }
}