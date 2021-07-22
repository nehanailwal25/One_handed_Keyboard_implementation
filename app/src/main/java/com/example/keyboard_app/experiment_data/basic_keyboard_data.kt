package com.example.keyboard_app.experiment_data

data class basic_keyboard_data(

    val Entry_keypad: String,
    val provided_text: String,
    val entered_text: String,
    val provided_text_length: Int,
    val entered_text_length: Int,
    val error_rate: Int,
    val accuracy_rate: Int,
    val time_taken: Long,
    val error_percent: Float,
    val words_per_minute: Float
)