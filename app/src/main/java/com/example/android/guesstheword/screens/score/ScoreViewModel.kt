package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int): ViewModel() {

    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain: LiveData<Boolean>
    get() = _eventPlayAgain

    private val _score= MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    init {
        _score.value = finalScore
        _eventPlayAgain.value = false
    }
}