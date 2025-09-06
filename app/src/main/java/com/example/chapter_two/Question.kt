package com.example.chapter_two

import androidx.annotation.StringRes

data class Question (@StringRes val textResId:Int, val answer: Boolean)