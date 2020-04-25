package com.example.android.guesstheword.base

import com.example.android.guesstheword.base.Constants.CORRECT_BUZZ_PATTERN
import com.example.android.guesstheword.base.Constants.GAME_OVER_BUZZ_PATTERN
import com.example.android.guesstheword.base.Constants.NO_BUZZ_PATTERN
import com.example.android.guesstheword.base.Constants.PANIC_BUZZ_PATTERN

enum class BuzzType(val pattern: LongArray) {
    CORRECT(CORRECT_BUZZ_PATTERN),
    GAME_OVER(GAME_OVER_BUZZ_PATTERN),
    COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
    NO_BUZZ(NO_BUZZ_PATTERN)
}