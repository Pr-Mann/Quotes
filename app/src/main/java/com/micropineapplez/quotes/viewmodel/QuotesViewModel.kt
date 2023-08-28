package com.micropineapplez.quotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micropineapplez.quotes.model.Quote
import com.micropineapplez.quotes.repository.QuotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesViewModel(
    private val repository: QuotesRepository = QuotesRepository()
) : ViewModel() {

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>>
        get() = _quotes

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getAllQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _quotes.postValue(repository.getAllQuotes())
            } catch (e: Exception) {
                Log.e("logs", "Error getting events: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}