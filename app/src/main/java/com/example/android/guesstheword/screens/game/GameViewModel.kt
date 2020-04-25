package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.base.BuzzType

class GameViewModel : ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private var timer: CountDownTimer

    // The current word
    private val _word = MutableLiveData<String>()

    // backing property
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()

    //backing property
    val score: LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _gameFinishedEvent = MutableLiveData<Boolean>()

    // backing property
    val gameFinishedEvent: LiveData<Boolean>
        get() = _gameFinishedEvent

    private val _currentTime = MutableLiveData<Long>()

    // backing property
    val currentTime: LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime) { timeLeft ->
        DateUtils.formatElapsedTime(timeLeft)
    }

    private val _buzzType = MutableLiveData<BuzzType>()
    val buzzType: LiveData<BuzzType>
        get() = _buzzType

    init {
        _score.value = 0
        _word.value = ""
        _gameFinishedEvent.value = false
        _currentTime.value = COUNTDOWN_TIME / 1000
        //_buzzType.value = BuzzType.NO_BUZZ
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _buzzType.value = BuzzType.GAME_OVER
                _gameFinishedEvent.value = true
                _currentTime.value = DONE
            }

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / 1000
            }
        }
        // start the timer
        timer.start()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = score.value?.dec()
        _buzzType.value = BuzzType.COUNTDOWN_PANIC
        nextWord()
    }

    fun onCorrect() {
        _buzzType.value = BuzzType.CORRECT
        _score.value = score.value?.inc()
        nextWord()
    }

    fun onGameFinishCompleted() {
        _gameFinishedEvent.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
