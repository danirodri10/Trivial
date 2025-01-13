package com.example.trivial.model

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String, // Índice de la respuesta correcta
)

