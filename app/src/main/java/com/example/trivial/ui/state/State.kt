package com.example.trivial.ui.state

data class State(
    val newWord: String = "",
    val finalText: String = "",
    val check: Boolean = false,
    val checkOptions: Boolean = true,
    val questionsQuantity: Int = 1,
    val currentQuestionIndex: Int = 0,  // Índice de la pregunta actual
    val points: Int = 0
)