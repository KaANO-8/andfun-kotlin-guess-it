package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(private val finalScore: Int): ViewModel() {

    init {
        Log.i("My Tag" ,"$finalScore")
    }
}